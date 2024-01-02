package nelsonssoares.ecomuserapi.constraints;

import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

public class Constraints {

    public static Page<Usuario> usuariosAtivosList(Page<Usuario> usuarios) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
                iterator.remove();
            }
        }
        return usuarios;
    }

    public static boolean cpfExistente(List<Usuario> usuarios, UsuarioDTO usuario){

        Iterator<Usuario> iterator = usuarios.iterator();
        while(iterator.hasNext()) {
            Usuario user = iterator.next();
            if(user.getCpf().equals(usuario.cpf())) {
                return true;
            }
        }
        return false;
    }

    public static List<Usuario> usuariosAtivosList(List<Usuario> usuarios) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getAtivo().equals(PerguntaAtivo.NAO)) {
                iterator.remove();
            }
        }
        return usuarios;
    }


}
