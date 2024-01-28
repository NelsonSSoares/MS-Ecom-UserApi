package nelsonssoares.ecomuserapi.commons;

import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.domain.entities.enums.Pais;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AddressConstants {
    public static final EnderecoDTO VALID_ADDRESSDTO = new EnderecoDTO(1,"","Rua dos Bobos","0","","02041-060","JD. São Paulo","São Paulo","SP","BRASIL", PerguntaAtivo.SIM);
    public static final EnderecoDTO INVALID_ADDRESSDTO = new EnderecoDTO(0,"","","0","","02041-060","JD. São Paulo","São Paulo","SP","Brasil", PerguntaAtivo.SIM);

    public static final Endereco VALID_ADDRESS = new Endereco(402,"","Santo Antonio","928","Adega de Bebidas","01314-001","Bela-Vista","São Paulo","SP", Pais.BRASIL, 1, PerguntaAtivo.NAO);

    public static final Endereco INVALID_ADDRESS = new Endereco(0,"","","928","Adega de Bebidas","01314-001","Bela-Vista","São Paulo","SP", Pais.BRASIL, 666, PerguntaAtivo.NAO);

    public static final ResponseEntity<Endereco> VALID_ADDRESS_RESPONSE = ResponseEntity.status(HttpStatus.CREATED).body(VALID_ADDRESS);

    public static final ResponseEntity<Endereco> VALID_ADDRESS_GETRESPONSE = ResponseEntity.status(HttpStatus.OK).body(VALID_ADDRESS);

    public static final ResponseEntity<Endereco> NO_CONTENT = ResponseEntity.noContent().build();

    public static final ResponseEntity<Endereco> INVALID_ADDRESS_GETRESPONSE = ResponseEntity.status(HttpStatus.NOT_FOUND).body(INVALID_ADDRESS);

    public static final ResponseEntity<EnderecoDTO> VALID_ADDRESSDTO_RESPONSE = ResponseEntity.status(HttpStatus.CREATED).body(VALID_ADDRESSDTO);

    public static final ResponseEntity<Endereco> INVALID_ADDRESSDTO_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ADDRESS);

    public static final ResponseEntity<List<Endereco>> VALID_ADDRESS_LIST_RESPONSE = ResponseEntity.status(HttpStatus.OK).body(List.of(VALID_ADDRESS));

    public static final ResponseEntity<List<Endereco>> INVALID_ADDRESS_LIST_RESPONSE = ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(INVALID_ADDRESS));

    public static final ResponseEntity<Endereco> NOT_FOUND = ResponseEntity.notFound().build();

    public static final ResponseEntity<List<Endereco>> LIST_ADDRESS = ResponseEntity.status(HttpStatus.OK).body(List.of(VALID_ADDRESS));

}
