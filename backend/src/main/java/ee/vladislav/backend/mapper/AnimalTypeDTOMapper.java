package ee.vladislav.backend.mapper;

import ee.vladislav.backend.dto.AnimalTypeDTO;
import ee.vladislav.backend.model.AnimalType;
import org.springframework.stereotype.Service;
@Service
public class AnimalTypeDTOMapper {
    public AnimalTypeDTO entityToDto(AnimalType animalType) {
        return new AnimalTypeDTO(animalType.getType());
    }
}
