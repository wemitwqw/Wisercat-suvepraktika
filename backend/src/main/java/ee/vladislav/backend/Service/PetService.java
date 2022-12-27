package ee.vladislav.backend.Service;

import ee.vladislav.backend.Model.Pet;
import ee.vladislav.backend.Repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PetService {
    @Autowired
    PetRepo petRepository;

    public List<Pet> getAllPets() throws ExecutionException {
        return petRepository.findAll();
    }

    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }
}
