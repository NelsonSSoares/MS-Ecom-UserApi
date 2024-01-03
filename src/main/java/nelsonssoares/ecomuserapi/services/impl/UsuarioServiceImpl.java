package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.services.UsuarioService;
import nelsonssoares.ecomuserapi.usecases.GetAllUsuarios;
import nelsonssoares.ecomuserapi.usecases.GetUsuarioById;
import nelsonssoares.ecomuserapi.usecases.SaveUsuario;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final SaveUsuario saveUsuario;
    private final GetAllUsuarios getAllUsuarios;
    private final GetUsuarioById getUsuarioById;
    @Override
    public ResponseEntity<UsuarioDTO> salvar(UsuarioDTO dto) {

        UsuarioDTO usuario = saveUsuario.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(Pageable paginacao) {

        List<UsuarioDTO> usuarios = getAllUsuarios.execute(paginacao);

        if(usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> buscarPorId(Integer id) {
        System.out.println(id);
        Usuario usuario = getUsuarioById.execute(id);
        System.out.println(usuario);
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else if(usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @Override
    public ResponseEntity<Usuario> atualizarUsuario(Integer id, UsuarioDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Usuario> deletarUsuario(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Usuario>> encontrarPorNome(String nome) {
        return null;
    }

    @Override
    public ResponseEntity<Usuario> encontrarPorCpf(String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<Usuario> reativarUsuario(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Usuario> findByEmail(String email) {
        return null;
    }
}
