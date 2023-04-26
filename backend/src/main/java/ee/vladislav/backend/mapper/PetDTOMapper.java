package ee.vladislav.backend.mapper;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.model.Pet;
import org.springframework.stereotype.Service;

//import java.util.function.Function;

@Service
public class PetDTOMapper {
    public PetDTO entityToDto(Pet pet) {
        return new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getCode(),
                pet.getAnimalType().getType(),
                pet.getFurColor().getColor(),
                pet.getCountry().getCountry(),
                pet.getAddedBy().getUsername()
        );
    }
}

//@Service
//public class PetDTOMapper implements Function<Pet, PetDTO> {
//    @Override
//    public PetDTO apply(Pet pet) {
//        return new PetDTO(
//                pet.getId(),
//                pet.getName(),
//                pet.getCode(),
//                pet.getAnimalType().getType(),
//                pet.getFurColor().getColor(),
//                pet.getCountry().getCountry()
//        );
//    }
//}
