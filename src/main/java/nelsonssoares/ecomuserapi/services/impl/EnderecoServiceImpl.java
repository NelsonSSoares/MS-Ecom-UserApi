package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.services.EnderecoService;
import nelsonssoares.ecomuserapi.usecases.endereco.SaveEndereco;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final SaveEndereco saveEndereco;

    @Override
    public ResponseEntity<Endereco> salvar(EnderecoDTO endDto) {
        Endereco endereco = saveEndereco.execute(endDto);
        return endereco != null ? ResponseEntity.status(HttpStatus.CREATED).body(endereco) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Endereco> atualizar(Integer id, EnderecoDTO endDto) {
        return null;
    }

    @Override
    public ResponseEntity<Endereco> deletar(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<EnderecoDTO> buscarPorId(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<Endereco>> obterTodos(Pageable paginacao) {
        return null;
    }

    @Override
    public ResponseEntity<List<Endereco>> obterPorUsuarioId(Integer id) {
        return null;
    }
}
