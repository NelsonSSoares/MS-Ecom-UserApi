package nelsonssoares.ecomuserapi.usecases.address;


import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.repository.EnderecoRepository;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.AddressConstants.VALID_ADDRESS;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteAddressTest {

    @InjectMocks
    private DeleteAddress deleteAddress;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    public void deleteAddress_WithValidAddress_ShouldReturnAddress() {
        when(enderecoRepository.findById(VALID_ADDRESS.getId())).thenReturn(Optional.of(VALID_ADDRESS));
        when(usuarioRepository.findById(VALID_ADDRESS.getUsuarioId())).thenReturn(Optional.of(VALID_USER));
        Endereco sut = deleteAddress.executeDeteleAddress(VALID_ADDRESS.getId());
        assertThat(sut).isEqualTo(VALID_ADDRESS);
    }

    @Test
    public void deleteAddress_WithInvalidAddress_ShouldReturnNull() {
        when(enderecoRepository.findById(VALID_ADDRESS.getId())).thenReturn(Optional.empty());
        Endereco sut = deleteAddress.executeDeteleAddress(VALID_ADDRESS.getId());
        assertThat(sut).isNull();
    }

}
