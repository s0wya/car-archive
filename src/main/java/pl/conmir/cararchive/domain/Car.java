package pl.conmir.cararchive.domain;


import lombok.*;
import pl.conmir.cararchive.domain.modificationFile.ModificationFile;
import pl.conmir.cararchive.domain.originalFile.OriginalFile;
import pl.conmir.cararchive.domain.performance.Performance;
import pl.conmir.cararchive.domain.performanceModification.PerformanceModification;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="original_file_id")
    private OriginalFile originalFile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="car_id")
    private Set<ModificationFile> modificationFiles;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="performance_id")
    private Performance performance;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="performance_modification_id")
    private PerformanceModification performanceModification;

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

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public void removePerformance(){
        this.performance = null;
    }

    public void setPerformanceModification(PerformanceModification performance) {
        this.performanceModification = performance;
    }

    public void removePerformanceModification(){
        this.performanceModification = null;
    }

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }


}

