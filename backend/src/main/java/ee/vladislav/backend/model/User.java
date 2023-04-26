package ee.vladislav.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    @Size(min = 1, max = 20)
    @Column(nullable = false, unique = true)
    private String username;

    @Pattern(regexp = "^(?=.*?\\d)(?=.*?[a-zA-Z])[a-zA-Z\\d]+$", message = "Only letters and numbers allowed in password!")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

}

