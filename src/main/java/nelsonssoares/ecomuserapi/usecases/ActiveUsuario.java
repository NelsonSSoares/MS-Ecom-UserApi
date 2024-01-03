package nelsonssoares.ecomuserapi.usecases;


import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActiveUsuario {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario execute(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            return null;
        }
        Usuario user = usuario.get();
        user.setDataModificacao(LocalDate.now());
        user.setAtivo(PerguntaAtivo.SIM);
        return usuarioRepository.save(user);
    }
}
