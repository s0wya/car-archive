package pl.conmir.cararchive.domain.modificationFile;


import lombok.*;
import pl.conmir.cararchive.domain.originalFile.FileData;
import pl.conmir.cararchive.domain.originalFile.FileName;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModificationFile {

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
