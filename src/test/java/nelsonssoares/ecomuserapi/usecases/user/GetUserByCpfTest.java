package nelsonssoares.ecomuserapi.usecases.user;

import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.UserConstants.INCORRECT_USER;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByCpfTest {

    @InjectMocks
    private GetUserByCpf getUserByCpf;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void getUserByCpf_WithValidCpf_ShouldReturnUser() {
        when(usuarioRepository.findByCpf(VALID_USER.getCpf())).thenReturn(Optional.of(VALID_USER));
        Usuario sut = getUserByCpf.executeUserByCpf(VALID_USER.getCpf());
        assertEquals(VALID_USER, sut);
    }

    @Test
    public void getUserByCpf_WithInvalidCpf_ShouldReturnNull() {
        when(usuarioRepository.findByCpf(INCORRECT_USER.getCpf())).thenReturn(Optional.empty());
        Usuario sut = getUserByCpf.executeUserByCpf(INCORRECT_USER.getCpf());
        assertEquals(null, sut);
    }

}
