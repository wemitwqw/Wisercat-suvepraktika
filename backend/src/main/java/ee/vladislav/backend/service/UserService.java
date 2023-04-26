package ee.vladislav.backend.service;

import ee.vladislav.backend.model.User;
import ee.vladislav.backend.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserByUserName(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    public Boolean checkUserNameExists(String username) {
        return userRepo.existsByUsername(username);
    }
}
