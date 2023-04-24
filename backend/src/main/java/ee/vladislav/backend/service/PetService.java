package ee.vladislav.backend.service;

import ee.vladislav.backend.model.Pet;
import ee.vladislav.backend.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PetService {

    private final PetRepo petRepository;

    public PetService(PetRepo petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() throws ExecutionException {
        return petRepository.findAll();
    }

    public Pet getById(String id) throws ExecutionException {
        return petRepository.findById(id).get();
    }

    public void addPet(Pet pet) throws ExecutionException {
        petRepository.save(pet);
    }
    public void editPet(String id, Pet pet) {
        Pet petFromDb = petRepository.findById(id).get();
        petFromDb.setName(pet.getName());
        petFromDb.setCode(pet.getCode());
        petFromDb.setAnimalType(pet.getAnimalType());
        petFromDb.setFur_color(pet.getFur_color());
        petFromDb.setCountry(pet.getCountry());
        petRepository.save(petFromDb);
    }
}
