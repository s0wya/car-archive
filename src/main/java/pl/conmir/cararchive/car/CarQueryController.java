package pl.conmir.cararchive.car;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.conmir.cararchive.car.service.CarQueryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/query/car")
public class CarQueryController {

    private final CarQueryService service;

//    @GetMapping("{carId}/files/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable Long carId, @PathVariable Long id) {
//        var fileDB = service.findByModificationId(carId, id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData().getValue());
//    }

//    @GetMapping("{carId}/files/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable Long carId, @PathVariable Long id) {
//        var fileDB = service.findByModifiedFilesId(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName().getValue() + "\"")
//                .body(fileDB.getData().getValue());
//    }

}
