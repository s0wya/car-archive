package pl.conmir.cararchive.car.domain;

/*
    TODO:
        -registration number DONE
        -make - DONE
        -model - DONE
        -production year - DONE
        -original file - DONE
        -modified file - in progress
 */


import lombok.*;
import pl.conmir.cararchive.modificationFile.ModificationFile;
import pl.conmir.cararchive.originalFile.OriginalFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="original_file_id")
    private OriginalFile originalFile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="car_id")
    private Set<ModificationFile> modificationFiles;

    public void changeRegistration(RegistrationNumber registrationNumber){
        this.registration = registrationNumber;
    }

    public void changeMake(Make make){
        this.make = make;
    }

    public void changeModel(Model model){
        this.model = model;
    }

    public void changeProductionYear(ProductionYear productionYear){
        this.year = productionYear;
    }

    public void setOriginalModificationFile(OriginalFile file) {
        this.originalFile = file;
    }

    public void removeOriginalModificationFile() {
        this.originalFile = null;
    }

    public void addModifiedFile(ModificationFile file) {
        this.modificationFiles.add(file);
    }

    public void removeModifiedFile(ModificationFile file) {
        this.modificationFiles.remove(file);
    }

}

