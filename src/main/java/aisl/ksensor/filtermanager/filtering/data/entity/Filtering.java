package aisl.ksensor.filtermanager.filtering.data.entity;

import aisl.ksensor.filtermanager.common.code.JsonConverter;
import aisl.ksensor.filtermanager.common.engine.data.entity.Engine;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "filtering", schema = "filter_manager")
@Data
public class Filtering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filteringId")
    private Long filterId;

    @Convert(converter = JsonConverter.class)
    @Column(name = "filterparameterRange", nullable = false)
    private Map<String, List<Object>> filterparameterRange;

    @Column(name = "optimizationOffset", nullable = false)
    private Integer optimizationOffset;

    @Column(name = "modelId", nullable = false)
    private Long modelId;

//    @OneToMany // Filter 외래키 관계 설정
//    @JoinColumn(name = "filterType", nullable = false)
//    private List<Filter> filter;

    @OneToOne // Model 외래키 관계 설정
    @JoinColumn(name = "engineId", nullable = false)
    private Engine filteringEngine;

    @Column(name = "createAt", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updateAt")
    private LocalDateTime updateAt;

    @Column(name = "createBy", nullable = false)
    private String createBy;

    @Column(name = "updateBy")
    private String updateBy;

//    @OneToMany(mappedBy = "filtering")
//    private List<Filter> filters;

    @ManyToMany
    @JoinTable(
            name = "filtering_filter", // 연관 테이블 이름
            joinColumns = @JoinColumn(name = "filteringId"),
            inverseJoinColumns = @JoinColumn(name = "filterType")
    )
    private List<Filter> filters;

    @PrePersist
    public void prePersist() {
        optimizationOffset = 0;
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        optimizationOffset++;
        updateAt = LocalDateTime.now();
    }
}
