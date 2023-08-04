package aisl.ksensor.filtermanager.filtering.redis.data.entity;


import aisl.ksensor.filtermanager.filtering.data.dto.PropagationSetupRequestDTO;
import com.google.gson.Gson;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash(value = "parameters", timeToLive = 30)
@NoArgsConstructor
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Indexed
    private String serviceName; // 동적으로 할당되는 키 값

    private  String sensorParameter;
    private  String filterParameter;
    private  String hyperParameter;
    private  Integer transactionNumber;

//    private Long modelId;
//    private String serviceId;
//    private String algorithmType;
//    private List<Map<String, Map<String, List<Object>>>> sensorParam;
//    private Map<String, Map<String, List<Object>>> filterParam;
//    private Map<String, Map<String, List<Object>>> hyperParam;
//    private Map<String, Object> optExit;

    public ParameterEntity(PropagationSetupRequestDTO propagationSetupRequestDTO) {
        Gson gson = new Gson();
        this.serviceName = propagationSetupRequestDTO.getServiceId();
        this.sensorParameter = gson.toJson(propagationSetupRequestDTO.getSensorParam());
        this.filterParameter = gson.toJson(propagationSetupRequestDTO.getFilterParam());
        this.hyperParameter = gson.toJson(propagationSetupRequestDTO.getHyperParam());
        this.transactionNumber = 0;
    }


}
