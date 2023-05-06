package ee.vladislav.backend.controller;
//
//@AfterEach
//public void resetDb() {
//        petRepository.deleteAll();
//        }
//
//private Pet createTestPet() {
//        AnimalType type = new AnimalType(1L, "dog");
//        FurColor color = new FurColor(1L, "orange");
//        Country country = new Country(1L, "Estonia");
//        User user = new User("user1", "password", true);
//
//        Pet pet = new Pet(1L, "Ditto", "12345678", type, color, country, user);
//
//        return petRepository.save(pet);
//        }

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exceptions.PetNotFoundException;
import ee.vladislav.backend.model.*;
import ee.vladislav.backend.repository.PetRepo;
import ee.vladislav.backend.service.PetService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PetController.class)
public class PetControllerTest {

    @MockBean
    PetService petService;

    @MockBean
    PetRepo petRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    public void resetDb() {
        petRepository.deleteAll();
    }

    private Pet createTestPet(String username) {
        AnimalType type = new AnimalType(1L, "dog");
        FurColor color = new FurColor(1L, "orange");
        Country country = new Country(1L, "Estonia");
        User user = new User(username, "password", true);

        Pet pet = new Pet(1L, "Ditto", "12345678", type, color, country, user);

        return petRepository.save(pet);
        }

    @Test
    @WithMockUser(username = "user1")
    public void testFindAllAddedByUserWithAuth() throws Exception {
        createTestPet("user1");

        PetDTO petDTO = new PetDTO(1L, "Ditto", "12345678", "dog", "orange", "Estonia");
        List<PetDTO> pets = List.of(petDTO);

        Mockito.when(petService.getAllPetsByUserName("user1")).thenReturn(pets);

        mockMvc.perform(get("/api/pets/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pets)));
//                .andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
//                .andExpect(jsonPath("$[0].name", Matchers.is("Ditto")))
//                .andExpect(jsonPath("$[0].code", Matchers.is("12345678")))
//                .andExpect(jsonPath("$[0].animalType", Matchers.is("dog")))
//                .andExpect(jsonPath("$[0].furColor", Matchers.is("orange")))
//                .andExpect(jsonPath("$[0].country", Matchers.is("Estonia")));
    }

    @Test
    public void testFindAllAddedByUserWithNoAuth() throws Exception {

        mockMvc.perform(get("/api/pets/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user1")
    public void testFindPetByIdWithAuth() throws Exception {
        createTestPet("user1");

        PetDTO petDTO = new PetDTO(1L, "Ditto", "12345678", "dog", "orange", "Estonia");

        Mockito.when(petService.getById("1", "user1")).thenReturn(petDTO);

        mockMvc.perform(get("/api/pets/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(petDTO)));
//                .andExpect(jsonPath("$.id", Matchers.is(1)))
//                .andExpect(jsonPath("$.name", Matchers.is("Ditto")))
//                .andExpect(jsonPath("$.code", Matchers.is("12345678")))
//                .andExpect(jsonPath("$.animalType", Matchers.is("dog")))
//                .andExpect(jsonPath("$.furColor", Matchers.is("orange")))
//                .andExpect(jsonPath("$.country", Matchers.is("Estonia")));
    }

    @Test
    @WithMockUser(username = "user2")
    public void testFindPetByIdWithWrongUserAuth() throws Exception {
        createTestPet("user1");

        PetDTO petDTO = new PetDTO(1L, "Ditto", "12345678", "dog", "orange", "Estonia");

        Mockito.when(petService.getById("1", "user1")).thenReturn(petDTO);

        mockMvc.perform(get("/api/pets/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindPetByIdWithNoUserAuth() throws Exception {

        mockMvc.perform(get("/api/pets/{id}", 1))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user3")
    public void testFindPetByIdWithNonExistentId() throws Exception {
        createTestPet("user3");

        PetDTO petDTO = new PetDTO(1L, "Ditto", "12345678", "dog", "orange", "Estonia");

        mockMvc.perform(get("/api/pets/{id}", 9999))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user2")
    public void testAddPetWithAuth() throws Exception {
        PetDTO petDTO = new PetDTO("Ditto", "12344228", "dog", "orange", "Estonia");

        mockMvc.perform(post("/api/pets/add").with(csrf()).content(objectMapper.writeValueAsString(petDTO)).contentType(MediaType.APPLICATION_JSON))
        .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(petDTO)));
    }
}