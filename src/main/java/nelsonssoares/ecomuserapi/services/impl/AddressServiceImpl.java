package nelsonssoares.ecomuserapi.services.impl;

import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.services.AddressService;
import nelsonssoares.ecomuserapi.usecases.endereco.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final SaveAddress saveAddress;
    private final GetAllAddresses getAllAddresses;
    private final GetAddressByUserId getAddressByUserId;
    private final UpdateAddress updateAddress;
    private final DeleteAddress deleteAddress;
    private final GetAddressById getAddressById;

    @Override
    public ResponseEntity<Endereco> save(EnderecoDTO endDto) {
        Endereco endereco = saveAddress.executeSaveAddress(endDto);
        return endereco != null ? ResponseEntity.status(HttpStatus.CREATED).body(endereco) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Endereco> update(Integer id, EnderecoDTO endDto) {
        Endereco endereco = updateAddress.executeUpdateAddress(id, endDto);
        return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Endereco> delete(Integer id) {
        Endereco endereco = deleteAddress.executeDeteleAddress(id);
        return endereco != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Endereco> findById(Integer id) {
        Endereco endereco = getAddressById.executeAddressById(id);
        return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Endereco>> findAll(Pageable paginacao) {
        List<Endereco> enderecos = getAllAddresses.executeAllAddresses(paginacao);
        return enderecos != null ? ResponseEntity.ok(enderecos) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Endereco>> findByUserId(Integer id) {
        List<Endereco> enderecos = getAddressByUserId.executeAddressByUserId(id);
        return enderecos != null ? ResponseEntity.ok(enderecos) : ResponseEntity.notFound().build();

    }
}
