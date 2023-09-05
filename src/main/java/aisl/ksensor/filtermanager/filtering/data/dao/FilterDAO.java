package aisl.ksensor.filtermanager.filtering.data.dao;

import aisl.ksensor.filtermanager.filtering.data.entity.Filter;

import java.util.List;
import java.util.Optional;

public interface FilterDAO {

    Filter insertFilter(Filter optimizationAlgorithm);

    Optional<Filter> findFilterByFilterType(String type);

    List<Filter> findAllFilters();

    Filter updateFilter(Filter optimizationAlgorithm);

    void deleteFilter(String type);
}
