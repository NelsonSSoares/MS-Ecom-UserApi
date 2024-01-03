package nelsonssoares.ecomuserapi.usecases.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.constraints.Constraints;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveUsuario {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public UsuarioDTO execute(UsuarioDTO user) {

        List<Usuario> usuarios = usuarioRepository.findAll();

        boolean result = Constraints.cpfExistente(usuarios, user);
        System.out.println(result);

        if(result == true) {
            throw new RuntimeException("CPF Já cadastrado! " + HttpStatus.CONFLICT);
        }

        Usuario usuario = objectMapper.convertValue(user, Usuario.class);

        usuario.setAtivo(PerguntaAtivo.SIM);
        usuario.setDataCriacao(LocalDate.now());
        usuario.setDataModificacao(LocalDate.now());

        Usuario usuarioSaved = usuarioRepository.save(usuario);

        return objectMapper.convertValue(usuarioSaved, UsuarioDTO.class);

    }
}
