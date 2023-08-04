package aisl.ksensor.filtermanager.filtering.service.impl;

import aisl.ksensor.filtermanager.common.code.FilterManagerCode.ServiceZoneCode;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

//        for (String filterType : propagationSetupRequestDTO.getFilterParam().keySet()) {
//            System.out.println("Filter Type: " + filterType);
////            System.out.println("Filter Param: " + propagationSetupRequestDTO.getFilterParam().get(filterType));
//            Optional<Filter> filter = filterDAO.findFilterByFilterType(filterType);
//            if (filter.isEmpty()) {
//                throw new NoSuchElementException(filterType + "Filter is not exist");
//            }
//            filterStoragePathList.add(filter.get().getFilterStoragePath());
//        }

        System.out.println("ServiceId " + propagationSetupRequestDTO.getServiceId());
        engineService.setupEngine(propagationSetupRequestDTO.getServiceId(), "nginx",
                filterStoragePathList, ServiceZoneCode.SERVICE_ZONE_FILTER_ENGINE.getCode()).flatMap(engineDTO -> {
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


    public void registFilter(String filterType, String storagePath, String insertedBy) {

//        FilterDTO filterDTO = new FilterDTO();
//        filterDTO.setFilterType(filterType);
//        filterDTO.setFilterStoragePath(storagePath);
//        filterDTO.setCreateBy(insertedBy);
        System.out.println("Filter Type: " + filterType);
        System.out.println("Filter Storage Path: " + storagePath);
        System.out.println("Inserted By: " + insertedBy);


        Filter filter = new Filter();
        filter.setFilterType(filterType);
        filter.setFilterStoragePath(storagePath);
        filter.setCreateBy(insertedBy);
        System.out.println("filter is :" + filter);
        filterDAO.insertFilter(filter);
    }

    public String provisioningServiceEngine(String serviceId) {
        return "hi";
    }

    public void deleteService(Long serviceId) {
        log.info("deleteService");
    }



}
