package aisl.ksensor.filtermanager.filtering.service;

import aisl.ksensor.filtermanager.filtering.data.dto.PropagationSetupRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.MalformedURLException;

public interface FilteringService {

    public void setupService(PropagationSetupRequestDTO propagationSetupRequestDTO)  throws MalformedURLException, JsonProcessingException;

    public void deleteService(Long serviceId);

    public void registFilter(String type, String storagePath, String insertedBy);

    public String provisioningServiceEngine(String serviceId);
}
