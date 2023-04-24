package ee.vladislav.backend.service;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exception.*;
import ee.vladislav.backend.mapper.PetDTOMapper;
import ee.vladislav.backend.model.AnimalType;
import ee.vladislav.backend.model.Country;
import ee.vladislav.backend.model.FurColor;
import ee.vladislav.backend.model.Pet;
import ee.vladislav.backend.repository.AnimalTypeRepo;
import ee.vladislav.backend.repository.CountryRepo;
import ee.vladislav.backend.repository.FurColorRepo;
import ee.vladislav.backend.repository.PetRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepo petRepository;
    private final PetDTOMapper petDTOMapper;
    private final AnimalTypeRepo animalTypeRepo;
    private final CountryRepo countryRepo;
    private final FurColorRepo furColorRepo;

    public PetService(PetRepo petRepository, PetDTOMapper petDTOMapper,
                      AnimalTypeRepo animalTypeRepo, CountryRepo countryRepo,
                      FurColorRepo furColorRepo) {
        this.petRepository = petRepository;
        this.petDTOMapper = petDTOMapper;
        this.animalTypeRepo = animalTypeRepo;
        this.countryRepo = countryRepo;
        this.furColorRepo = furColorRepo;
    }

    public List<PetDTO> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(petDTOMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Optional<PetDTO> getById(String id) throws PetNotFoundException {
        Optional<PetDTO> pet = petRepository.findById(id).map(petDTOMapper::entityToDto);
        if (pet.isEmpty()) {
            throw new PetNotFoundException();
        }

        return pet;
    }

    public PetDTO addPet(PetDTO petDTO) {
        if (petRepository.existsPetByCode(petDTO.code())) {throw new PetCodeAlreadyExistsException(); }

        AnimalType type = animalTypeRepo.findAnimalTypeByType(petDTO.animalType().toLowerCase());
        if (type == null) { throw new AnimalTypeNotAcceptedException(); }

        FurColor color = furColorRepo.findFurColorByColor(petDTO.furColor().toLowerCase());
        if (color == null) { throw new FurColorNotAcceptedException(); }

        Country country = countryRepo.findCountryByCountry(petDTO.country());
        if (country == null) { throw new CountryNotAcceptedException(); }

        Pet savedPet = petRepository.save(new Pet(null, petDTO.name(), petDTO.code(), type, color, country));
        if (savedPet.getId() == null) {
            throw new PetNotAddedException();
        }

        return petDTOMapper.entityToDto(savedPet);
    }
    public void editPet(String id, Pet pet) {
        Pet petFromDb = petRepository.findById(id).get();
        petFromDb.setName(pet.getName());
        petFromDb.setCode(pet.getCode());
        petFromDb.setAnimalType(pet.getAnimalType());
        petFromDb.setFurColor(pet.getFurColor());
        petFromDb.setCountry(pet.getCountry());
        petRepository.save(petFromDb);
    }
}
