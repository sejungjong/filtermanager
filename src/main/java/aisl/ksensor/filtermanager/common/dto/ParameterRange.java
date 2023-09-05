package aisl.ksensor.filtermanager.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParameterRange<T> {
    private String type;
    private T start;
    private T end;
    private List<String> options; // options 필드 추가
}
