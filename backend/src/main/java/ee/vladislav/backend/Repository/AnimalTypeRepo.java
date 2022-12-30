package ee.vladislav.backend.Repository;

import ee.vladislav.backend.Model.AnimalType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepo extends JpaRepository<AnimalType, String> {

//    @Query("SELECT animal_types.id, animal_types.animal_type FROM animal_types WHERE animal_types.animal_type = ?1")
//    Long findByType(String type);
}
