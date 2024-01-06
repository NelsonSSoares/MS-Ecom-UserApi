package nelsonssoares.ecomuserapi.usecases.usuario;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserById {

    private final UsuarioRepository usuarioRepository;

    public Usuario executeUserById(Integer id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()) {
            return null;
        }
        Usuario user = usuario.get();
        return user;
    }
}
