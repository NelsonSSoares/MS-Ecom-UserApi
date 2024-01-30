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
import org.springframework.transaction.annotation.Transactional;

import static javax.management.Query.eq;
import static nelsonssoares.ecomuserapi.commons.UserConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserTest {

    @InjectMocks
    private UpdateUser updateUser;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void updateUser_WithValidUser_ShouldReturnUser() {
        when(usuarioRepository.save(any())).thenReturn(VALID_USER);
        when(objectMapper.convertValue(UsuarioDTO.class, Usuario.class)).thenReturn(VALID_USER);
        Usuario sut = updateUser.executeUpdateUser(VALID_USER.getId(), VALID_USERDTO);
        assertEquals(VALID_USER, sut);
    }
    @Test
    public void updateUser_WithInvalidUser_ThrowsException() {
        when(objectMapper.convertValue(UsuarioDTO.class, Usuario.class)).thenReturn(INVALID_USER);
        when(usuarioRepository.save(INVALID_USER)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> updateUser.executeUpdateUser(VALID_USER.getId(), INVALID_USERDTO))
                .isInstanceOf(RuntimeException.class);

    }

}
