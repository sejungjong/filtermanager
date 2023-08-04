package aisl.ksensor.filtermanager.filtering.data.dao.impl;

import aisl.ksensor.filtermanager.filtering.data.dao.FilteringDAO;
import aisl.ksensor.filtermanager.filtering.data.entity.Filtering;
import aisl.ksensor.filtermanager.filtering.data.repository.FilteringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FilteringDAOImpl implements FilteringDAO {


    private final FilteringRepository filteringRepository;
    @Autowired
    public FilteringDAOImpl(FilteringRepository filteringRepository) {
        this.filteringRepository = filteringRepository;
    }

    @Override
    public Filtering insertFiltering(Filtering filtering) {
        return filteringRepository.save(filtering);
    }

    @Override
    public Optional<Filtering> findFilteringById(Long id) {
        return filteringRepository.findById(id);
    }

    @Override
    public Filtering updateOptimizationOffset(Long id) throws Exception {
        Optional<Filtering> selectedFilteringOptional = filteringRepository.findById(id);

        if (selectedFilteringOptional.isPresent()) {
            Filtering filtering = selectedFilteringOptional.get();
            filtering.preUpdate();

            return filteringRepository.save(filtering);
        }
        else {
            throw new Exception("Filtering Not found");
        }
    }

    @Override
    public void deleteFiltering(Long id) throws Exception{
        Optional<Filtering> selectedFilteringOptional = filteringRepository.findById(id);

        if (selectedFilteringOptional.isPresent()) {
            Filtering filtering = selectedFilteringOptional.get();

            filteringRepository.delete(filtering);
        }
        else {
            throw new Exception("Filtering Not found");
        }
    }
}

