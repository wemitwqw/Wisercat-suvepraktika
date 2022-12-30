package ee.vladislav.backend.Repository;

import ee.vladislav.backend.Model.AnimalFurColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalFurColorRepo extends JpaRepository<AnimalFurColor, String> {
}
