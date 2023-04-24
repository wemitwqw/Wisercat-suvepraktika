package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.AnimalType;
import ee.vladislav.backend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepo extends JpaRepository<Pet, String> {
    Boolean existsPetByCode(String code);
}
