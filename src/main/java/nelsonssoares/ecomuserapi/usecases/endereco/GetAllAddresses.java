package nelsonssoares.ecomuserapi.usecases.endereco;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import nelsonssoares.ecomuserapi.domain.repository.EnderecoRepository;
import nelsonssoares.ecomuserapi.domain.repository.UsuarioRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAddresses {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Endereco> executeAllAddresses(Pageable paginacao) {

        List<Usuario> usuarios = usuarioRepository.findAll();

        List<Endereco> enderecos = enderecoRepository.findAll(paginacao).getContent();

        List<Endereco> enderecosAtivos = new ArrayList<>();


        for(Usuario usuario : usuarios){
            if(usuario.getAtivo().equals(PerguntaAtivo.SIM)){
                for(Endereco endereco : enderecos){
                    if(endereco.getUsuarioId().equals(usuario.getId())){
                        enderecosAtivos.add(endereco);
                    }
                }
            }
        }

        return enderecosAtivos;
    }
}
