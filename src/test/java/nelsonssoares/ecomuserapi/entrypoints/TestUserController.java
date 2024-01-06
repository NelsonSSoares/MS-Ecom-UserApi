package nelsonssoares.ecomuserapi.entrypoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.outlayers.entrypoints.UserController;
import nelsonssoares.ecomuserapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static nelsonssoares.ecomuserapi.commons.ControllerConstants.*;
import static nelsonssoares.ecomuserapi.commons.UsuarioConstants.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class TestUserController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void createUsuario_withValidInput_shouldReturnCreated() throws Exception {

        when(userService.save(VALID_USERDTO)).thenReturn(VALID_USERDTO_RESPONSE);
        mockMvc.perform(post(API_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_USERDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(VALID_USERDTO_RESPONSE.getBody().nome()))
                .andExpect(jsonPath("$.sobrenome").value(VALID_USERDTO_RESPONSE.getBody().sobrenome()))
                .andExpect(jsonPath("$.cpf").value(VALID_USERDTO_RESPONSE.getBody().cpf()))
                .andExpect(jsonPath("$.telefone").value(VALID_USERDTO_RESPONSE.getBody().telefone()))
                .andExpect(jsonPath("$.email").value(VALID_USERDTO_RESPONSE.getBody().email()));

    }

    @Test
    public void createUser_withInvalidInput_shouldReturnBadRequest() throws Exception {

        /*
            TESTE FALHANDO, RETORNANDO 201 AO INVÉS DE 400 OU 422, Falha no teste de validação de dados
         */

        UsuarioDTO usuario = new UsuarioDTO("","","","","");
        UsuarioDTO invalidoUsuario = new UsuarioDTO(null, null, null, null, null);

        mockMvc.perform(post(API_BASE_URL)
                        .content(objectMapper.writeValueAsString(usuario))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(post(API_BASE_URL)
                        .content(objectMapper.writeValueAsString(invalidoUsuario))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createUser_withExistingUser_shouldReturnConflict() throws Exception {
           /*
            TESTE FALHANDO, RETORNANDO 201 AO INVÉS DE 409, Falha no teste de validação de dados
         */

        when(userService.save(VALID_USERDTO)).thenReturn(VALID_USERDTO_RESPONSE);
        mockMvc.perform(post(API_BASE_URL)
                        .content(objectMapper.writeValueAsString(VALID_USERDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf").value(VALID_USERDTO_RESPONSE.getBody().cpf()))
                .andExpect(status().isConflict());
    }

    @Test
    public void getUser_ByExistingId_shouldReturnUsuario() throws Exception {

        when(userService.findById(1)).thenReturn(VALID_USER_GETRESPONSE);
        mockMvc.perform(get(API_BASE_URL + ID, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(VALID_USER_GETRESPONSE.getBody().getNome()))
                .andExpect(jsonPath("$.sobrenome").value(VALID_USER_GETRESPONSE.getBody().getSobrenome()))
                .andExpect(jsonPath("$.cpf").value(VALID_USER_GETRESPONSE.getBody().getCpf()))
                .andExpect(jsonPath("$.telefone").value(VALID_USER_GETRESPONSE.getBody().getTelefone()))
                .andExpect(jsonPath("$.email").value(VALID_USER_GETRESPONSE.getBody().getEmail()));
    }

    @Test
    public void getUser_ByNonExistingId_shouldReturnNotFound() throws Exception {

        when(userService.findById(50)).thenReturn(INVALID_USER_GETRESPONSE);
        mockMvc.perform(get(API_BASE_URL + ID, 50))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUser_ByExistingName_ShouldReturnUsuario() throws Exception {

        when(userService.findByName("Nelson")).thenReturn(VALID_USERDTO_GETLISTRESPONSE);
        mockMvc.perform(get(API_BASE_URL + NAME, "Nelson"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(VALID_USER_GETRESPONSE.getBody().getNome()))
                .andExpect(jsonPath("$[0].sobrenome").value(VALID_USER_GETRESPONSE.getBody().getSobrenome()))
                .andExpect(jsonPath("$[0].cpf").value(VALID_USER_GETRESPONSE.getBody().getCpf()))
                .andExpect(jsonPath("$[0].telefone").value(VALID_USER_GETRESPONSE.getBody().getTelefone()))
                .andExpect(jsonPath("$[0].email").value(VALID_USER_GETRESPONSE.getBody().getEmail()));
    }

    @Test
    public void getUser_ByNonExistingName_ShouldReturnNotFound() throws Exception {
        when(userService.findByName("Juarez")).thenReturn(EMPTY_LIST);

        mockMvc.perform(get(API_BASE_URL + NAME, "Juarez"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }


}
