package ee.vladislav.backend;

import ee.vladislav.backend.controller.PetController;
import ee.vladislav.backend.controller.SelectorsController;
import ee.vladislav.backend.service.SelectorsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BackendApplicationTests {

    @Autowired
    PetController petController;

    @Autowired
    SelectorsController selectorsController;

    @Test
    void contextLoads() {
        assertNotNull(petController);
        assertNotNull(selectorsController);
    }

}
