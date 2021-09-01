package pl.conmir.cararchive.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.conmir.cararchive.car.domain.Car;
import pl.conmir.cararchive.modificationFile.ModificationFile;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car p join p.modificationFiles c where p.id = :carId and c.id = :modificationFileId ")
    Optional<ModificationFile> findByModifiedFileId(@Param("carId") Long carId, @Param("modificationFileId")Long fileId);

//    Optional<ModifiedFile> findByModifiedFilesId(Long id);


}

