package pl.conmir.cararchive.car;

/*
    TODO:
        -registration number DONE
        -make - DONE
        -model - DONE
        -production year - DONE
        -original file - DONE
        -modified file - in progress

 */


import pl.conmir.cararchive.car.Make;
import pl.conmir.cararchive.car.Model;
import pl.conmir.cararchive.car.ProductionYear;
import pl.conmir.cararchive.car.RegistrationNumber;
import pl.conmir.cararchive.modifiedFile.ModifiedFile;
import pl.conmir.cararchive.originalModificationFile.OriginalModificationFile;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "registration_number"))
    private RegistrationNumber registration;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "model"))
    private Model model;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "make"))
    private Make make;

    @Embedded
    @AttributeOverride( name = "value", column = @Column(name = "production_year"))
    private ProductionYear year;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private OriginalModificationFile originalFile;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<ModifiedFile> modifiedFiles;
}
