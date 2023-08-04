package aisl.ksensor.filtermanager.filtering.redis.data.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDTO {

//    private String id;
    private String serviceName; // 동적으로 할당되는 키 값
    private List<Map<String, List<Map<String, Object>>>> sensorParameter;
    private Map<String, Map<String, Object>> filterParameter;
    private Map<String, Map<String, Object>> hyperParameter;
    private Integer transactionNumber;

    public ParameterDTO(String serviceName, String sensorParameter, String filterParameter, String hyperParameter, Integer transactionNumber) {
        Gson gson = new Gson();
        Type sensorParamType = new TypeToken<List<Map<String, List<Map<String, Object>>>>>(){}.getType();
        Type otherParamType = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
        this.serviceName = serviceName;
        this.sensorParameter = gson.fromJson(sensorParameter, sensorParamType);
        this.filterParameter = gson.fromJson(filterParameter, otherParamType);
        this.hyperParameter = gson.fromJson(hyperParameter, otherParamType);
        this.transactionNumber = transactionNumber;
    }

    // 생성자 오버로딩 - 필드 값이 없을 때 빈 맵으로 초기화
    public ParameterDTO(String serviceName, Integer transactionNumber) {
        this.serviceName = serviceName;
        this.sensorParameter = null;
        this.filterParameter = null;
        this.hyperParameter = null;
        this.transactionNumber = transactionNumber;
    }
}
