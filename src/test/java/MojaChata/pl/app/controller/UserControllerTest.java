package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.User;
import MojaChata.pl.app.model.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLogin_ValidCredentials() throws Exception {
        User mockUser = new User("ValidUser", new BCryptPasswordEncoder().encode("Validpass"));

        Mockito.when(userRepository.findByUsername("ValidUser")).thenReturn(Optional.of(mockUser));

        mockMvc.perform(post("/login")
                .param("username", "ValidUser")
                .param("password", "Validpass")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    public void testLogin_InvalidCredentials() throws Exception {

        Mockito.when(userRepository.findByUsername("invalidUser")).thenReturn(Optional.empty());


        mockMvc.perform(post("/login")
                .param("username", "invalidUser")
                .param("password", "wrongPassword")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Invalid username or password."));
    }

    @Test
    public void testRegisterUsernameAlreadyExists() throws Exception {
        User existingUser = new User("existingUser", "123");
        Mockito.when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(existingUser));
    
        mockMvc.perform(post("/register")
                .param("username", "existingUser")
                .param("passwordHash", "password123")
                .param("confirmPassword", "password123")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Username already exists. Please choose a different one."));
    }

    @Test
    public void testRegisterPasswordsDoNotMatch() throws Exception {
        Mockito.when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());

        mockMvc.perform(post("/register")
                .param("username", "newUser")
                .param("passwordHash", "password123")
                .param("confirmPassword", "password456")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Passwords do not match."));
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        Mockito.when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());

        mockMvc.perform(post("/register")
                .param("username", "newUser")
                .param("passwordHash", "password123")
                .param("confirmPassword", "password123")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }
}
