package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country, String> {
    Country findCountryByCountry(String country);
}
