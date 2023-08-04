package aisl.ksensor.filtermanager.common.engine.data.repository;

import aisl.ksensor.filtermanager.common.engine.data.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    Engine findByEngineContainerName(String id);
}
