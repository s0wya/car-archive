package pl.conmir.cararchive;


import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import pl.conmir.cararchive.originalModificationFile.FileData;
import pl.conmir.cararchive.originalModificationFile.FileName;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class ModifiedFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "name"))
    private ModifiedFileName name;

    private String type;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "data"))
    private ModifiedFileData data;

    @ManyToOne
    private Car car;
}
