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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static nelsonssoares.ecomuserapi.commons.UsuarioConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @InjectMocks
    private UserServiceImpl userService;

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
        when(getUserById.executeUserById(VALID_USER.getId())).thenReturn(VALID_USER);
        ResponseEntity<Usuario> sut = userService.findById(VALID_USER.getId());
        assertThat(sut).isEqualTo(VALID_USER_GETRESPONSE);
    }

    @Test
    public void getUser_WithInvalidId_ShouldReturnNotFound() {

        when(getUserById.executeUserById(DESACTIVATED_USER.getId())).thenReturn(INVALID_USER_GETRESPONSE.getBody());
        ResponseEntity<Usuario> sut = userService.findById(DESACTIVATED_USER.getId());
        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getUser_WithValidName_ShouldReturnUser() {
        when(getUserByName.executeUserByName(VALID_USER.getNome())).thenReturn(VALID_USERDTO_GETLISTRESPONSE.getBody());
        ResponseEntity<List<UsuarioDTO>> sut = userService.findByName(VALID_USERDTO.nome());
        assertThat(sut).isEqualTo(VALID_USERDTO_GETLISTRESPONSE);
    }

    @Test
    public void getUser_WithInvalidName_ShouldReturnNotFound() {

        when(getUserByName.executeUserByName(NONEXISTENT_USERDTO.nome())).thenReturn(EMPTY_LIST.getBody());
        assertThatThrownBy(() -> userService.findByName(NONEXISTENT_USERDTO.nome()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getUser_WithValidCpf_ShouldReturnUser() {
        when(getUserByCpf.executeUserByCpf(VALID_USER.getCpf())).thenReturn(VALID_USER_GETRESPONSE.getBody());
        ResponseEntity<Usuario> sut = userService.findByCpf(VALID_USER.getCpf());
        assertThat(sut).isEqualTo(VALID_USER_GETRESPONSE);
    }

    @Test
    public void getUser_WithInvalidCpf_ShouldReturnNotFound() {

        /*TESTE COM ERRO retornando 200 ao inves de 404*/

        when(getUserByCpf.executeUserByCpf(NONEXISTENT_USERDTO.cpf())).thenReturn(INVALID_USER_GETRESPONSE.getBody());
        ResponseEntity<Usuario> sut = userService.findByCpf(NONEXISTENT_USERDTO.cpf());
        assertThat(sut).isEqualTo(INVALID_USER_GETRESPONSE);
    }

    @Test
    public void getUser_WithValidEmail_ShouldReturnUser() {
        when(getUserByEmail.executeUserByEmail(VALID_USER.getEmail())).thenReturn(VALID_USERDTO_GETRESPONSE.getBody());
        ResponseEntity<UsuarioDTO> sut = userService.findByEmail(VALID_USERDTO.email());
        assertThat(sut).isEqualTo(VALID_USERDTO_GETRESPONSE);
    }

    @Test
    public void getUser_WithInvalidEmail_ShouldReturnNotFound() {

        /*TESTE COM ERRO*/

        when(getUserByEmail.executeUserByEmail(NONEXISTENT_USERDTO.email())).thenReturn(INVALID_USERDTO_GETRESPONSE.getBody());
        ResponseEntity<UsuarioDTO> sut = userService.findByEmail(NONEXISTENT_USERDTO.email());
        assertThat(sut).isEqualTo(INVALID_USERDTO_GETRESPONSE);
    }

    @Test
    public void updateUser_WithValidUser_ShouldReturnUser() {
        when(updateUser.executeUpdateUser(VALID_USER.getId(), VALID_USERDTO)).thenReturn(VALID_USER);
        ResponseEntity<Usuario> sut = userService.updateUser(VALID_USER.getId(), VALID_USERDTO);
        assertThat(sut).isEqualTo(VALID_USER_GETRESPONSE);
    }

    @Test
    public void updateUser_WithInvalidUser_ShouldReturnNotFound() {

        /*TESTE COM ERRO*/

        when(updateUser.executeUpdateUser(INVALID_USER.getId(), INVALID_USERDTO)).thenReturn(INVALID_USER);
        ResponseEntity<Usuario> sut = userService.updateUser(INVALID_USER.getId(), INVALID_USERDTO);
        assertThat(sut).isEqualTo(INVALID_USER_GETRESPONSE);
    }

    @Test
    public void deleteUser_WithValidUser_ShouldReturnUser() {
        when(deleteUser.executeDeleteUser(VALID_USER.getId())).thenReturn(VALID_USER);
        ResponseEntity<Usuario> sut = userService.deleteUser(VALID_USER.getId());
        assertThat(sut).isEqualTo(VALID_USER_GETRESPONSE);
    }

    @Test
public void deleteUser_WithInvalidUser_ShouldReturnNotFound() {

        /*TESTE COM ERRO*/

        when(deleteUser.executeDeleteUser(INVALID_USER.getId())).thenReturn(INVALID_USER);
        ResponseEntity<Usuario> sut = userService.deleteUser(INVALID_USER.getId());
        assertThat(sut).isEqualTo(INVALID_USER_GETRESPONSE);
    }

    @Test
    public void reactiveUser_WithValidUser_ShouldReturnUser() {
        when(activeUser.executeActiveUser(VALID_USER.getId())).thenReturn(VALID_USER);
        ResponseEntity<Usuario> sut = userService.reactiveUser(VALID_USER.getId());
        assertThat(sut).isEqualTo(VALID_USER_GETRESPONSE);
    }

    @Test
    public void reactiveUser_WithInvalidUser_ShouldReturnNotFound() {

        /*TESTE COM ERRO*/

        when(activeUser.executeActiveUser(INVALID_USER.getId())).thenReturn(INVALID_USER);
        ResponseEntity<Usuario> sut = userService.reactiveUser(INVALID_USER.getId());
        assertThat(sut).isEqualTo(INVALID_USER_GETRESPONSE);
    }

//    @Test
//    public void getAllUsers_WithValidUser_ShouldReturnUser() {
//        when(getAllUsers.executeAllUsers()).thenReturn(List.of(VALID_USERDTO));
//        ResponseEntity<List<Usuario>> sut = userService.getAllUsers();
//        assertThat(sut).isEqualTo(VALID_USER_GETRESPONSE);
//    }
//
//    @Test
//    public void getAllUsers_WithInvalidUser_ShouldReturnUser() {
//
//        /*TESTE COM ERRO*/
//
//        when(getAllUsers.executeGetAllUsers()).thenReturn(List.of(INVALID_USER));
//        ResponseEntity<List<Usuario>> sut = userService.getAllUsers();
//        assertThat(sut).isEqualTo(INVALID_USER_GETRESPONSE);
//    }
    
}
