package pl.conmir.cararchive.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.dto.GetCarDto;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.dto.GetPerformanceDto;
import pl.conmir.cararchive.dto.UpdateCarDto;
import pl.conmir.cararchive.api.CarCommandService;
import pl.conmir.cararchive.api.CarQueryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarCommandService carCommandService;
    private final CarQueryService carQueryService;

    @PostMapping
    public ResponseEntity<Object> saveCar(@RequestBody CreateCarDto request) {
        carCommandService.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}/")
    public ResponseEntity<?> updateCar(@PathVariable Long id ,@RequestBody UpdateCarDto request) {
        carCommandService.update(id, request);

        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carCommandService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{carId}")
    public ResponseEntity<GetCarDto> getCar(@PathVariable Long carId){
        var car = carQueryService.findCarById(carId);

        return ResponseEntity.ok()
                .body(car);
    }

    @PostMapping("/{carId}/modification-file/upload")
    public ResponseEntity<?> uploadModificationFile(@PathVariable Long carId, @RequestParam("file") MultipartFile file) {
        carCommandService.addModificationFile(carId, file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{carId}/modification-file/{fileId}")
    public ResponseEntity<?> deleteModificationFile(@PathVariable Long carId, @PathVariable Long fileId) {
        carCommandService.removeModificationFile(carId, fileId);

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{carId}/modification-file/{fileId}/download")
    public ResponseEntity<Resource> getModificationFile(@PathVariable Long carId, @PathVariable Long fileId){
        var file = carQueryService.findByModificationId(carId, fileId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\""
                )
                .body(
                        new ByteArrayResource(file.getData())
                );
    }

    @PostMapping("/{carId}/original-file/upload")
    public ResponseEntity<?> uploadOriginalFile(@PathVariable Long carId, @RequestPart("file") MultipartFile file) {
        carCommandService.setOriginalFile(carId, file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{carId}/original-file")
    public ResponseEntity<?> deleteOriginalFile(@PathVariable Long carId) {
        carCommandService.removeOriginalModificationFile(carId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{carId}/original-file/download")
    public ResponseEntity<Resource> getOriginalFile(@PathVariable Long carId){
        var file = carQueryService.findOriginalFileByCarId(carId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\""
                )
                .body(
                        new ByteArrayResource(file.getData())
                );
    }

    @GetMapping("/{carId}/performance")
    public ResponseEntity<GetPerformanceDto> getPerformance(@PathVariable Long carId){
        var performance = carQueryService.findCarPerformance(carId);

        return ResponseEntity.ok(performance);
    }

    @GetMapping("/registration")
    public ResponseEntity<List<GetCarDto>> getByRegistration(@RequestParam String value){
        var performance = carQueryService.findCarByRegistration(value);

        return ResponseEntity.ok(performance);
    }





}
