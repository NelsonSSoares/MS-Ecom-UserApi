package nelsonssoares.ecomuserapi.entrypoints.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import nelsonssoares.ecomuserapi.commons.AddressConstants;
import nelsonssoares.ecomuserapi.outlayers.entrypoints.AddressController;
import nelsonssoares.ecomuserapi.services.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static nelsonssoares.ecomuserapi.commons.ControllerConstants.*;
import static org.mockito.Mockito.when;

@WebMvcTest(AddressController.class)
public class TestAddressController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    @Test
    public void createAddress_withValidInput_shouldReturnCreated() throws Exception {

        when(addressService.save(AddressConstants.VALID_ADDRESSDTO)).thenReturn(AddressConstants.VALID_ADDRESS_RESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.post(API_BASE_URL +ADDRESS)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.VALID_ADDRESSDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createAddress_withInvalidInput_shouldReturnBadRequest() throws Exception {

        when(addressService.save(AddressConstants.INVALID_ADDRESSDTO)).thenReturn(AddressConstants.INVALID_ADDRESSDTO_RESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.post(API_BASE_URL +ADDRESS)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.INVALID_ADDRESSDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getAddress_withValidId_shouldReturnOk() throws Exception {

        when(addressService.findById(1)).thenReturn(AddressConstants.VALID_ADDRESS_GETRESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.get(API_BASE_URL +ADDRESS_ID, 1)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.VALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAddress_withInvalidId_shouldReturnNotFound() throws Exception {

        when(addressService.findById(0)).thenReturn(AddressConstants.INVALID_ADDRESS_GETRESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.get(API_BASE_URL +ADDRESS_ID, 0)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.INVALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAddress_withInvalidId_shouldReturnBadRequest() throws Exception {

        when(addressService.findById(666)).thenReturn(AddressConstants.INVALID_ADDRESS_GETRESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.get(API_BASE_URL +ADDRESS_ID, 666)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.INVALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteAddress_withValidId_shouldReturnNoContent() throws Exception {

        /*
        TESTE FALHANDO, RETORNANDO 200 AO INVES DE 204
         */

        when(addressService.delete(1)).thenReturn(AddressConstants.VALID_ADDRESS_GETRESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.delete(API_BASE_URL +ADDRESS_ID, 1)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.VALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteAddress_withInvalidId_shouldReturnNotFound() throws Exception {

        when(addressService.delete(0)).thenReturn(AddressConstants.INVALID_ADDRESS_GETRESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.delete(API_BASE_URL +ADDRESS_ID, 0)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.INVALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAddressByUserId_withValidId_shouldReturnOk() throws Exception {

        when(addressService.findByUserId(2)).thenReturn(AddressConstants.VALID_ADDRESS_LIST_RESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.get(API_BASE_URL +ADDRESS + ADDRESS_USER_ID, 2)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.VALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAddressByUserId_withInvalidId_shouldReturnNotFound() throws Exception {

        when(addressService.findByUserId(666)).thenReturn(AddressConstants.INVALID_ADDRESS_LIST_RESPONSE);
        mockMvc.perform(MockMvcRequestBuilders.get(API_BASE_URL +ADDRESS + ADDRESS_USER_ID, 666)
                .contentType(API_PRODUCES)
                .content(objectMapper.writeValueAsString(AddressConstants.INVALID_ADDRESS)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}
