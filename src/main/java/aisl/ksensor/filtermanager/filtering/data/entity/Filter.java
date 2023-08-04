package aisl.ksensor.filtermanager.filtering.data.entity;

import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "filter", schema = "filter_manager")
@Data
public class Filter {

    @Id
    @Column(name = "filterType")
    private String filterType;

    @Column(name = "filterStoragePath", nullable = false)
    private String filterStoragePath;

    @Column(name = "createAt", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updateAt")
    private LocalDateTime updateAt;

    @Column(name = "createBy", nullable = false)
    private String createBy;

    @Column(name = "updateBy")
    private String updateBy;


    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateAt = LocalDateTime.now();
    }

}
