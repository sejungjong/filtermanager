package aisl.ksensor.filtermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"aisl.ksensor.filtermanager.filtering.data.repository", "aisl.ksensor.filtermanager.common.engine.data.repository"})
@ComponentScan(basePackages = {"aisl.ksensor.filtermanager.filtering", "aisl.ksensor.filtermanager.common"})
@EntityScan(basePackages = { "aisl.ksensor.filtermanager.filtering.data.entity", "aisl.ksensor.filtermanager.common.transfer.redis.data.entity","aisl.ksensor.filtermanager.common.engine.data.entity" })
@SpringBootApplication
public class FiltermanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiltermanagerApplication.class, args);
    }

}
