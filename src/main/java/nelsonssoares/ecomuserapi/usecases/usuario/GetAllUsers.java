package nelsonssoares.ecomuserapi.usecases.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.constraints.Constraints;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsers {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public List<UsuarioDTO> executeAllUsers(Pageable paginacao) {

        Page<Usuario> usuarios =  usuarioRepository.findAll(paginacao);
        Page<Usuario> usuariosAtivos = Constraints.usuariosAtivosList(usuarios);
        List<UsuarioDTO> usuariosConverted = new ArrayList<>();
        for (Usuario usuarioAtivacted : usuariosAtivos) {
            UsuarioDTO usuarioDto = objectMapper.convertValue(usuarioAtivacted, UsuarioDTO.class);
            usuariosConverted.add(usuarioDto);
        }

        return usuariosConverted.isEmpty() ? null : usuariosConverted;
    }
}
