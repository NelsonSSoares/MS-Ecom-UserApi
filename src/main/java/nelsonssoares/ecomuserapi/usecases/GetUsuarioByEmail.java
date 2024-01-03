package nelsonssoares.ecomuserapi.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUsuarioByEmail {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioDTO execute(String email) {
        System.out.println(email);
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        System.out.println(user);
        if(user.isEmpty() || user.get().getAtivo().equals(PerguntaAtivo.NAO)){
            return null;
        }
        Usuario usuario = user.get();
        return objectMapper.convertValue(usuario, UsuarioDTO.class);
    }

}
