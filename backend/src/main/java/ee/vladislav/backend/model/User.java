package ee.vladislav.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Only letters and numbers allowed in password!")
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 1, max = 35)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Only letters and numbers allowed in password!")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

}

