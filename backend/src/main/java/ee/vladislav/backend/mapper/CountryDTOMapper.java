package ee.vladislav.backend.mapper;

import ee.vladislav.backend.dto.CountryDTO;
import ee.vladislav.backend.model.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryDTOMapper {
    public CountryDTO entityToDto(Country country) {
        return new CountryDTO(country.getCountry());
    }
}
