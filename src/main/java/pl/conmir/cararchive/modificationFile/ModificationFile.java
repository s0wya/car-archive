package pl.conmir.cararchive.modificationFile;


import lombok.*;

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
    private ModificationFileName name;

    private String type;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "data"))
    private ModificationFileData data;

}
