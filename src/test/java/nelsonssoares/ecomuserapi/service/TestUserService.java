package nelsonssoares.ecomuserapi.service;


import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import nelsonssoares.ecomuserapi.services.impl.UserServiceImpl;
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

    @Test
    public void createUser_WithValidUser_ShouldReturnUser() {

       when(usuarioRepository.save(VALID_USER)).thenReturn(VALID_USER);

       UsuarioDTO sut = userService.save(VALID_USERDTO).getBody();

       assertThat(sut).isEqualTo(VALID_USERDTO);
    }

    @Test
    public void createUser_WithInvalidUser_ThrowsException() {

        /*
            TESTE COM ERRO
         */

        when(usuarioRepository.save(INVALID_USER)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> userService.save(INVALID_USERDTO))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getUser_WithValidId_ShouldReturnUser() {

        /*
            TESTE COM ERRO
         */

        when(usuarioRepository.findById(VALID_USER.getId())).thenReturn(Optional.of(VALID_USER));

        Usuario sut = userService.findById(VALID_USER.getId()).getBody();

        assertThat(sut).isEqualTo(VALID_USERDTO);
    }

    @Test
    public void getUser_WithInvalidId_ShouldReturnUser() {

        when(usuarioRepository.findById(INVALID_USER.getId())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> sut = userService.findById(INVALID_USER.getId());

        assertThat(sut).isEqualTo(INVALID_USER_GETRESPONSE);
    }
    
}
