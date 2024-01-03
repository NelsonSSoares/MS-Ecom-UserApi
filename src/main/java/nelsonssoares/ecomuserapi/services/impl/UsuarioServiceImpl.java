package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.services.UsuarioService;
import nelsonssoares.ecomuserapi.usecases.usuario.*;
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
    private final UpdateUsuario updateUsuario;
    private final DeleteUsuario deleteUsuario;
    private final GetUsuarioByName getUsuarioByName;
    private final GetUsuarioByCpf getUsuarioByCpf;
    private final GetUsuarioByEmail getUsuarioByEmail;
    private final ActiveUsuario activeUsuario;

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

        Usuario usuario = getUsuarioById.execute(id);

        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else if(usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @Override
    public ResponseEntity<Usuario> atualizarUsuario(Integer id, UsuarioDTO userDTO) {

        Usuario usuario = updateUsuario.execute(id, userDTO);

        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @Override
    public ResponseEntity<Usuario> deletarUsuario(Integer id) {
        Usuario usuario = deleteUsuario.execute(id);

        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> encontrarPorNome(String nome) {

        List<UsuarioDTO> usuarios = getUsuarioByName.execute(nome);

        return usuarios.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> encontrarPorCpf(String cpf) {

        Usuario usuario = getUsuarioByCpf.execute(cpf);

        return usuario == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<Usuario> reativarUsuario(Integer id) {

        Usuario usuario = activeUsuario.execute(id);

        return usuario == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<UsuarioDTO> findByEmail(String email) {

        UsuarioDTO usuario = getUsuarioByEmail.execute(email);

        return usuario == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(usuario);
    }
}
