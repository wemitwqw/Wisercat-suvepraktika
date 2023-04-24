package ee.vladislav.backend.service;

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

    public List<Country> getAllowedCountries() {
        return countryRepository.findAll();
    }

    public List<FurColor> getAllowedFurColors() {
        return furColorRepository.findAll();
    }

    public List<AnimalType> getAllowedAnimalTypes() {
        return animalTypeRepository.findAll();
    }

//    public List<Any> getAllSelectorData() throws ExecutionException {
//        List<List> data = new ArrayList<>();
//        List<Country> countries = new ArrayList<>();
//        List<FurColor> colors = new ArrayList<>();
//        List<AnimalType> types = new ArrayList<>();
//        countries.add(countryRepository.findAll());
//        colors.add(furColorRepository.findAll());
//        types.add(animalTypeRepository.findAll());
//        data.addAll(types);
//        data.addAll(colors);
//        data.addAll(countries);
//        return data;
//    }
}
