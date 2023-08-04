package aisl.ksensor.filtermanager.filtering.data.dao;

import aisl.ksensor.filtermanager.filtering.data.entity.Filtering;

import java.util.Optional;

public interface FilteringDAO {

    Filtering insertFiltering(Filtering ml);

    Optional<Filtering> findFilteringById(Long id);

    Filtering updateOptimizationOffset(Long id) throws Exception;

    void deleteFiltering(Long id) throws Exception;
}
