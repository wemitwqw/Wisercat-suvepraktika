//package ee.vladislav.backend.repository;
//
//import ee.vladislav.backend.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserRepo extends JpaRepository<UserDetails, Long> {
//    Optional<User> findByUsername(String username);
//
//    Boolean existsByUsername(String username);
//
//}