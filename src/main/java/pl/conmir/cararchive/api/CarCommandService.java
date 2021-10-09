package pl.conmir.cararchive.api;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.dto.UpdateCarDto;

@Service
public interface CarCommandService {

    void save(CreateCarDto request);
    void delete(Long id);
    void update(Long id, UpdateCarDto request);

    void setOriginalFile(Long carId, MultipartFile file);
    void removeOriginalModificationFile(Long carId);

    void addModificationFile(Long carIdd, MultipartFile file);
    void removeModificationFile(Long carId, Long fileId);

}
