package nelsonssoares.ecomuserapi.service;

import nelsonssoares.ecomuserapi.commons.AddressConstants;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.services.impl.AddressServiceImpl;
import nelsonssoares.ecomuserapi.usecases.address.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static nelsonssoares.ecomuserapi.commons.AddressConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestAddressService {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private SaveAddress saveAddress;

    @Mock
    private GetAddressById getAddressById;

    @Mock
    private UpdateAddress updateAddress;

    @Mock
    private DeleteAddress deleteAddress;

    @Mock
    private GetAddressByUserId getAddressByUserId;

    @Test
    public void createAddress_WithValidAddress_ShouldReturnAddress() {
        when(saveAddress.executeSaveAddress(VALID_ADDRESSDTO)).thenReturn(VALID_ADDRESS);
        Endereco sut = addressService.save(VALID_ADDRESSDTO).getBody();
        assertThat(sut).isEqualTo(VALID_ADDRESS);
    }

    @Test
    public void createAddress_WithInvalidAddress_ThrowsException() {
        when(saveAddress.executeSaveAddress(AddressConstants.INVALID_ADDRESSDTO)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> addressService.save(AddressConstants.INVALID_ADDRESSDTO))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void updateAddress_WithValidAddress_ShouldReturnAddress() {
        when(updateAddress.executeUpdateAddress(VALID_ADDRESS.getId(), VALID_ADDRESSDTO)).thenReturn(VALID_ADDRESS_RESPONSE.getBody());
        Endereco sut = addressService.update(VALID_ADDRESS.getId(), VALID_ADDRESSDTO).getBody();
        assertThat(sut).isEqualTo(VALID_ADDRESS);

    }



    @Test
    public void updateAddress_WithInvalidAddress_SholdReturnBadRequest() {

        when(updateAddress.executeUpdateAddress(INVALID_ADDRESS.getId(), INVALID_ADDRESSDTO)).thenReturn(INVALID_ADDRESSDTO_RESPONSE.getBody());
        Endereco sut = addressService.update(INVALID_ADDRESS.getId(), INVALID_ADDRESSDTO).getBody();
        assertThat(sut).isEqualTo(INVALID_ADDRESSDTO_RESPONSE.getBody());

    }

    @Test
    public void deleteAddress_WithValidAddress_ShouldReturnNoContent() {
        when(deleteAddress.executeDeteleAddress(VALID_ADDRESS.getId())).thenReturn(NO_CONTENT.getBody());
        Endereco sut = addressService.delete(VALID_ADDRESS.getId()).getBody();
        assertThat(sut).isEqualTo(NO_CONTENT.getBody());
    }

    @Test
    public void deleteAddress_WithInvalidAddress_ShouldReturnNotFound() {
        when(deleteAddress.executeDeteleAddress(INVALID_ADDRESS.getId())).thenReturn(NOT_FOUND.getBody());
        Endereco sut = addressService.delete(INVALID_ADDRESS.getId()).getBody();
        assertThat(sut).isEqualTo(NOT_FOUND.getBody());
    }

    @Test
    public void getAddressById_WithValidAddress_ShouldReturnAddress() {
        when(getAddressById.executeAddressById(VALID_ADDRESS.getId())).thenReturn(VALID_ADDRESS_GETRESPONSE.getBody());
        Endereco sut = addressService.findById(VALID_ADDRESS.getId()).getBody();
        assertThat(sut).isEqualTo(VALID_ADDRESS_GETRESPONSE.getBody());
    }

    @Test
    public void getAddressById_WithInvalidAddress_ShouldReturnNotFound() {
        when(getAddressById.executeAddressById(INVALID_ADDRESS.getId())).thenReturn(INVALID_ADDRESS_GETRESPONSE.getBody());
        Endereco sut = addressService.findById(INVALID_ADDRESS.getId()).getBody();
        assertThat(sut).isEqualTo(INVALID_ADDRESS_GETRESPONSE.getBody());
    }

    @Test
    public void getAddressByUserId_WithValidAddress_ShouldReturnAddress() {
        when(getAddressByUserId.executeAddressByUserId(VALID_ADDRESS.getUsuarioId())).thenReturn(VALID_ADDRESS_LIST_RESPONSE.getBody());
        List<Endereco> sut = addressService.findByUserId(VALID_ADDRESS.getUsuarioId()).getBody();
        assertThat(sut).isEqualTo(VALID_ADDRESS_LIST_RESPONSE.getBody());
    }

    @Test
    public void getAddressByUserId_WithInvalidAddress_ShouldReturnNotFound() {
        when(getAddressByUserId.executeAddressByUserId(INVALID_ADDRESS.getUsuarioId())).thenReturn(INVALID_ADDRESS_LIST_RESPONSE.getBody());
        List<Endereco> sut = addressService.findByUserId(INVALID_ADDRESS.getUsuarioId()).getBody();
        assertThat(sut).isEqualTo(INVALID_ADDRESS_LIST_RESPONSE.getBody());
    }



}
