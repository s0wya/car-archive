package pl.conmir.cararchive.domain.performance;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "power"))
    private Power power;
    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "torque"))
    private Torque torque;


}
