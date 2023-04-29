package ee.vladislav.backend.controller;

import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );



        Authentication authenticated = authenticationProvider.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        HashMap<String, String> map = new HashMap<>();
        if (authenticated.isAuthenticated()) {
            map.put("status", "OK");
            map.put("username", authenticated.getName());

        }

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

//    @PostMapping("/")
//    public ResponseEntity<String> authenticate(Principal principal) {
//
//        return ResponseEntity.ok().body("Welcome, " + principal.getName() + "!");
//    }
}
