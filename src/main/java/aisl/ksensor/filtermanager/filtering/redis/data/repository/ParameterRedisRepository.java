package aisl.ksensor.filtermanager.filtering.redis.data.repository;

import aisl.ksensor.filtermanager.filtering.redis.data.entity.ParameterEntity;
import org.springframework.data.repository.CrudRepository;

public interface ParameterRedisRepository extends CrudRepository<ParameterEntity, String> {
}
