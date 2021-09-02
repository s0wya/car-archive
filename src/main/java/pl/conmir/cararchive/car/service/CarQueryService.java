package pl.conmir.cararchive.car.service;


import org.springframework.stereotype.Service;
import pl.conmir.cararchive.car.CarResponse;
import pl.conmir.cararchive.modificationFile.dto.ModificationFileResponse;
import pl.conmir.cararchive.originalFile.dto.OriginalFileResponse;

@Service
public interface CarQueryService {

    CarResponse findCarById(Long carId);
    OriginalFileResponse findOriginalFileByCarId(Long carId);
    ModificationFileResponse findByModificationId(Long carId, Long fileId);

}
