package ee.vladislav.backend.Repository;

import ee.vladislav.backend.Model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepo extends JpaRepository<Pet, String> {

}
