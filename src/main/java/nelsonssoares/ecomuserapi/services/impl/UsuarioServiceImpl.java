package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.constraints.Constraints;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import nelsonssoares.ecomuserapi.services.UsuarioService;
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
    private final UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<UsuarioDTO> salvar(UsuarioDTO dto) {

        List<Usuario> usuarios = usuarioRepository.findAll();

        boolean result = Constraints.cpfExistente(usuarios, dto);
        System.out.println(result);

        if(result == true) {
            throw new RuntimeException("CPF Já cadastrado! " + HttpStatus.CONFLICT);
        }
        UsuarioDTO usuario = saveUsuario.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(Pageable paginacao) {
        return null;
    }

    @Override
    public ResponseEntity<Usuario> buscarPorId(Integer id) {
        return null;
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
