package pl.conmir.cararchive.originalFile;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.conmir.cararchive.car.domain.Car;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OriginalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "name"))
    private FileName name;

    private String type;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "data"))
    private FileData data;

}
