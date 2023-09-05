package aisl.ksensor.filtermanager.filtering.controller;

import aisl.ksensor.filtermanager.filtering.data.dto.FilterDTO;
import aisl.ksensor.filtermanager.filtering.data.dto.PropagationSetupRequestDTO;
import aisl.ksensor.filtermanager.filtering.service.FilteringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FilteringController {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    FilteringService mlService;


    /**
     * create dataModel
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param propagationSetupRequestDTO create dataModel data
     * @throws Exception create error
     */
    @PostMapping("/setup")
    public @ResponseBody void setupOptimization(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @RequestBody PropagationSetupRequestDTO propagationSetupRequestDTO) throws Exception {
        log.info("Setup ML Request msg: '{}'", propagationSetupRequestDTO);

        // 1. Validation
        // TODO: Essential Value Check

        // 2. Duplicate Check
        String retrieveServiceId = propagationSetupRequestDTO.getServiceId();
//        if (retrieveServiceId != null) {
//            throw new Exception("Duplicate Service ID");
//        }

        try {
            mlService.setupService(propagationSetupRequestDTO);
        } catch (Exception e) {
            log.error("Failed to setup optimization", e);
            throw new Exception("Failed to setup optimization");
        }
    }


    /**
     * run optimization
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param requestBody run optimization data
     * @throws Exception run error
     */
    @PostMapping("/filter/run")
    public @ResponseBody void runOptimization(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody String requestBody) throws Exception {

        log.info("RunOptimization Reqeust msg= '{}'", requestBody);
    }

    /**
     * regist optimizationAlgorithm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param mlModelDTO run optimization data
     * @throws Exception run error
     */
    @PostMapping("/filter/regist")
    public @ResponseBody void registOptimizationAlgorithm(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          @RequestBody FilterDTO filterDTO) throws Exception {


        mlService.registFilter(filterDTO.getFilterType(),
                filterDTO.getFilterParam(),
                filterDTO.getFilterStoragePath(),
                filterDTO.getCreateBy());


    }

    @GetMapping("/filters")
    public @ResponseBody List<FilterDTO> getMlModels() {
        return mlService.getFilters();
    }
}
