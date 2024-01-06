package nelsonssoares.ecomuserapi.services;

import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    ResponseEntity<Endereco> save(EnderecoDTO endDto);

    ResponseEntity<Endereco> update(Integer id, EnderecoDTO endDto);

    ResponseEntity<Endereco> delete(Integer id);

    ResponseEntity<Endereco> findById(Integer id);

    ResponseEntity<List<Endereco>> findAll(Pageable paginacao);


    ResponseEntity<List<Endereco>> findByUserId(Integer id);
}
