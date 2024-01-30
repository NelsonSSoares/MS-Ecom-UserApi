package nelsonssoares.ecomuserapi.usecases.address;


import com.fasterxml.jackson.databind.ObjectMapper;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.repository.EnderecoRepository;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.AddressConstants.*;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateAddressTest {

    @InjectMocks
    private UpdateAddress updateAddress;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void executeUpdateAddress_WithValidAddress_ShouldReturnAddress() {
        when(enderecoRepository.save(VALID_ADDRESS)).thenReturn(VALID_ADDRESS);
        when(usuarioRepository.findById(VALID_ADDRESSDTO.usuarioId())).thenReturn(Optional.of(VALID_USER));
        when(objectMapper.convertValue(VALID_ADDRESSDTO, Endereco.class)).thenReturn(VALID_ADDRESS);
        Endereco sut = updateAddress.executeUpdateAddress(VALID_ADDRESS.getId(), VALID_ADDRESSDTO);
        assertThat(sut).isEqualTo(VALID_ADDRESS);
    }

    @Test
    public void executeUpdateAddress_WithInvalidAddress_ThrowsException() {
        when(enderecoRepository.save(INVALID_ADDRESS)).thenThrow(RuntimeException.class);
        when(usuarioRepository.findById(INVALID_ADDRESSDTO.usuarioId())).thenReturn(Optional.of(VALID_USER));
        when(objectMapper.convertValue(INVALID_ADDRESSDTO, Endereco.class)).thenReturn(INVALID_ADDRESS);
        assertThatThrownBy(() -> updateAddress.executeUpdateAddress(INVALID_ADDRESS.getId(), INVALID_ADDRESSDTO))
                .isInstanceOf(RuntimeException.class);
    }
}
