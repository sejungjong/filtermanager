package aisl.ksensor.filtermanager.filtering.data.dto;

import aisl.ksensor.filtermanager.common.dto.ParameterRange;
import lombok.Data;

import java.util.Map;

@Data
public class FilterDTO<T> {

    private String filterType;
    Map<String, ParameterRange<T>> filterParam;
    private String filterStoragePath;
    private String createBy;
}
