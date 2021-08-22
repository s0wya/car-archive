package pl.conmir.cararchive.originalModificationFile;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.conmir.cararchive.car.Car;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OriginalModificationFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "name"))
    private FileName name;

    private String type;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "data"))
    private FileData data;

    @OneToOne
    private Car car;
}
