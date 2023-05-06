package ee.vladislav.backend.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
class AuthControllerIntTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1")
    public void shouldAuthenticateWithAuth() throws Exception {
        mockMvc.perform(get("/api/auth/login"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldNotAuthenticateWithNoAuth() throws Exception {
        mockMvc.perform(get("/api/auth/login"))
                .andExpect(status().isUnauthorized());

    }
}