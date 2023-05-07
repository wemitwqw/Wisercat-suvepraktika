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

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.vladislav.backend.dto.PetDTO;
import ee.vladislav.backend.exceptions.PetNotAddedException;
import ee.vladislav.backend.exceptions.PetNotUpdatedException;
import ee.vladislav.backend.model.*;
import ee.vladislav.backend.repository.PetRepo;
import ee.vladislav.backend.service.PetService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PetController.class)
public class PetControllerIntTest {

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
    public void shouldFindAllAddedByUserWithAuth() throws Exception {
        createTestPet("user1");

        PetDTO petDTO = new PetDTO(1L, "Ditto", "12345678", "dog", "orange", "Estonia");
        List<PetDTO> pets = List.of(petDTO);

        Mockito.when(petService.getAllPetsByUserName("user1")).thenReturn(pets);

        mockMvc.perform(get("/api/pets/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pets)));
    }

    @Test
    public void shouldNotFindAllAddedByUserWithNoAuth() throws Exception {

        mockMvc.perform(get("/api/pets/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user1")
    public void shouldFindPetByIdWithAuth() throws Exception {
        createTestPet("user1");

        PetDTO petDTO = new PetDTO(1L, "Ditto", "12345678", "dog", "orange", "Estonia");

        Mockito.when(petService.getById("1", "user1")).thenReturn(petDTO);

        mockMvc.perform(get("/api/pets/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(petDTO)));
    }

    @Test
    public void shouldNotFindPetByIdWithNoUserAuth() throws Exception {

        mockMvc.perform(get("/api/pets/{id}", 1))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user2")
    public void shouldAddPetWithAuth() throws Exception {
        PetDTO petDTO = new PetDTO("Ditto", "23345678", "dog", "orange", "Estonia");

        Mockito.when(petService.addPet(petDTO, "user2"))
                .thenReturn(new PetDTO("Ditto", "23345678", "dog", "orange", "Estonia"));

        mockMvc.perform(post("/api/pets/add")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(petDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(petDTO)));
    }

    @Test
    public void shouldNotAddPetWithoutAuth() throws Exception {
        PetDTO petDTO = new PetDTO("Ditto", "23345678", "dog", "orange", "Estonia");

        mockMvc.perform(post("/api/pets/add")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(petDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user2")
    public void shouldEditPetWithAuth() throws Exception {
        createTestPet("user2");

        PetDTO petDTO = new PetDTO("Britto", "23345678", "cat", "white", "Sweden");

        Mockito.when(petService.editPet("1", petDTO, "user2"))
                .thenReturn(new PetDTO("Britto", "23345678", "cat", "white", "Sweden"));

        mockMvc.perform(put("/api/pets/edit/{id}", 1)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(petDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(petDTO)));
    }

    @Test
    public void shouldNotEditPetWithAuth() throws Exception {
        createTestPet("user2");

        PetDTO petDTO = new PetDTO("Britto", "23345678", "cat", "white", "Sweden");

        mockMvc.perform(put("/api/pets/edit/{id}", 1)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(petDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }
}