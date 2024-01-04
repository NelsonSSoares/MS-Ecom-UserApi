package nelsonssoares.ecomuserapi.usecases.endereco;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.repository.EnderecoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllEndereco {

    private final EnderecoRepository enderecoRepository;
    /*
        VERIFICAR SE USUARIO ESTA ATIVO PARA EXIBIR ENDEREÇO
     */
    public List<Endereco> execute(Pageable paginacao) {
        return enderecoRepository.findAll(paginacao).getContent();
    }
}
