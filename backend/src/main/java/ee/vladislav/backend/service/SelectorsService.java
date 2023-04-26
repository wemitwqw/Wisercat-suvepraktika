package ee.vladislav.backend.service;

import ee.vladislav.backend.exceptions.AnimalTypeNotAcceptedException;
import ee.vladislav.backend.exceptions.CountryNotAcceptedException;
import ee.vladislav.backend.exceptions.FurColorNotAcceptedException;
import ee.vladislav.backend.mapper.AnimalTypeDTOMapper;
import ee.vladislav.backend.mapper.CountryDTOMapper;
import ee.vladislav.backend.mapper.FurColorDTOMapper;
import ee.vladislav.backend.model.AnimalType;
import ee.vladislav.backend.model.Country;
import ee.vladislav.backend.model.FurColor;
import ee.vladislav.backend.repository.CountryRepo;
import ee.vladislav.backend.repository.FurColorRepo;
import ee.vladislav.backend.repository.AnimalTypeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectorsService {
    private final CountryRepo countryRepository;
    private final FurColorRepo furColorRepository;
    private final AnimalTypeRepo animalTypeRepository;
    private final FurColorDTOMapper furColorDTOMapper;
    private final AnimalTypeDTOMapper animalTypeDTOMapper;
    private final CountryDTOMapper countryDTOMapper;

    public SelectorsService(CountryRepo countryRepository,
                            FurColorRepo furColorRepository,
                            AnimalTypeRepo animalTypeRepository,
                            FurColorDTOMapper furColorDTOMapper,
                            AnimalTypeDTOMapper animalTypeDTOMapper,
                            CountryDTOMapper countryDTOMapper) {
        this.countryRepository = countryRepository;
        this.furColorRepository = furColorRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.furColorDTOMapper = furColorDTOMapper;
        this.animalTypeDTOMapper = animalTypeDTOMapper;
        this.countryDTOMapper = countryDTOMapper;
    }

    public AnimalType getAnimalTypeByType(String type) {
        AnimalType animalTypeEnt = animalTypeRepository.findAnimalTypeByType(type.toLowerCase());
        if (animalTypeEnt == null) { throw new AnimalTypeNotAcceptedException(); }

        return animalTypeEnt;
    }

    public FurColor getFurColorByColor(String color) {
        FurColor furColorEnt = furColorRepository.findFurColorByColor(color.toLowerCase());
        if (furColorEnt == null) { throw new FurColorNotAcceptedException(); }

        return furColorEnt;
    }

    public Country getCountryByCountry(String country) {
        Country countryEnt = countryRepository.findCountryByCountry(country);
        if (countryEnt == null) { throw new CountryNotAcceptedException(); }

        return countryEnt;
    }

    public List<List<String>> getAllSelectorData() {
        List<List<String>> selectors = new ArrayList<>();

        List<String> colors = furColorRepository.findAll()
                .stream()
                .map(col -> furColorDTOMapper.entityToDto(col).getColor())
                .collect(Collectors.toList());

        List<String> countries = countryRepository.findAll()
                .stream()
                .map(country -> countryDTOMapper.entityToDto(country).getCountry())
                .collect(Collectors.toList());

        List<String> types = animalTypeRepository.findAll()
                .stream()
                .map(type -> animalTypeDTOMapper.entityToDto(type).getType())
                .collect(Collectors.toList());

        selectors.add(types);
        selectors.add(colors);
        selectors.add(countries);

        return selectors;
    }
}
