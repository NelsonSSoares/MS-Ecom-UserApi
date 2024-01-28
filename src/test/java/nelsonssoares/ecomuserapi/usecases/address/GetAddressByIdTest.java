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

import static nelsonssoares.ecomuserapi.commons.AddressConstants.INVALID_ADDRESS;
import static nelsonssoares.ecomuserapi.commons.AddressConstants.VALID_ADDRESS;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAddressByIdTest {

    @InjectMocks
    private GetAddressById getAddressById;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    public void getAddressById_WithValidId_ShouldReturnAddress() {
        when(enderecoRepository.findById(VALID_ADDRESS.getId())).thenReturn(Optional.of(VALID_ADDRESS));
        when(usuarioRepository.findById(VALID_ADDRESS.getUsuarioId())).thenReturn(Optional.of(VALID_USER));
        Endereco sut = getAddressById.executeAddressById(VALID_ADDRESS.getId());
        assertEquals(VALID_ADDRESS, sut);
    }

    @Test
    public void getAddressById_WithInvalidId_ShouldReturnNotFound() {
        when(enderecoRepository.findById(INVALID_ADDRESS.getId())).thenReturn(Optional.empty());
        Endereco sut = getAddressById.executeAddressById(INVALID_ADDRESS.getId());
        assertEquals(null, sut);
    }


}
