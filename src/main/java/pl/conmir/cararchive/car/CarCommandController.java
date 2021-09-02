package pl.conmir.cararchive.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.car.dto.CreateCarDto;
import pl.conmir.cararchive.car.dto.UpdateCarDto;
import pl.conmir.cararchive.car.service.CarCommandService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/command/car")
public class CarCommandController {

    private final CarCommandService carCommandService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateCarDto request) {
        carCommandService.save(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/")
    public ResponseEntity<?> save(@PathVariable Long id ,@RequestBody UpdateCarDto request) {
        carCommandService.update(id, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable Long id) {
        carCommandService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{carId}/upload")
    public ResponseEntity uploadFile(@PathVariable Long carId, @RequestParam("file") MultipartFile file) {


        carCommandService.addModificationFile(carId, file);
            return ResponseEntity.status(HttpStatus.OK).build();

    }




}
