package nelsonssoares.ecomuserapi.usecases.user;

import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTest {

    @InjectMocks
    private DeleteUser deleteUser;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    public void deleteUser_WithValidId_ShouldReturnVoid() {
        when(usuarioRepository.findById(VALID_USER.getId())).thenReturn(Optional.of(VALID_USER));
        deleteUser.executeDeleteUser(VALID_USER.getId());
        verify(deleteUser.executeDeleteUser(VALID_USER.getId()));

    }

    @Test
    @Transactional
    public void deleteUser_WithInvalidId_ShouldReturnVoid() {
        when(usuarioRepository.findById(VALID_USER.getId())).thenReturn(Optional.empty());
        deleteUser.executeDeleteUser(VALID_USER.getId());
        verify(deleteUser.executeDeleteUser(VALID_USER.getId()));
    }
}
