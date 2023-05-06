package ee.vladislav.backend;

import ee.vladislav.backend.controller.PetController;
import ee.vladislav.backend.controller.SelectorsController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BackendApplicationTests {

    @Autowired
    PetController petController;

    @Autowired
    SelectorsController selectorsController;

    @Test
    void contextLoads() {
        Assertions.assertThat(petController).isNot(null);
        Assertions.assertThat(selectorsController).isNot(null);
    }

}
