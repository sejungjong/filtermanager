package aisl.ksensor.filtermanager.filtering.data.entity;

import aisl.ksensor.filtermanager.common.code.JsonConverter;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "filter", schema = "filter_manager")
@Data
public class Filter {

    @Id
    @Column(name = "filterType")
    private String filterType;

    @Convert(converter = JsonConverter.class)
    @Column(name = "filterParam", columnDefinition = "TEXT") // JSON 문자열로 저장될 것이므로 TEXT 타입을 사용합니다.
    private Map<String, Object> filterParam;

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
