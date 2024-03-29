package nelsonssoares.ecomuserapi.usecases.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.constraints.Constraints;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserByName {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    public List<UsuarioDTO> executeUserByName(String nome) {

        List<Usuario> usuario = usuarioRepository.findByNome(nome);

        List<Usuario> usuariosAtivos = Constraints.usuariosAtivosList(usuario);
        List<UsuarioDTO> usuariosConverted = new ArrayList<>();

        for (Usuario usuarioAtivacted : usuariosAtivos) {
            UsuarioDTO usuarioDto = objectMapper.convertValue(usuarioAtivacted, UsuarioDTO.class);
            usuariosConverted.add(usuarioDto);
        }
        return usuariosConverted;

    }
}
