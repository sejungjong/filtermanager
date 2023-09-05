package aisl.ksensor.filtermanager.filtering.service;

import aisl.ksensor.filtermanager.common.dto.ParameterRange;
import aisl.ksensor.filtermanager.filtering.data.dto.FilterDTO;
import aisl.ksensor.filtermanager.filtering.data.dto.PropagationSetupRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface FilteringService {

    public void setupService(PropagationSetupRequestDTO propagationSetupRequestDTO)  throws MalformedURLException, JsonProcessingException;

    public void deleteService(Long serviceId);

    public <T> void registFilter(String type, Map<String, Map<String, ParameterRange<T>>> filterParam, String storagePath, String insertedBy);

    public List<FilterDTO> getFilters();
}
