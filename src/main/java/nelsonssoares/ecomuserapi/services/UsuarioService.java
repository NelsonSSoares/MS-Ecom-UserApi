package nelsonssoares.ecomuserapi.services;

import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    ResponseEntity<UsuarioDTO> salvar (UsuarioDTO dto);

    ResponseEntity<List<UsuarioDTO>> buscarTodos(Pageable paginacao);

    ResponseEntity<Usuario> buscarPorId(Integer id);

    ResponseEntity<Usuario> atualizarUsuario(Integer id, UsuarioDTO userDTO);

    ResponseEntity<Usuario> deletarUsuario(Integer id);

    ResponseEntity<List<Usuario>> encontrarPorNome(String nome);

    ResponseEntity<Usuario> encontrarPorCpf(String cpf);

    ResponseEntity<Usuario> reativarUsuario(Integer id);

    ResponseEntity<Usuario> findByEmail(String email);

}
