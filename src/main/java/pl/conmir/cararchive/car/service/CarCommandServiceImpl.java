package pl.conmir.cararchive.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.car.CarFactory;
import pl.conmir.cararchive.car.CarRepository;
import pl.conmir.cararchive.car.Validator;
import pl.conmir.cararchive.car.domain.*;
import pl.conmir.cararchive.car.dto.CreateCarDto;
import pl.conmir.cararchive.car.dto.UpdateCarDto;
import pl.conmir.cararchive.exception.FileStorageException;
import pl.conmir.cararchive.exception.ResourseNotFoundException;
import pl.conmir.cararchive.modificationFile.ModificationFile;
import pl.conmir.cararchive.modificationFile.ModificationFileData;
import pl.conmir.cararchive.modificationFile.ModificationFileName;
import pl.conmir.cararchive.originalFile.FileData;
import pl.conmir.cararchive.originalFile.FileName;
import pl.conmir.cararchive.originalFile.OriginalFile;
import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@Service
@RequiredArgsConstructor
public class CarCommandServiceImpl implements CarCommandService {

    private final CarRepository repository;
    private final CarFactory carFactory;
    private final Validator validator;


    @Override
    public void save(CreateCarDto request) {
        validator.validate(request);

        var make = Make.of(request.getMake());
        var model = Model.of(request.getModel());
        var productionYear = ProductionYear.of(request.getYear());
        var registrationNumber = RegistrationNumber.of(request.getRegistration());

        var car = carFactory.create(make, model, productionYear, registrationNumber);
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
                    throw new ResourseNotFoundException(404, "File with id:" + carId + "not found");
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
                    throw new ResourseNotFoundException(404, "File with id: " + carId + " not found!");
                });
        var modifiedFile = createOriginalFile(file);

        car.setOriginalModificationFile(modifiedFile);
    }


    @Override
    public void removeOriginalModificationFile(Long carId) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException(404, "Car with id: " + carId + " not found!");
                });

        car.removeOriginalModificationFile();
    }

    @Override
    public void addModificationFile(Long carId, MultipartFile file) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException(404, "Car with id: " + carId + " not found!");
                });

        var modifiedFile = createModifiedFile(file);

        car.addModifiedFile(modifiedFile);
    }

    @Override
    public void removeModificationFile(Long carId, Long fileId) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException(404, "Car with id: " + carId + " not found!");
                });

        var fileToRemove = repository.findByModifiedFileId(carId, fileId)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException(404, "File with id: " + fileId + " not found!");
                });


        car.removeModifiedFile(fileToRemove);
    }

    private OriginalFile createOriginalFile(MultipartFile file) {
        try {
            return OriginalFile.builder()
                    .name(FileName.of(StringUtils.cleanPath(file.getOriginalFilename())))
                    .type(file.getContentType())
                    .data(FileData.of(file.getBytes()))
                    .build();
        } catch (IOException ex) {
            throw new FileStorageException(500, "Could not store file. " + ex);
        }
    }

    private ModificationFile createModifiedFile(MultipartFile file) {
        try {
            return ModificationFile.builder()
                    .name(ModificationFileName.of(StringUtils.cleanPath(file.getOriginalFilename())))
                    .type(file.getContentType())
                    .data(ModificationFileData.of(file.getBytes()))
                    .build();
        } catch (IOException ex) {
            throw new FileStorageException(500, "Could not store file. " + ex);
        }
    }
}
