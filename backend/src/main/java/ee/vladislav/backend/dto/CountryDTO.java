package ee.vladislav.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryDTO {

    @NotNull(message = "Country must not be null!")
    @NotEmpty(message = "Country must not be empty!")
    @Size(min = 1, max = 30, message = "Country length must be 1 to 30 characters!")
    private String country;
}
