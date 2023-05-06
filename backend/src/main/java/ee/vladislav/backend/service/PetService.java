package ee.vladislav.backend.service;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exceptions.*;
import ee.vladislav.backend.mapper.PetDTOMapper;
import ee.vladislav.backend.model.*;
import ee.vladislav.backend.repository.PetRepo;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<PetDTO> getAllPetsByUserName(String userName) {
        return petRepository.getPetsByAddedBy_Username(userName)
                .stream()
                .map(petDTOMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public PetDTO getById(String id, String userName){

        return petRepository.findPetByIdAndAddedBy_Username(id, userName).map(petDTOMapper::entityToDto).orElse(null);
    }

    public PetDTO addPet(PetDTO petDTO, String userName) {
        if (petRepository.existsPetByCode(petDTO.getCode())) {throw new PetCodeAlreadyExistsException(); }

        AnimalType type = getAnimalType(petDTO.getAnimalType());
        FurColor color = getFurColor(petDTO.getFurColor());
        Country country = getCountry(petDTO.getCountry());
        User user = getUser(userName);

        Pet petToSave = new Pet(null, petDTO.getName(), petDTO.getCode(), type, color, country, user);

        Pet savedPet;
        try {
            savedPet = petRepository.save(petToSave);
        } catch (DataIntegrityViolationException e) {
            throw new PetNotUpdatedException("Code must be unique!", e.getCause());
        }

        return petDTOMapper.entityToDto(savedPet);
    }
    public PetDTO editPet(String id, PetDTO petDTO, String userName) {

        Pet petFromDb = petRepository.findPetByIdAndAddedBy_Username(id, userName).orElse(null);
        if (petFromDb == null) { throw new PetNotFoundException(); }

        petFromDb.setName(petDTO.getName());
        petFromDb.setCode(petDTO.getCode());

        AnimalType type = getAnimalType(petDTO.getAnimalType());
        FurColor color = getFurColor(petDTO.getFurColor());
        Country country = getCountry(petDTO.getCountry());
        User user = getUser(userName);

        petFromDb.setAnimalType(type);
        petFromDb.setFurColor(color);
        petFromDb.setCountry(country);
        petFromDb.setAddedBy(user);

        Pet savedPet;
        try {
            savedPet = petRepository.save(petFromDb);
        } catch (DataIntegrityViolationException e) {
            throw new PetNotUpdatedException("Code must be unique!", e.getCause());
        }

        return new PetDTO(savedPet.getId(),
                savedPet.getName(),
                savedPet.getCode(),
                savedPet.getAnimalType().getType(),
                savedPet.getFurColor().getColor(),
                savedPet.getCountry().getCountry()
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

        return userService.getUserByUserName(userName);
    }
}
