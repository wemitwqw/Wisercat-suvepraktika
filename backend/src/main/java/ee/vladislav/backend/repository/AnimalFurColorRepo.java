package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.AnimalFurColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalFurColorRepo extends JpaRepository<AnimalFurColor, String> {
}
