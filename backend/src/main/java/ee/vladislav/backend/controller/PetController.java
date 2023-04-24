package ee.vladislav.backend.controller;

import ee.vladislav.backend.model.Pet;
import ee.vladislav.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class PetController {
    @Autowired
    PetService petService;

    @GetMapping("/")
    public List<Pet> getPets() throws ExecutionException {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable String id) throws ExecutionException {
        return petService.getById(id);
    }

    @PostMapping("/edit")
    public void addPet(@RequestBody Pet pet) throws ExecutionException {
        petService.addPet(pet);
    }
    @PutMapping("/edit/{id}")
    public void editPet(@PathVariable String id, @RequestBody Pet pet) {
        petService.editPet(id, pet);
    }
}
