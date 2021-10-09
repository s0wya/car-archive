package pl.conmir.cararchive.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@AllArgsConstructor
public class CarFactory {
    private final OriginalFileFactory originalFileFactory;
    private final ModificationFileFactory modificationFileFactory;


    public Car create(Make make, Model model, ProductionYear productionYear, RegistrationNumber registrationNumber,
                      MultipartFile originalFile, List<MultipartFile> modificationFiles) {


        var car = Car.builder()
                .make(make)
                .model(model)
                .year(productionYear)
                .registration(registrationNumber)
                .build();



        if (isOriginalFileCorrect(originalFile)) {
            var file = originalFileFactory.createFile(originalFile);
            car.setOriginalModificationFile(file);
        }

        if (!isModificationFileCorrect(modificationFiles)) {
            modificationFiles.stream()
                    .map(modificationFileFactory::createFile)
                    .forEach(car::addModifiedFile);
        }

        return car;
    }

    boolean isOriginalFileCorrect(MultipartFile file){
        return file != null;
    }

    boolean isModificationFileCorrect(List<MultipartFile> file){
        return file != null && !file.isEmpty();
    }

}
