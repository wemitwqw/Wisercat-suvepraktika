package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.AnimalCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalCountryRepo extends JpaRepository<AnimalCountry, String> {
}
