package ee.vladislav.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String code;

    @NotNull
    @OneToOne
    @JoinColumn(name="animal_type_id", referencedColumnName="id")
    private AnimalType animalType;

    @NotNull
    @OneToOne
    @JoinColumn(name="fur_color_id", referencedColumnName = "id")
    private FurColor furColor;

    @NotNull
    @OneToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private Country country;

    @NotNull
    @OneToOne
    @JoinColumn(name="added_by", referencedColumnName = "username")
    private User addedBy;

}
