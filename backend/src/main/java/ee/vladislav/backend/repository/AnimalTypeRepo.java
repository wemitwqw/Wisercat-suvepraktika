package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepo extends JpaRepository<AnimalType, String> {
    AnimalType findAnimalTypeByType(String type);
}
