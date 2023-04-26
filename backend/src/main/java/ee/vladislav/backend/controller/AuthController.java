package ee.vladislav.backend.controller;

import ee.vladislav.backend.dto.PetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/auth")
public class AuthController {

    @GetMapping("/")
    public ResponseEntity<String> getPets(Principal principal) {

        return ResponseEntity.ok().body("Welcome, " + principal.getName() + "!");
    }
}
