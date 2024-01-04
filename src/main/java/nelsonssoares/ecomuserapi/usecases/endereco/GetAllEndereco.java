package nelsonssoares.ecomuserapi.usecases.endereco;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllEndereco {

    private final EnderecoRepository enderecoRepository;

    public List<Endereco> execute() {
      List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos;
    }
}
