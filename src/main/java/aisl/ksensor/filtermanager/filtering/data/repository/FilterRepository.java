package aisl.ksensor.filtermanager.filtering.data.repository;

import aisl.ksensor.filtermanager.filtering.data.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilterRepository extends JpaRepository<Filter, Long>{

    Optional<Filter> findByFilterType(String type);

    List<Filter> findAll();
}
