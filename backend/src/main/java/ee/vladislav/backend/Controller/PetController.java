package ee.vladislav.backend.Controller;

import ee.vladislav.backend.Model.Pet;
import ee.vladislav.backend.Repository.PetRepo;
import ee.vladislav.backend.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class PetController {
    @Autowired
    PetService petService;

    @GetMapping("/")
    public List<Pet> getPets() throws ExecutionException {
        return petService.getAllPets();
    }

    @PostMapping("/add")
    public void addPet(@RequestBody Pet pet) {
        petService.updatePet(pet);
    }
}
