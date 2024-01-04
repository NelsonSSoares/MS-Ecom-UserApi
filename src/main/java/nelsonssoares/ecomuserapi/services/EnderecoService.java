package nelsonssoares.ecomuserapi.services;

import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnderecoService {
    ResponseEntity<Endereco> salvar(EnderecoDTO endDto);

    ResponseEntity<Endereco> atualizar(Integer id, EnderecoDTO endDto);

    ResponseEntity<Endereco> deletar(Integer id);

    ResponseEntity<EnderecoDTO> buscarPorId(Integer id);

    ResponseEntity<List<Endereco>> obterTodos(Pageable paginacao);


    ResponseEntity<List<Endereco>> obterPorUsuarioId(Integer id);
}
