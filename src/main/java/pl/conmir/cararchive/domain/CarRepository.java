package pl.conmir.cararchive.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.conmir.cararchive.domain.Car;
import pl.conmir.cararchive.domain.modificationFile.ModificationFile;
import pl.conmir.cararchive.domain.performance.Performance;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    long countById(long id);

    List<Car> findByRegistration_ValueContaining(String registration);

    @Query("select c from Car p join p.modificationFiles c where p.id = :carId and c.id = :modificationFileId ")
    Optional<ModificationFile> findByModifiedFileId(@Param("carId") Long carId, @Param("modificationFileId")Long fileId);

    @Query("select c from Car p join p.performance c where p.id = :carId and c.id = :carId ")
    Optional<Performance> findPerformanceByCarId(@Param("carId") Long carId);

}

