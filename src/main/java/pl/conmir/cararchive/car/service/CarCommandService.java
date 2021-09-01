package pl.conmir.cararchive.car.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.car.dto.CreateCarDto;

@Service
public interface CarCommandService {

    void save(CreateCarDto request);
    void delete(Long id);
    void update(Long id, CreateCarDto request);

    void setOriginalFile(Long carId, MultipartFile file);
    void removeOriginalModificationFile(Long carId);

    void addModifiedFile(Long carIdd, MultipartFile file);
    void removeModifiedFile(Long carId, Long fileId);

}
