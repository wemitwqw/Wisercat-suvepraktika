package ee.vladislav.backend.repository;

import ee.vladislav.backend.model.FurColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurColorRepo extends JpaRepository<FurColor, String> {
}
