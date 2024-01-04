package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.services.EnderecoService;
import nelsonssoares.ecomuserapi.usecases.endereco.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final SaveEndereco saveEndereco;
    private final GetAllEndereco getAllEndereco;
    private final GetEnderecoByUserId getEnderecoByUserId;
    private final UpdateEndereco updateEndereco;
    private final DeleteEndereco deleteEndereco;

    @Override
    public ResponseEntity<Endereco> salvar(EnderecoDTO endDto) {
        Endereco endereco = saveEndereco.execute(endDto);
        return endereco != null ? ResponseEntity.status(HttpStatus.CREATED).body(endereco) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Endereco> atualizar(Integer id, EnderecoDTO endDto) {
        Endereco endereco = updateEndereco.execute(id, endDto);
        return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Endereco> deletar(Integer id) {
        Endereco endereco = deleteEndereco.execute(id);
        return endereco != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<EnderecoDTO> buscarPorId(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Endereco>> obterTodos(Pageable paginacao) {
        List<Endereco> enderecos =getAllEndereco.execute(paginacao);
        return enderecos != null ? ResponseEntity.ok(enderecos) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Endereco>> obterPorUsuarioId(Integer id) {
        List<Endereco> enderecos = getEnderecoByUserId.execute(id);
        return enderecos != null ? ResponseEntity.ok(enderecos) : ResponseEntity.notFound().build();

    }
}
