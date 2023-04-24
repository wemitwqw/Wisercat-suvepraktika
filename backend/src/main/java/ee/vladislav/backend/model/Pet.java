package ee.vladislav.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotBlank
    @OneToOne
    @JoinColumn(name="animal_type_id", referencedColumnName="id")
    private AnimalType animalType;
    @NotBlank
    @OneToOne
    @JoinColumn(name="fur_color_id", referencedColumnName = "id")
    private AnimalFurColor fur_color;
    @NotBlank
    @OneToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private AnimalCountry country;

}
