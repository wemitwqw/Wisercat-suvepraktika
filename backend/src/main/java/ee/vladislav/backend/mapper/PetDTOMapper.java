package ee.vladislav.backend.mapper;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.model.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetDTOMapper {
    public PetDTO entityToDto(Pet pet) {
        return new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getCode(),
                pet.getAnimalType().getType(),
                pet.getFurColor().getColor(),
                pet.getCountry().getCountry()
        );
    }
}