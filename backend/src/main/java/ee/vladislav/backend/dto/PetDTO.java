package ee.vladislav.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetDTO {

    private Long id;

    @NotNull(message = "Name must not be null!")
    @Size(min = 1, max = 30, message = "Name length must be 1 to 30 characters!")
    private String name;

    @NotNull(message = "Code must not be null!")
    @Size(min = 1, max = 8, message = "Code length must be 1 to 8 characters!")
    private String code;

    @NotNull(message = "Animal type must not be null!")
    @NotEmpty(message = "Animal type must not be empty string!")
    @Size(min = 3, max = 20, message = "Animal type length must be 20>X>3 characters!")
    private String animalType;

    @NotNull(message = "Fur color must not be null!")
    @NotEmpty(message = "Fur color must not be empty string!")
    @Size(min = 3, max = 20, message = "Fur color length must be 20>X>3 characters!")
    private String furColor;

    @NotNull(message = "Country must not be null!")
    @NotEmpty(message = "Country must not be empty string!")
    @Size(min = 3, max = 20, message = "Country length must be 20>X>3 characters!")
    private String country;

//    private String addedBy;


    public PetDTO(String name, String code, String animalType, String furColor, String country) {
        this.name = name;
        this.code = code;
        this.animalType = animalType;
        this.furColor = furColor;
        this.country = country;
    }
}
