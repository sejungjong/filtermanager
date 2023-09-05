package aisl.ksensor.filtermanager.filtering.service.impl;

import aisl.ksensor.filtermanager.common.code.FilterManagerCode.ServiceZoneCode;
import aisl.ksensor.filtermanager.common.dto.ParameterRange;
import aisl.ksensor.filtermanager.common.engine.data.dao.EngineDAO;
import aisl.ksensor.filtermanager.common.engine.service.EngineService;
import aisl.ksensor.filtermanager.filtering.data.dao.FilterDAO;
import aisl.ksensor.filtermanager.filtering.data.dao.FilteringDAO;
import aisl.ksensor.filtermanager.filtering.data.dto.FilterDTO;
import aisl.ksensor.filtermanager.filtering.data.dto.PropagationSetupRequestDTO;
import aisl.ksensor.filtermanager.filtering.data.entity.Filter;
import aisl.ksensor.filtermanager.filtering.redis.data.repository.ParameterRedisRepository;
import aisl.ksensor.filtermanager.filtering.service.FilteringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.util.*;

@Service
@Slf4j
public class FilteringServiceImpl implements FilteringService {

    private final FilteringDAO filteringDAO;
    private final FilterDAO filterDAO;
    private final EngineDAO engineDAO;

    private ParameterRedisRepository parameterRedisRepository;

    @Autowired
    public FilteringServiceImpl(FilteringDAO filteringDAO, FilterDAO filterDAO, EngineDAO engineDAO) {
        this.filteringDAO = filteringDAO;
        this.filterDAO = filterDAO;
        this.engineDAO = engineDAO;
    }

    @Autowired
    private EngineService engineService;


    public void setupService(PropagationSetupRequestDTO propagationSetupRequestDTO)  throws MalformedURLException, JsonProcessingException{
        log.info("setupService");
        log.info(propagationSetupRequestDTO.toString());

        List<String> filterTypeList = new ArrayList<>();
        List<String> filterStoragePathList = new ArrayList<>();

        for (Object filterType : propagationSetupRequestDTO.getFilterParam().keySet()) {
            String filterTypeString = (String) filterType;
            System.out.println("Filter Type: " + filterTypeString);
//            System.out.println("Filter Param: " + propagationSetupRequestDTO.getFilterParam().get(filterType));
            Optional<Filter> filter = filterDAO.findFilterByFilterType(filterTypeString);
            if (filter.isEmpty()) {
                throw new NoSuchElementException(filterType + "Filter is not exist");
            }
            filterStoragePathList.add(filter.get().getFilterStoragePath());
        }

        System.out.println("ServiceId " + propagationSetupRequestDTO.getServiceId());
        engineService.setupEngine(propagationSetupRequestDTO.getServiceId(), ServiceZoneCode.ENGINE_IMAGE_NAME.getCode(),
                filterStoragePathList, ServiceZoneCode.FILTER_ENGINE.getCode(),
                propagationSetupRequestDTO.getSensorType()).flatMap(engineDTO -> {
                    System.out.println("EngineDTO: " + engineDTO);
                    return Mono.just(engineDTO);
//            ParameterEntity parameterEntity = new ParameterEntity(propagationSetupRequestDTO);
//            Mono<ParameterEntity> saveOperation = Mono.fromCallable(() -> parameterRedisRepository.save(parameterEntity))
//                    .subscribeOn(Schedulers.elastic());
//            return Mono.zip(Mono.just(engineDTO), saveOperation, (engine, savedEntity) -> {
//                System.out.println("Saved entity ID: " + savedEntity.getId());
//                System.out.println("Saved Entity Retrival Test: " + parameterRedisRepository.findById(savedEntity.getId()).get());
//                return engine;
//            });
        }).subscribe(result -> {
            // 최종 결과 처리
            System.out.println("Result of the task: " + result);
        }, error -> {
            // 에러 처리
            System.out.println("Failed to set up engine or perform the task: " + error.getMessage());
        });
    }



//        OptimizationDTO optimizationDTO = new OptimizationDTO();
//        optimizationDTO.setOptimizationExitCondition(propagationSetupRequestDTO.getOptExit());
//        optimizationDTO.setOptimizationAlgorithmType(propagationSetupRequestDTO.getAlgorithmType());
//        optimizationDTO.setModelId(propagationSetupRequestDTO.getModelId());
//        optimizationDTO.setOptimizationOffset(0);
//        optimizationDTO.setCreateBy(ServiceCommonCode.OPTIMIZATIONMANAGER.getCode());
//
//        Optimization optimization = new Optimization();
//        optimization.setModelId(propagationSetupRequestDTO.getModelId());
////                    optimization.setOptimizationAlgorithm(optimizationAlgorithm.get());
//        optimization.setOptimizationAlgorithm(optimizationAlgorithm.get());
//        optimization.setOptimizationExitCondition(optimizationDTO.getOptimizationExitCondition());
//        optimization.setOptimizationOffset(optimizationDTO.getOptimizationOffset());
//        optimization.setCreateBy(optimizationDTO.getCreateBy());


    public <T> void registFilter(String filterType, Map<String, Map<String, ParameterRange<T>>> filterParam, String storagePath, String insertedBy) {

        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setFilterType(filterType);
        filterDTO.setFilterParam(filterParam);
        filterDTO.setFilterStoragePath(storagePath);
        filterDTO.setCreateBy(insertedBy);
//        System.out.println("Filter Type: " + filterType);
//        System.out.println("Filter Param: " + filterType);
//        System.out.println("Filter Storage Path: " + storagePath);
//        System.out.println("Inserted By: " + insertedBy);


        Filter filter = new Filter();
        filter.setFilterType(filterDTO.getFilterType());
        filter.setFilterParam(filterDTO.getFilterParam());
        filter.setFilterStoragePath(filterDTO.getFilterStoragePath());
        filter.setCreateBy(filterDTO.getCreateBy());
        System.out.println("filter is :" + filter);
        filterDAO.insertFilter(filter);
    }

    public List<FilterDTO> getFilters() {

        List<FilterDTO> filterDTOList = new ArrayList<>();

        List<Filter> filterList =
                filterDAO.findAllFilters();
        for (Filter filter : filterList) {
            FilterDTO<Object> filterDTO = new FilterDTO<>();
            filterDTO.setFilterType(filter.getFilterType());

            // optimizationParam 변환 로직
            Map<String, Object> originalParams = filter.getFilterParam();

            Map<String, ParameterRange<Object>> innerMap = new HashMap<>();

            for (Map.Entry<String, Object> entry : originalParams.entrySet()) {
                {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> paramMap = (Map<String, Object>) entry.getValue();

                        ParameterRange<Object> parameterRange = new ParameterRange<>();
                        parameterRange.setType((String) paramMap.get("type"));
                        parameterRange.setStart((Object) paramMap.get("start"));
                        parameterRange.setEnd((Object) paramMap.get("end"));
                        parameterRange.setOptions((List<String>) paramMap.get("options"));

                        innerMap.put(entry.getKey(), parameterRange);
                    }


                }
                filterDTO.setFilterParam(innerMap);
                filterDTO.setFilterStoragePath(filter.getFilterStoragePath());
                filterDTO.setCreateBy(filter.getCreateBy());

                filterDTOList.add(filterDTO);
            }

        }
        return filterDTOList;
    }

    public void deleteService(Long serviceId) {
        log.info("deleteService");
    }



}
