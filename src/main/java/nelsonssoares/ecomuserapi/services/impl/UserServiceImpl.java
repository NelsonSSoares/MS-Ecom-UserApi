package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.services.UserService;
import nelsonssoares.ecomuserapi.usecases.user.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SaveUser saveUser;
    private final GetAllUsers getAllUsers;
    private final GetUserById getUserById;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;
    private final GetUserByName getUserByName;
    private final GetUserByCpf getUserByCpf;
    private final GetUserByEmail getUserByEmail;
    private final ActiveUser activeUser;

    @Override
    public ResponseEntity<UsuarioDTO> save(UsuarioDTO dto) {

        UsuarioDTO usuario = saveUser.executeSaveUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> findAll(Pageable paginacao) {

        List<UsuarioDTO> usuarios = getAllUsers.executeAllUsers(paginacao);

        if(usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> findById(Integer id) {

        Usuario usuario = getUserById.executeUserById(id);

        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else if(usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @Override
    public ResponseEntity<Usuario> updateUser(Integer id, UsuarioDTO userDTO) {

        Usuario usuario = updateUser.executeUpdateUser(id, userDTO);

        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @Override
    public ResponseEntity<Usuario> deleteUser(Integer id) {
        Usuario usuario = deleteUser.executeDeleteUser(id);

        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> findByName(String nome) {

        List<UsuarioDTO> usuarios = getUserByName.executeUserByName(nome);

        return usuarios.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> findByCpf(String cpf) {

        Usuario usuario = getUserByCpf.executeUserByCpf(cpf);

        return usuario == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<Usuario> reactiveUser(Integer id) {

        Usuario usuario = activeUser.executeActiveUser(id);

        return usuario == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<UsuarioDTO> findByEmail(String email) {

        UsuarioDTO usuario = getUserByEmail.executeUserByEmail(email);

        return usuario == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(usuario);
    }
}
