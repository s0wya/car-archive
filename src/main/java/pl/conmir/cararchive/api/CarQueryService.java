package pl.conmir.cararchive.api;


import org.springframework.stereotype.Service;
import pl.conmir.cararchive.dto.*;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CarQueryService {

    GetCarDto findCarById(Long carId);
    GetOriginalFile findOriginalFileByCarId(Long carId);
    GetModificationFile findByModificationId(Long carId, Long fileId);
    GetCarCount findCountForCarId(Long id);
    List<GetCarDto> findCarByRegistration(String registration);
    GetPerformanceDto findCarPerformance(Long carId);
//    List<GetCarDto> find(FindCarQuery query);

}
