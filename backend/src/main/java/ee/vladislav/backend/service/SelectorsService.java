package ee.vladislav.backend.service;

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
    @Autowired
    CountryRepo countryRepository;
    @Autowired
    FurColorRepo furColorRepository;
    @Autowired
    AnimalTypeRepo animalTypeRepository;

    public List<Any> getAllSelectorData() throws ExecutionException {
        List data = new ArrayList<>();
        List countries = new ArrayList<>();
        List colors = new ArrayList<>();
        List types = new ArrayList<>();
        countries.add(countryRepository.findAll());
        colors.add(furColorRepository.findAll());
        types.add(animalTypeRepository.findAll());
        data.addAll(types);
        data.addAll(colors);
        data.addAll(countries);
        return data;
    }
}
