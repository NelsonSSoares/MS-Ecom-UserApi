package nelsonssoares.ecomuserapi.service;


import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import nelsonssoares.ecomuserapi.services.impl.UserServiceImpl;
import nelsonssoares.ecomuserapi.usecases.usuario.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static nelsonssoares.ecomuserapi.commons.UsuarioConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private SaveUser saveUser;

    @Mock
    private GetAllUsers getAllUsers;

    @Mock
    private GetUserById getUserById;

    @Mock
    private UpdateUser updateUser;

    @Mock
    private DeleteUser deleteUser;

    @Mock
    private GetUserByName getUserByName;

    @Mock
    private GetUserByCpf getUserByCpf;

    @Mock
    private GetUserByEmail getUserByEmail;

    @Mock
    private ActiveUser activeUser;

    @Test
    public void createUser_WithValidUser_ShouldReturnUser() {

        when(saveUser.executeSaveUser(VALID_USERDTO)).thenReturn(VALID_USERDTO);
        UsuarioDTO sut = userService.save(VALID_USERDTO).getBody();
        assertThat(sut).isEqualTo(VALID_USERDTO);
    }

    @Test
    public void createUser_WithInvalidUser_ThrowsException() {
        when(saveUser.executeSaveUser(INVALID_USERDTO)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> userService.save(INVALID_USERDTO))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getUser_WithValidId_ShouldReturnUser() {

    }

    @Test
    public void getUser_WithInvalidId_ShouldReturnUser() {


    }
    
}
