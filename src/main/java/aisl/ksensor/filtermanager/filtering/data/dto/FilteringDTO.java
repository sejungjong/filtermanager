package aisl.ksensor.filtermanager.filtering.data.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class FilteringDTO {

    private Map<String, List<Object>> filterparameterRange;
    private Integer optimizationOffset;
    private String createBy;
    private Long ModelId;
    private List<String> filterType;
    private Long engineId;
}
