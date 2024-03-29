package nelsonssoares.ecomuserapi.usecases.user;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUser {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario executeUpdateUser(Integer id, UsuarioDTO userDTO) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            return null;
        }
        Usuario user = usuario.get();
        user.setDataModificacao(LocalDate.now());
        user.setNome(userDTO.nome());
        user.setSobrenome(userDTO.sobrenome());
        user.setEmail(userDTO.email());
        user.setTelefone(userDTO.telefone());

        return usuarioRepository.save(user);
    }

}
