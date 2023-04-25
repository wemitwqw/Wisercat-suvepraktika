package ee.vladislav.backend.service;

import ee.vladislav.backend.exceptions.AnimalTypeNotAcceptedException;
import ee.vladislav.backend.exceptions.CountryNotAcceptedException;
import ee.vladislav.backend.exceptions.FurColorNotAcceptedException;
import ee.vladislav.backend.model.AnimalType;
import ee.vladislav.backend.model.Country;
import ee.vladislav.backend.model.FurColor;
import ee.vladislav.backend.repository.CountryRepo;
import ee.vladislav.backend.repository.FurColorRepo;
import ee.vladislav.backend.repository.AnimalTypeRepo;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SelectorsService {
    private final CountryRepo countryRepository;
    private final FurColorRepo furColorRepository;
    private final AnimalTypeRepo animalTypeRepository;

    public SelectorsService(CountryRepo countryRepository, FurColorRepo furColorRepository, AnimalTypeRepo animalTypeRepository) {
        this.countryRepository = countryRepository;
        this.furColorRepository = furColorRepository;
        this.animalTypeRepository = animalTypeRepository;
    }

//    private List<Country> getAllowedCountries() {
//        return countryRepository.findAll();
//    }
//
//    private List<FurColor> getAllowedFurColors() {
//        return furColorRepository.findAll();
//    }
//
//    private List<AnimalType> getAllowedAnimalTypes() {
//        return animalTypeRepository.findAll();
//    }

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

    public List<List> getAllSelectorData() {
        List<List> data = new ArrayList<>();

        List<FurColor> colors = new ArrayList<>(furColorRepository.findAll());
        List<Country> countries = new ArrayList<>(countryRepository.findAll());
        List<AnimalType> types = new ArrayList<>(animalTypeRepository.findAll());

        data.add(types);
        data.add(colors);
        data.add(countries);

        return data;
    }
}
