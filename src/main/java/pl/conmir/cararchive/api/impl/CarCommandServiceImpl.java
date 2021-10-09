package pl.conmir.cararchive.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.api.CarCommandService;
import pl.conmir.cararchive.domain.*;
import pl.conmir.cararchive.domain.CarRepository;
import pl.conmir.cararchive.api.validator.CarValidator;
import pl.conmir.cararchive.domain.performance.Performance;
import pl.conmir.cararchive.domain.performance.Power;
import pl.conmir.cararchive.domain.performance.Torque;
import pl.conmir.cararchive.domain.performanceModification.PerformanceModification;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.dto.CreatePerformanceDto;
import pl.conmir.cararchive.dto.CreatePerformanceModificationDto;
import pl.conmir.cararchive.dto.UpdateCarDto;
import pl.conmir.cararchive.exception.ResourseNotFoundException;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CarCommandServiceImpl implements CarCommandService {

    private final CarRepository repository;
    private final CarFactory carFactory;
    private final OriginalFileFactory originalFileFactory;
    private final ModificationFileFactory modificationFileFactory;
    private final CarValidator carValidator;



    @Override
    public void save(CreateCarDto request) {
        carValidator.validate(request);

        var make = Make.of(request.getMake());
        var model = Model.of(request.getModel());
        var productionYear = ProductionYear.of(request.getYear());
        var registrationNumber = RegistrationNumber.of(request.getRegistration());
        var originalFile = request.getOriginalFile();
        var modificationFiles = request.getModificationFiles();

        var car = carFactory.create(make, model, productionYear, registrationNumber, originalFile, modificationFiles);

        var performance = request.getPerformance();
        if (doesContainPerformance(performance)) {
            //TODO: add validation
            Performance createdPerformance = createPerformance(performance);
            car.setPerformance(createdPerformance);
        }

        var performanceModification = request.getPerformanceModification();
        if (doesContainPerformanceModification(performanceModification)) {
            //TODO: add validation
            PerformanceModification createdPerformance = createPerformanceModification(performanceModification);
            car.setPerformanceModification(createdPerformance);
        }

        repository.save(car);
    }




    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long carId, UpdateCarDto request) {

        //TODO: add validation for update dto

        Car car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("File with id:" + carId + "not found");
                });

        var make = Make.of(request.getMake());
        var model = Model.of(request.getModel());
        var productionYear = ProductionYear.of(request.getYear());
        var registrationNumber = RegistrationNumber.of(request.getRegistration());

        car.changeMake(make);
        car.changeModel(model);
        car.changeRegistration(registrationNumber);
        car.changeProductionYear(productionYear);
    }

    @Override
    public void setOriginalFile(Long carId, MultipartFile file) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("File with id: " + carId + " not found!");
                });
        var modifiedFile = originalFileFactory.createFile(file);

        car.setOriginalModificationFile(modifiedFile);
    }


    @Override
    public void removeOriginalModificationFile(Long carId) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("Car with id: " + carId + " not found!");
                });

        car.removeOriginalModificationFile();
    }

    @Override
    public void addModificationFile(Long carId, MultipartFile file) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("Car with id: " + carId + " not found!");
                });

        var modifiedFile = modificationFileFactory.createFile(file);

        car.addModifiedFile(modifiedFile);
    }

    @Override
    public void removeModificationFile(Long carId, Long fileId) {

            var car = repository.findById(carId)
                    .orElseThrow(() -> {
                        throw new ResourseNotFoundException("Car with id: " + carId + " not found!");
                    });

            var fileToRemove = repository.findByModifiedFileId(carId, fileId)
                    .orElseThrow(() -> {
                        throw new ResourseNotFoundException("File with id: " + fileId + " not found!");
                    });

            car.removeModifiedFile(fileToRemove);

    }

    private Performance createPerformance(CreatePerformanceDto performance) {
        var power = Power.of(performance.getPower());
        var torque = Torque.of(performance.getTorque());

        return Performance.builder()
                .power(power)
                .torque(torque)
                .build();
    }

    private PerformanceModification createPerformanceModification(CreatePerformanceModificationDto performanceModification) {
        var power = Power.of(performanceModification.getPowerAfterModification());
        var torque = Torque.of(performanceModification.getTorqueAfterModification());

        return PerformanceModification.builder()
                .powerAfter(power)
                .torqueAfter(torque)
                .build();
    }

    private boolean doesContainPerformanceModification(CreatePerformanceModificationDto performanceModification) {
        return performanceModification != null;
    }

    private boolean doesContainPerformance(CreatePerformanceDto performance) {
        return performance != null;
    }



}
