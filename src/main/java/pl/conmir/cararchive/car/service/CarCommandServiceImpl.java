package pl.conmir.cararchive.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.car.CarFactory;
import pl.conmir.cararchive.car.CarMapper;
import pl.conmir.cararchive.car.CarRepository;
import pl.conmir.cararchive.car.domain.*;
import pl.conmir.cararchive.car.dto.CreateCarDto;
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


    @Override
    public void save(CreateCarDto request) {
        //TODO -> Add validation to request
        /*
            boolean isCorrect = validator.validate(CarRaquest req)
         */
//        validator.validate(request); // ValidationException -> 400, errorCode, message

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
    public void update(Long carId, CreateCarDto request) {
        //TODO -> Add validation to request
        Car car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException();
                });

        //TODO: change to factory
        Car mappedRequest = CarMapper.fromCreateCarDto(request);
        car.update(mappedRequest);
    }

    @Override
    public void setOriginalFile(Long carId, MultipartFile file) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("File with id: " + carId + " not found!");
                });
        var modifiedFile = createOriginalFile(file);

        car.setOriginalModificationFile(modifiedFile);
    }


    @Override
    public void removeOriginalModificationFile(Long carId) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Car with id: " + carId + " not found!");
                });

        car.removeOriginalModificationFile();
    }

    @Override
    public void addModifiedFile(Long carId, MultipartFile file) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Car with id: " + carId + " not found!");
                });

        var modifiedFile = createModifiedFile(file);

        car.addModifiedFile(modifiedFile);
    }

    @Override
    public void removeModifiedFile(Long carId, Long fileId) {
        var car = repository.findById(carId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Car with carId: " + carId + " not found!");
                });

        var fileToRemove = repository.findByModifiedFileId(carId, fileId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("File with carId: " + fileId + " not found!");
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
            throw new IllegalStateException("Could not store file. " + ex);
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
            throw new IllegalStateException("Could not store file. " + ex);
        }
    }
}
