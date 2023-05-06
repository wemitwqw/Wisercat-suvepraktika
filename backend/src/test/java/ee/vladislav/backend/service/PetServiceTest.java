package ee.vladislav.backend.service;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.mapper.PetDTOMapper;
import ee.vladislav.backend.model.*;
import ee.vladislav.backend.repository.PetRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepo petRepository;

    @Mock
    private PetDTOMapper petDTOMapper;

    @Mock
    private SelectorsService selectorsService;

    @Mock
    private UserService userService;

    @InjectMocks
    private PetService petService;

    @Test
    void testGetAllPetsByUserName() {
        String userName = "user1";

        List<Pet> pets = Arrays.asList(new Pet(), new Pet());

        when(petRepository.getPetsByAddedBy_Username(userName)).thenReturn(pets);
        when(petDTOMapper.entityToDto(any())).thenReturn(new PetDTO());

        List<PetDTO> result = petService.getAllPetsByUserName(userName);

        assertEquals(2, result.size());
    }

    @Test
    void testGetPetById() {
        AnimalType type = new AnimalType(1L, "cat");
        FurColor color = new FurColor(1L, "white");
        Country country = new Country(1L, "Estonia");
        User user = new User("user1", "password", true);

        Pet pet = new Pet(1L, "Ditto", "12345678", type, color, country, user);

        PetRepo petRepo = mock(PetRepo.class);
        when(petRepo.findPetByIdAndAddedBy_Username("1", "user")).thenReturn(Optional.of(pet));

        PetService petService = new PetService(petRepo, new PetDTOMapper(), mock(SelectorsService.class), mock(UserService.class));

        PetDTO result = petService.getById("1", "user");

        assertNotNull(result);
        assertEquals(result.getId(), 1);
        assertEquals(result.getName(), "Ditto");
        assertEquals(result.getCode(), "12345678");
        assertEquals(result.getAnimalType(), "cat");
        assertEquals(result.getFurColor(), "white");
        assertEquals(result.getCountry(), "Estonia");
    }

    @Test
    public void testAddPet() {

        PetDTO petDTO = new PetDTO("Ditto", "12345678", "dog", "orange", "Estonia");
        String userName = "user1";

        AnimalType animalType = new AnimalType(1L, "dog");
        FurColor furColor = new FurColor(1L, "orange");
        Country country = new Country(1L, "Estonia");
        User user = new User("user1", "password", true);

        Pet savedPet = new Pet(1L, petDTO.getName(), petDTO.getCode(), animalType, furColor, country, user);

        PetDTO expectedPetDTO = new PetDTO(1L, petDTO.getName(), petDTO.getCode(), animalType.getType(), furColor.getColor(), country.getCountry());

        Mockito.when(petRepository.existsPetByCode(petDTO.getCode())).thenReturn(false);
        Mockito.when(selectorsService.getAnimalTypeByType(petDTO.getAnimalType())).thenReturn(animalType);
        Mockito.when(selectorsService.getFurColorByColor(petDTO.getFurColor())).thenReturn(furColor);
        Mockito.when(selectorsService.getCountryByCountry(petDTO.getCountry())).thenReturn(country);
        Mockito.when(userService.getUserByUserName(userName)).thenReturn(user);
        Mockito.when(petRepository.save(Mockito.any(Pet.class))).thenReturn(savedPet);
        Mockito.when(petDTOMapper.entityToDto(savedPet)).thenReturn(expectedPetDTO);

        PetDTO resultPetDTO = petService.addPet(petDTO, userName);

        verify(petRepository).existsPetByCode(petDTO.getCode());
        verify(selectorsService).getAnimalTypeByType(petDTO.getAnimalType());
        verify(selectorsService).getFurColorByColor(petDTO.getFurColor());
        verify(selectorsService).getCountryByCountry(petDTO.getCountry());
        verify(userService).getUserByUserName(userName);
        verify(petDTOMapper).entityToDto(savedPet);
        assertEquals(expectedPetDTO, resultPetDTO);
    }

    @Test
    void testEditPetWhenPetExists() {

        String id = "1";
        PetDTO petDTO = new PetDTO("New Pet", "12345678", "dog", "orange", "Estonia");
        String userName = "user1";

        AnimalType animalType = new AnimalType(1L, "dog");
        FurColor furColor = new FurColor(1L, "orange");
        Country country = new Country(1L, "Estonia");
        User user = new User("user1", "password", true);
        Pet petFromDb = new Pet(1L, "Old Pet", petDTO.getCode(), animalType, furColor, country, user);

        Pet updatedPet = new Pet(1L, petDTO.getName(), petDTO.getCode(), animalType, furColor, country, user);

        when(petRepository.findPetByIdAndAddedBy_Username(id, userName)).thenReturn(Optional.of(petFromDb));
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        PetDTO result = petService.editPet(id, petDTO, userName);

        verify(petRepository).findPetByIdAndAddedBy_Username(id, userName);
        verify(petRepository).save(any(Pet.class));
        assertNotNull(result);
        assertEquals(result.getName(), petDTO.getName());
        assertEquals(result.getCode(), petDTO.getCode());
        assertEquals(result.getAnimalType(), petDTO.getAnimalType());
        assertEquals(result.getFurColor(), petDTO.getFurColor());
        assertEquals(result.getCountry(), petDTO.getCountry());
    }

}