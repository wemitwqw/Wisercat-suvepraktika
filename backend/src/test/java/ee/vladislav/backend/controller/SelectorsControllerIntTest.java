package ee.vladislav.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.model.*;
import ee.vladislav.backend.repository.AnimalTypeRepo;
import ee.vladislav.backend.repository.CountryRepo;
import ee.vladislav.backend.repository.FurColorRepo;
import ee.vladislav.backend.repository.PetRepo;
import ee.vladislav.backend.service.PetService;
import ee.vladislav.backend.service.SelectorsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SelectorsController.class)
class SelectorsControllerIntTest {

    @MockBean
    SelectorsService selectorsService;

    @MockBean
    AnimalTypeRepo animalTypeRepo;

    @MockBean
    CountryRepo countryRepo;

    @MockBean
    FurColorRepo furColorRepo;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    public void resetDb() {
        animalTypeRepo.deleteAll();
        furColorRepo.deleteAll();
        countryRepo.deleteAll();
    }

    void createTestSelectors() {
        animalTypeRepo.save(new AnimalType(1L, "dog"));
        animalTypeRepo.save(new AnimalType(2L, "cat"));
        furColorRepo.save(new FurColor(1L, "white"));
        furColorRepo.save(new FurColor(2L, "orange"));
        countryRepo.save(new Country(1L, "Estonia"));
        countryRepo.save(new Country(2L, "Finland"));
    }

    @Test
    @WithMockUser(username = "user1")
    public void shouldFindAllSelectorsWithAuth() throws Exception {
        createTestSelectors();

        AnimalType type1 = new AnimalType(1L, "dog");
        AnimalType type2 = new AnimalType(2L, "cat");
        FurColor color1 = new FurColor(1L, "white");
        FurColor color2 = new FurColor(2L, "orange");
        Country country1 = new Country(1L, "Estonia");
        Country country2 = new Country(2L, "Finland");
        Map<String, List<String>> map = new HashMap<>();

        map.put("types", Arrays.asList(type1.getType(), type2.getType()));
        map.put("colors", Arrays.asList(color1.getColor(), color2.getColor()));
        map.put("countries", Arrays.asList(country1.getCountry(), country2.getCountry()));

        Mockito.when(selectorsService.getAllSelectorData()).thenReturn(map);

        mockMvc.perform(get("/api/selectors/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(map)));
    }

    @Test
    public void shouldNotFindAllSelectorsWithoutAuth() throws Exception {

        mockMvc.perform(get("/api/selectors/"))
                .andExpect(status().isUnauthorized());
    }

}