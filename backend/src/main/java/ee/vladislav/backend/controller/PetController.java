package ee.vladislav.backend.controller;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.model.Pet;
import ee.vladislav.backend.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PetDTO>> getPets() {
        return ResponseEntity.ok().body(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PetDTO>> getPetById(@PathVariable String id) {
        return ResponseEntity.ok().body(petService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PetDTO> addPet(@RequestBody PetDTO petDTO) {

        return ResponseEntity.status(CREATED).body(petService.addPet(petDTO));
    }

    @PutMapping("/edit/{id}")
    public void editPet(@PathVariable String id, @RequestBody Pet pet) {
        petService.editPet(id, pet);
    }
}
