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
public class FurColorDTO {

    @NotNull(message = "Fur color must not be null!")
    @NotEmpty(message = "Fur color must not be empty!")
    @Size(min = 1, max = 30, message = "Fur color length must be 1 to 30 characters!")
    private String color;

}
