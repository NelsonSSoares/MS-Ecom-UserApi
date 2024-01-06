package nelsonssoares.ecomuserapi.services;

import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UsuarioDTO> save(UsuarioDTO dto);

    ResponseEntity<List<UsuarioDTO>> findAll(Pageable paginacao);

    ResponseEntity<Usuario> findById(Integer id);

    ResponseEntity<Usuario> updateUser(Integer id, UsuarioDTO userDTO);

    ResponseEntity<Usuario> deleteUser(Integer id);

    ResponseEntity<List<UsuarioDTO>> findByName(String nome);

    ResponseEntity<Usuario> findByCpf(String cpf);

    ResponseEntity<Usuario> reactiveUser(Integer id);

    ResponseEntity<UsuarioDTO> findByEmail(String email);

}
