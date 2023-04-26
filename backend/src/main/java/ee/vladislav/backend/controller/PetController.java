package ee.vladislav.backend.controller;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exceptions.PetNotAddedException;
import ee.vladislav.backend.exceptions.PetNotFoundException;
import ee.vladislav.backend.model.Pet;
import ee.vladislav.backend.service.PetService;
import ee.vladislav.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/pets")
public class PetController {
    private final PetService petService;
    private final UserService userService;

    public PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PetDTO>> getPets(Principal principal) {
        String userName = principal.getName();
        if(!userService.checkUserNameExists(userName)){
            return ResponseEntity.ok().body(new ArrayList<>());
        }

        List<PetDTO> pets = petService.getAllPetsByUserName(userName);

        return ResponseEntity.ok().body(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable String id) {
        PetDTO pet = petService.getById(id);
        if (pet == null) { throw new PetNotFoundException(); }

        return ResponseEntity.ok().body(pet);
    }

    @PostMapping("/add")
    public ResponseEntity<PetDTO> addPet(@Valid @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.addPet(petDTO);
        if (savedPet == null) { throw new PetNotAddedException(); }

        return ResponseEntity.status(CREATED).body(savedPet);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PetDTO> editPet(@PathVariable String id, @Valid @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.editPet(id, petDTO);
        if (savedPet == null) { throw new PetNotAddedException(); }

        return ResponseEntity.status(OK).body(savedPet);
    }
}
