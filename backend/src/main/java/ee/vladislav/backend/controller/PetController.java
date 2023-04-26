package ee.vladislav.backend.controller;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exceptions.PetNotAddedException;
import ee.vladislav.backend.exceptions.PetNotFoundException;
import ee.vladislav.backend.service.PetService;
import ee.vladislav.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PetDTO>> getPets(Principal principal) {
        String userName = principal.getName();
        List<PetDTO> pets = petService.getAllPetsByUserName(userName);

        return ResponseEntity.ok().body(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable String id, Principal principal) {
        PetDTO pet = petService.getById(id, principal.getName());
        if (pet == null) { throw new PetNotFoundException(); }

        return ResponseEntity.ok().body(pet);
    }

    @PostMapping("/add")
    public ResponseEntity<PetDTO> addPet(@Valid @RequestBody PetDTO petDTO, Principal principal) {
        PetDTO savedPet = petService.addPet(petDTO, principal.getName());
        if (savedPet == null) { throw new PetNotAddedException(); }

        return ResponseEntity.status(CREATED).body(savedPet);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PetDTO> editPet(@PathVariable String id, @Valid @RequestBody PetDTO petDTO, Principal principal) {
        PetDTO savedPet = petService.editPet(id, petDTO, principal.getName());
        if (savedPet == null) { throw new PetNotAddedException(); }

        return ResponseEntity.status(OK).body(savedPet);
    }
}
