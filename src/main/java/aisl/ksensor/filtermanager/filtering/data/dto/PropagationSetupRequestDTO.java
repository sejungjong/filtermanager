package aisl.ksensor.filtermanager.filtering.data.dto;

import aisl.ksensor.filtermanager.common.dto.ParameterRange;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PropagationSetupRequestDTO<T> {

    private Long modelId;
    private String serviceId;
    private String optimizationAlgorithmType;
    private Map<String, Object> optimizationParam;
    private List<Map<String, Map<String, ParameterRange<T>>>> sensorParam;
    private Map<String, Map<String, ParameterRange<T>>> filterParam;
    private Map<String, Map<String, ParameterRange<T>>> hyperParam;
    private String sensorType;
}

