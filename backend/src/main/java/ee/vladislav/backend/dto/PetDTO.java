package ee.vladislav.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Size(max = 20, message = "Animal type length cannot exceed 20 characters!")
    private String animalType;

    @NotNull(message = "Fur color must not be null!")
    @NotEmpty(message = "Fur color must not be empty string!")
    @Size(max = 20, message = "Fur color length cannot exceed 20 characters!")
    private String furColor;

    @NotNull(message = "Country must not be null!")
    @NotEmpty(message = "Country must not be empty string!")
    @Pattern(regexp = "\\b[A-Z][a-zA-Z]*\\b", message = "Country name must be capitalized!")
    @Size(max = 20, message = "Country length cannot exceed 20 characters!")
    private String country;

}
