package pl.conmir.cararchive.api.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.conmir.cararchive.api.CarQueryService;
import pl.conmir.cararchive.domain.performance.Performance;
import pl.conmir.cararchive.dto.*;
import pl.conmir.cararchive.domain.CarRepository;
import pl.conmir.cararchive.domain.Car;
import pl.conmir.cararchive.exception.ResourseNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CarQueryServiceImpl implements CarQueryService {

    private final CarRepository repository;

    @Override
    public GetCarDto findCarById(Long carId) {
        return repository.findById(carId)
                .map(CarMapper::toCarResponse)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("Car with id: " + carId + " cannot be found!");
                });
    }

    @Override
    public GetOriginalFile findOriginalFileByCarId(Long carId) {
        return repository.findById(carId)
                .map(Car::getOriginalFile)
                .map(OriginalFileMapper::toOriginalFileResponse)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("There is no file for car:" + carId);
                });
    }

    @Override
    public GetModificationFile findByModificationId(Long carId, Long fileId) {
        return repository.findByModifiedFileId(carId, fileId)
                .map(ModificationFileMapper::toModificationFileResponse)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("There is no file");
                });
    }

    @Override
    public GetCarCount findCountForCarId(Long id) {
        long count = repository.countById(id);
        return CarMapper.toGetCarCount(id, count);
    }

    @Override
    public GetCarDto findCarByRegistration(String registration) {
        return repository.findByRegistration_Value(registration)
                .map(CarMapper::toCarResponse)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("There is no car for registration number: " + registration);
                });
    }

    @Override
    public GetPerformanceDto findCarPerformance(Long carId) {
        return repository.findPerformanceByCarId(carId)
                .map(PerformanceMapper::toGetPerformanceDto)
                .orElseThrow(() -> {
                    throw new ResourseNotFoundException("There is no performance for car with id: " + carId);
                });

    }

    private PageRequest buildPageRequest(FindCarQuery query) {
        return PageRequest.of(
                query.getPageNumber(),
                query.getPageSize(),
                Sort.by(query.getOrderDirection(), query.getOrderBy())
        );
    }
}

