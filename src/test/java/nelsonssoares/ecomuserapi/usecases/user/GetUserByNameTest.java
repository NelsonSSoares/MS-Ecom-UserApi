package nelsonssoares.ecomuserapi.usecases.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static nelsonssoares.ecomuserapi.commons.UserConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByNameTest {

    @InjectMocks
    private GetUserByName getUserByName;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void getUserByName_WithValidName_ShouldReturnUser() {
        when(usuarioRepository.findByNome(VALID_USER.getNome())).thenReturn(VALID_USERLIST_GETRESPONSE.getBody());
        when(objectMapper.convertValue(VALID_USER, UsuarioDTO.class)).thenReturn(VALID_USERDTO);
        List<UsuarioDTO> sut = getUserByName.executeUserByName(VALID_USER.getNome());
        assertEquals(VALID_USERDTO_GETLISTRESPONSE.getBody(), sut);
    }

    @Test
    public void getUserByName_WithInvalidName_ShouldReturnEmptyList() {
        when(usuarioRepository.findByNome(INCORRECT_USER.getNome())).thenReturn(EMPTY_USER_LIST);
        List<UsuarioDTO> sut = getUserByName.executeUserByName(INCORRECT_USER.getNome());
        assertEquals(EMPTY_USER_LIST, sut);
    }

}
