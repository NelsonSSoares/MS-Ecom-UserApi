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

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.AddressConstants.*;
import static nelsonssoares.ecomuserapi.commons.UserConstants.VALID_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveAddressTest {

    @InjectMocks
    private SaveAddress saveAddress;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void saveAddress_WithValidAddress_ShouldReturnAddress() {
        when(enderecoRepository.save(VALID_ADDRESS)).thenReturn(VALID_ADDRESS);
        when(objectMapper.convertValue(VALID_ADDRESSDTO, Endereco.class)).thenReturn(VALID_ADDRESS);
        when(usuarioRepository.findById(VALID_ADDRESSDTO.usuarioId())).thenReturn(Optional.of(VALID_USER));
        Endereco sut = saveAddress.executeSaveAddress(VALID_ADDRESSDTO);
        assertEquals(VALID_ADDRESS, sut);
    }

    @Test
    public void saveAddress_WithInvalidAddress_TrowsException() {
        when(enderecoRepository.save(INVALID_ADDRESS)).thenThrow(RuntimeException.class);
        when(objectMapper.convertValue(INVALID_ADDRESSDTO, Endereco.class)).thenReturn(INVALID_ADDRESS);
        when(usuarioRepository.findById(INVALID_ADDRESSDTO.usuarioId())).thenReturn(Optional.of(VALID_USER));
        assertThrows(RuntimeException.class, () -> saveAddress.executeSaveAddress(INVALID_ADDRESSDTO));
    }

}
