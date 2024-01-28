package nelsonssoares.ecomuserapi.usecases.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static nelsonssoares.ecomuserapi.commons.UserConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveUserTest {

    @InjectMocks
    private SaveUser saveUser;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void createUser_WithValidUser_ShouldReturnUser() {
        when(objectMapper.convertValue(UsuarioDTO.class, Usuario.class)).thenReturn(VALID_USER);
        when(usuarioRepository.save(VALID_USER)).thenReturn(VALID_USER);
        UsuarioDTO sut = saveUser.executeSaveUser(VALID_USERDTO);
        assertThat(sut).isEqualTo(VALID_USER);
    }

    @Test
    public void createUser_WithInvalidUser_ThrowsException() {
        when(objectMapper.convertValue(UsuarioDTO.class, Usuario.class)).thenReturn(INVALID_USER);
        when(usuarioRepository.save(INVALID_USER)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> saveUser.executeSaveUser(INVALID_USERDTO))
                .isInstanceOf(RuntimeException.class);
    }
}
