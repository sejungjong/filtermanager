package aisl.ksensor.filtermanager.filtering.data.dao.impl;

import aisl.ksensor.filtermanager.filtering.data.dao.FilterDAO;
import aisl.ksensor.filtermanager.filtering.data.entity.Filter;
import aisl.ksensor.filtermanager.filtering.data.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class FilterDAOImpl implements FilterDAO {

    private final FilterRepository filterRepository;
    @Autowired
    public FilterDAOImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    @Override
    public Filter insertFilter(Filter filter) {
        return filterRepository.save(filter);
    }

    @Override
    public Optional<Filter> findFilterByFilterType(String type) {
        return filterRepository.findByFilterType(type);
    }

    @Override
    public List<Filter> findAllFilters() {
        return filterRepository.findAll();
    }

    @Override
    public Filter updateFilter(Filter filter) {
        Optional<Filter> selectedFilter = filterRepository.findByFilterType(filter.getFilterType());

        if (selectedFilter.isPresent()) {
            Filter updateMlModel = selectedFilter.get();
            updateMlModel.setFilterType(filter.getFilterType());
            updateMlModel.setFilterStoragePath(filter.getFilterStoragePath());
            updateMlModel.setUpdateAt(LocalDateTime.now());


            return filterRepository.save(updateMlModel);
        } else {
            throw new RuntimeException("Filter not found");
        }
    }

    @Override
    public void deleteFilter(String type) {
        Optional<Filter> selectedFilterOptional = filterRepository.findByFilterType(type);

        if (selectedFilterOptional.isPresent()) {
            Filter selectedFilter = selectedFilterOptional.get();
            filterRepository.delete(selectedFilter);
        } else {
            throw new RuntimeException("Filter not found");
        }
    }
}
