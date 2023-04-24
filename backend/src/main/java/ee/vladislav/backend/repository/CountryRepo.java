package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, String> {
}
