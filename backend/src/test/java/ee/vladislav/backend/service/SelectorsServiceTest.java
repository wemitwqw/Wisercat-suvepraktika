package ee.vladislav.backend.service;

import ee.vladislav.backend.dto.AnimalTypeDTO;
import ee.vladislav.backend.dto.CountryDTO;
import ee.vladislav.backend.dto.FurColorDTO;
import ee.vladislav.backend.exceptions.AnimalTypeNotAcceptedException;
import ee.vladislav.backend.exceptions.CountryNotAcceptedException;
import ee.vladislav.backend.exceptions.FurColorNotAcceptedException;
import ee.vladislav.backend.mapper.AnimalTypeDTOMapper;
import ee.vladislav.backend.mapper.CountryDTOMapper;
import ee.vladislav.backend.mapper.FurColorDTOMapper;
import ee.vladislav.backend.model.AnimalType;
import ee.vladislav.backend.model.Country;
import ee.vladislav.backend.model.FurColor;
import ee.vladislav.backend.repository.AnimalTypeRepo;
import ee.vladislav.backend.repository.CountryRepo;
import ee.vladislav.backend.repository.FurColorRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SelectorsServiceTest {
    @Mock
    private AnimalTypeRepo animalTypeRepo;
    @Mock
    private FurColorRepo furColorRepo;
    @Mock
    private CountryRepo countryRepo;
    @Mock
    private FurColorDTOMapper furColorDTOMapper;
    @Mock
    private AnimalTypeDTOMapper animalTypeDTOMapper;
    @Mock
    private CountryDTOMapper countryDTOMapper;
    @InjectMocks
    private SelectorsService selectorsService;

    @Test
    void testGetAnimalTypeByType() {
        AnimalType animalType = new AnimalType();
        animalType.setType("dog");

        when(animalTypeRepo.findAnimalTypeByType("dog")).thenReturn(animalType);

        AnimalType result = selectorsService.getAnimalTypeByType("dog");

        assertEquals(animalType, result);
    }

    @Test
    void testGetAnimalTypeByTypeNotAcceptedException() {
        when(animalTypeRepo.findAnimalTypeByType("llama")).thenReturn(null);

        assertThrows(AnimalTypeNotAcceptedException.class, () -> selectorsService.getAnimalTypeByType("llama"));
    }

    @Test
    void testGetFurColorByColor() {
        FurColor furColor = new FurColor();
        furColor.setColor("white");

        when(furColorRepo.findFurColorByColor("white")).thenReturn(furColor);

        FurColor result = selectorsService.getFurColorByColor("white");

        assertEquals(furColor, result);
    }

    @Test
    void testGetFurColorByColorNotAcceptedException() {
        when(furColorRepo.findFurColorByColor("cyan")).thenReturn(null);

        assertThrows(FurColorNotAcceptedException.class, () -> selectorsService.getFurColorByColor("cyan"));
    }

    @Test
    void testGetCountryByCountry() {
        Country country = new Country();
        country.setCountry("Estonia");

        when(countryRepo.findCountryByCountry("Estonia")).thenReturn(country);

        Country result = selectorsService.getCountryByCountry("Estonia");

        assertEquals(country, result);
    }

    @Test
    void testGetCountryByCountryNotAcceptedException() {
        when(countryRepo.findCountryByCountry("USA")).thenReturn(null);

        assertThrows(CountryNotAcceptedException.class, () -> selectorsService.getCountryByCountry("USA"));
    }


    @Test
    void testGetAllSelectorData() {

        List<FurColor> furColors = Arrays.asList(new FurColor(1L, "white"), new FurColor(2L, "orange"));
        when(furColorRepo.findAll()).thenReturn(furColors);

        List<Country> countries = Arrays.asList(new Country(1L, "Estonia"), new Country(2L, "Finland"));
        when(countryRepo.findAll()).thenReturn(countries);

        List<AnimalType> animalTypes = Arrays.asList(new AnimalType(1L, "dog"), new AnimalType(2L, "cat"));
        when(animalTypeRepo.findAll()).thenReturn(animalTypes);

        when(furColorDTOMapper.entityToDto(any(FurColor.class))).thenAnswer(
                (Answer<FurColorDTO>) invocation -> {
                    FurColor entity = invocation.getArgument(0);
                    FurColorDTO dto = new FurColorDTO();
                    dto.setColor(entity.getColor());
                    return dto;
                });

        when(countryDTOMapper.entityToDto(any(Country.class))).thenAnswer(
                (Answer<CountryDTO>) invocation -> {
                    Country entity = invocation.getArgument(0);
                    CountryDTO dto = new CountryDTO();
                    dto.setCountry(entity.getCountry());
                    return dto;
                });

        when(animalTypeDTOMapper.entityToDto(any(AnimalType.class))).thenAnswer(
                (Answer<AnimalTypeDTO>) invocation -> {
                    AnimalType entity = invocation.getArgument(0);
                    AnimalTypeDTO dto = new AnimalTypeDTO();
                    dto.setType(entity.getType());
                    return dto;
                });

        Map<String, List<String>> selectors = selectorsService.getAllSelectorData();

        assertEquals(3, selectors.size());
        assertTrue(selectors.containsKey("types"));
        assertTrue(selectors.containsKey("colors"));
        assertTrue(selectors.containsKey("countries"));

        assertEquals(Arrays.asList("dog", "cat"), selectors.get("types"));
        assertEquals(Arrays.asList("white", "orange"), selectors.get("colors"));
        assertEquals(Arrays.asList("Estonia", "Finland"), selectors.get("countries"));

        verify(furColorRepo, times(1)).findAll();
        verify(countryRepo, times(1)).findAll();
        verify(animalTypeRepo, times(1)).findAll();

        verify(furColorDTOMapper, times(2)).entityToDto(any(FurColor.class));
        verify(countryDTOMapper, times(2)).entityToDto(any(Country.class));
        verify(animalTypeDTOMapper, times(2)).entityToDto(any(AnimalType.class));
    }
}