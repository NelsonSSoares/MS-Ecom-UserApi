package nelsonssoares.ecomuserapi.usecases.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.UserConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByEmailTest {

    @InjectMocks
    private GetUserByEmail getByUserEmail;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void getUserByEmail_WithValidEmail_ShouldReturnUser() {
        when(usuarioRepository.findByEmail(VALID_USER.getEmail())).thenReturn(Optional.of(VALID_USER));
        when(objectMapper.convertValue(VALID_USER, UsuarioDTO.class)).thenReturn(VALID_USERDTO);
        UsuarioDTO sut = getByUserEmail.executeUserByEmail(VALID_USER.getEmail());
        assertEquals(VALID_USERDTO, sut);
    }

    @Test
    public void getUserByEmail_WithInvalidEmail_ShouldReturnNull() {
        when(usuarioRepository.findByEmail(INCORRECT_USER.getEmail())).thenReturn(Optional.empty());
        UsuarioDTO sut = getByUserEmail.executeUserByEmail(INCORRECT_USER.getEmail());
        assertEquals(null, sut);
    }
}
