package nelsonssoares.ecomuserapi.usecases.user;

import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.UserConstants.INCORRECT_USER;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetUserByIdTest {

    @InjectMocks
    private GetUserById getByUserId;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void getUserById_WithValidId_ShouldReturnUser() {
        when(usuarioRepository.findById(VALID_USER.getId())).thenReturn(Optional.of(VALID_USER));
        Usuario sut = getByUserId.executeUserById(VALID_USER.getId());
        assertEquals(VALID_USER, sut);
    }

    @Test
    public void getUserById_WithInvalidId_ShouldReturnNull() {
        when(usuarioRepository.findById(INCORRECT_USER.getId())).thenReturn(Optional.empty());
        Usuario sut = getByUserId.executeUserById(INCORRECT_USER.getId());
        assertEquals(null, sut);
    }
}
