package pl.conmir.cararchive.domain.performanceModification;

import lombok.*;
import pl.conmir.cararchive.domain.performance.Power;
import pl.conmir.cararchive.domain.performance.Torque;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PerformanceModification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "power_after"))
    private Power powerAfter;
    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "torqueAfter"))
    private Torque torqueAfter;

}
