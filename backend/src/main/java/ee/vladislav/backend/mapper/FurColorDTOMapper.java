package ee.vladislav.backend.mapper;

import ee.vladislav.backend.dto.FurColorDTO;
import ee.vladislav.backend.model.FurColor;
import org.springframework.stereotype.Service;

@Service
public class FurColorDTOMapper {
    public FurColorDTO entityToDto(FurColor furColor) {
        return new FurColorDTO(furColor.getColor());
    }
}
