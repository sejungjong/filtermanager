package aisl.ksensor.filtermanager.filtering.data.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PropagationSetupRequestDTO {

    private Long modelId;
    private String serviceId;
    private String algorithmType;
    private List<Map<String, Map<String, List<Object>>>> sensorParam;
    private Map<String, Map<String, List<Object>>> filterParam;
    private Map<String, Map<String, List<Object>>> hyperParam;
    private Map<String, Object> optExit;
}

