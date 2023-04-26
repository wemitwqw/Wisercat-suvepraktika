package ee.vladislav.backend.service;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exceptions.*;
import ee.vladislav.backend.mapper.PetDTOMapper;
import ee.vladislav.backend.model.*;
import ee.vladislav.backend.repository.AnimalTypeRepo;
import ee.vladislav.backend.repository.CountryRepo;
import ee.vladislav.backend.repository.FurColorRepo;
import ee.vladislav.backend.repository.PetRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepo petRepository;
    private final PetDTOMapper petDTOMapper;
    private final SelectorsService selectorsService;
    private final UserService userService;

    public PetService(PetRepo petRepository, PetDTOMapper petDTOMapper,
                      SelectorsService selectorsService, UserService userService) {
        this.petRepository = petRepository;
        this.petDTOMapper = petDTOMapper;
        this.selectorsService = selectorsService;
        this.userService = userService;
    }

//    public List<PetDTO> getAllPets() {
//        return petRepository.findAll()
//                .stream()
//                .map(petDTOMapper::entityToDto)
//                .collect(Collectors.toList());
//    }

    public List<PetDTO> getAllPetsByUserName(String userName) {
        return petRepository.findAll()
                .stream()
                .map(petDTOMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public PetDTO getById(String id) {

        return petRepository.findById(id).map(petDTOMapper::entityToDto).orElse(null);
    }

    public PetDTO addPet(PetDTO petDTO) {
        if (petRepository.existsPetByCode(petDTO.getCode())) {throw new PetCodeAlreadyExistsException(); }

        AnimalType type = getAnimalType(petDTO.getAnimalType());
        FurColor color = getFurColor(petDTO.getFurColor());
        Country country = getCountry(petDTO.getCountry());
        User user = getUser(petDTO.getAddedBy());

        Pet savedPet = petRepository.save(new Pet(null, petDTO.getName(), petDTO.getCode(), type, color, country, user));

        return petDTOMapper.entityToDto(savedPet);
    }
    public PetDTO editPet(String id, PetDTO petDTO) {

        Pet petFromDb = petRepository.findById(id).orElse(null);
        if (petFromDb == null) { throw new PetNotFoundException(); }

        petFromDb.setName(petDTO.getName());
        petFromDb.setCode(petDTO.getCode());

        AnimalType type = getAnimalType(petDTO.getAnimalType());
        FurColor color = getFurColor(petDTO.getFurColor());
        Country country = getCountry(petDTO.getCountry());
        User user = getUser(petDTO.getAddedBy());

        petFromDb.setAnimalType(type);
        petFromDb.setFurColor(color);
        petFromDb.setCountry(country);
        petFromDb.setAddedBy(user);

        Pet savedPet = petRepository.save(petFromDb);

        return new PetDTO(savedPet.getId(),
                savedPet.getName(),
                savedPet.getCode(),
                savedPet.getAnimalType().getType(),
                savedPet.getFurColor().getColor(),
                savedPet.getCountry().getCountry(),
                savedPet.getAddedBy().getUsername()
        );
    }

    private AnimalType getAnimalType(String type) {

        return selectorsService.getAnimalTypeByType(type.toLowerCase());
    }

    private FurColor getFurColor(String color) {

        return selectorsService.getFurColorByColor(color.toLowerCase());
    }

    private Country getCountry(String country) {

        return selectorsService.getCountryByCountry(country);
    }

    private User getUser(String userName) {
        return userService.getUserByUsername(userName);
    }
}
