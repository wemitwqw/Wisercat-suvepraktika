package ee.vladislav.backend.service;

import ee.vladislav.backend.repository.AnimalCountryRepo;
import ee.vladislav.backend.repository.AnimalFurColorRepo;
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
    AnimalCountryRepo animalCountryRepository;
    @Autowired
    AnimalFurColorRepo animalFurColorRepository;
    @Autowired
    AnimalTypeRepo animalTypeRepository;

    public List<Any> getAllSelectorData() throws ExecutionException {
        List data = new ArrayList<>();
        List countries = new ArrayList<>();
        List colors = new ArrayList<>();
        List types = new ArrayList<>();
        countries.add(animalCountryRepository.findAll());
        colors.add(animalFurColorRepository.findAll());
        types.add(animalTypeRepository.findAll());
        data.addAll(types);
        data.addAll(colors);
        data.addAll(countries);
        return data;
    }
}
