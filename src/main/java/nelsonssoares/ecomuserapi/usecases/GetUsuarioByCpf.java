package nelsonssoares.ecomuserapi.usecases;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUsuarioByCpf {

private final UsuarioRepository usuarioRepository;

    public Usuario execute(String cpf) {

        Optional<Usuario> user = usuarioRepository.findByCpf(cpf);

        if(user.isEmpty() || user.get().getAtivo().equals(PerguntaAtivo.NAO)){
            return null;
        }
        return user.get();
    }

}
