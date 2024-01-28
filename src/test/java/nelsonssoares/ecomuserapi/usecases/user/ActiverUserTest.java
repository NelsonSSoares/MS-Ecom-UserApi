package nelsonssoares.ecomuserapi.usecases.user;

import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActiverUserTest {

    @InjectMocks
    private ActiveUser activeUser;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void activeUser_WithValidId_ShouldReturnVoid() {
        when(usuarioRepository.findById(VALID_USER.getId())).thenReturn(Optional.of(VALID_USER));
        activeUser.executeActiveUser(VALID_USER.getId());
        verify(activeUser.executeActiveUser(VALID_USER.getId()));
    }


}
