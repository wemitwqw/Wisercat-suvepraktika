package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepo extends JpaRepository<Pet, String> {
    Boolean existsPetByCode(String code);

    List<Pet> getPetsByAddedBy_Username(String userName);

    Optional<Pet> findPetByIdAndAddedBy_Username(String id, String userName);

}
