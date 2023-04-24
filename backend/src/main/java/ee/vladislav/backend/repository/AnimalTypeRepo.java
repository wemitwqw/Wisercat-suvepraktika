package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepo extends JpaRepository<AnimalType, String> {

//    @Query("SELECT animal_types.id, animal_types.animal_type FROM animal_types WHERE animal_types.animal_type = ?1")
//    Long findByType(String type);
}
