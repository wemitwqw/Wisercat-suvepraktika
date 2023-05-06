package ee.vladislav.backend.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> authenticate(Principal principal) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Welcome, " + principal.getName() + "!");

        return ResponseEntity.ok().body(map);
    }
}
