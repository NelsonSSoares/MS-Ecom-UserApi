package nelsonssoares.ecomuserapi.usecases.address;

import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.repository.EnderecoRepository;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.AddressConstants.*;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAddressByUserIdTest {

    @InjectMocks
    private GetAddressByUserId getAddressByUserId;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    public void getAddressByUserId_WithValidId_ShouldReturnAddress() {
        when(enderecoRepository.findAllByUsuarioId(VALID_ADDRESS.getUsuarioId())).thenReturn(VALID_ADDRESS_LIST_RESPONSE.getBody());
        when(usuarioRepository.findById(VALID_ADDRESS.getUsuarioId())).thenReturn(Optional.of(VALID_USER));
        List<Endereco> sut = getAddressByUserId.executeAddressByUserId(VALID_ADDRESS.getUsuarioId());
        assertEquals(VALID_ADDRESS_LIST_RESPONSE.getBody(), sut);
    }

    @Test
    public void getAddressByUserId_WithInvalidUserId_TrowsException() {
       assertThatThrownBy(() -> getAddressByUserId.executeAddressByUserId(INVALID_ADDRESS.getUsuarioId()))
               .isInstanceOf(ResponseStatusException.class);
    }



}
