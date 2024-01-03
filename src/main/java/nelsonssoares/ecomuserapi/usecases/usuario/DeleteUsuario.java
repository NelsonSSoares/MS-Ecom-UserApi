package nelsonssoares.ecomuserapi.usecases.usuario;


import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteUsuario {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario execute(Integer id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            return null;
        }

        Usuario user  = usuario.get();
        user.setAtivo(PerguntaAtivo.NAO);
        System.out.println(user);
        return user;
    }
}
