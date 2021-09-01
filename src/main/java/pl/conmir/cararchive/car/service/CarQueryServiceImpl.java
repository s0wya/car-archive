package pl.conmir.cararchive.car.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.conmir.cararchive.car.CarMapper;
import pl.conmir.cararchive.car.CarRepository;
import pl.conmir.cararchive.car.CarResponse;
import pl.conmir.cararchive.car.domain.Car;
import pl.conmir.cararchive.modificationFile.ModificationFile;
import pl.conmir.cararchive.modificationFile.ModificationFileMapper;
import pl.conmir.cararchive.modificationFile.ModificationFileResponse;
import pl.conmir.cararchive.originalFile.OriginalFileMapper;
import pl.conmir.cararchive.originalFile.OriginalFileResponse;

@Service
@RequiredArgsConstructor
public class CarQueryServiceImpl implements CarQueryService {

    private final CarRepository repository;

    @Override
    public CarResponse findCarById(Long carId) {
        return repository.findById(carId)
                .map(CarMapper::toCarResponse)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Car with id: " + carId + " cannot be found!");
                });
    }

    @Override
    public OriginalFileResponse findOriginalFileByCarId(Long carId) {
        return repository.findById(carId)
                .map(Car::getOriginalFile)
                .map(OriginalFileMapper::toOriginalFileResponse)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("There is no file for car:" + carId);
                });
    }

    @Override
    public ModificationFileResponse findByModificationId(Long carId, Long fileId) {
        return repository.findByModifiedFileId(carId, fileId)
                .map(ModificationFileMapper::toModificationFileResponse)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("There is no file");
                });
    }


//    //ONE TO MANY <- modfiedfiles
//    public ModifiedFile findModifiedFileById(Long carId, String fileId) {
//        /*
//
//            var file =  carRepository.findByFileId(fileId); // Select by id
//            var car = carrepository.findCarByHistoryFile(file); // select by id
//
//
//            car.getFiless.stream(0.find(t -> id.getid == id).findFirst().get() ->
//
//            car_table -> file_table (id, car_id)
//
//            Car
//                originFileId
//                fileHistory (resourceId)
//
//                var filecontent = dataService.get(resourceId);
//
//            1.
//            +reposutorium do modifiedfile
//            ModifiedFile -> findbyid(String id);
//            car -> originalFile
//            file.car.getOwner();
//
//            2.
//            Car -> files[]
//
//
//            repositry orifinal file -> znalezc po id <-
//         */
//
////        //szukac przez car czy bezposrednio stworzyc repozytorium dla file i po prostu po nim.
////        Car car = repository.findById(id)
////                .orElseThrow(() -> {
////                    throw new IllegalArgumentException("There is no car with id: " + id);
////                });
////        return;
//    }
}
