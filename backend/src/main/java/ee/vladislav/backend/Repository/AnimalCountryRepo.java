package ee.vladislav.backend.Repository;

import ee.vladislav.backend.Model.AnimalCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalCountryRepo extends JpaRepository<AnimalCountry, String> {
}
