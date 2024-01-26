package nelsonssoares.ecomuserapi.commons;

import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UsuarioConstants {
    public static final UsuarioDTO VALID_USERDTO = new UsuarioDTO("Nelson","Sousa","998.767.250-76","11949793152","123deoliveira4@gmail.com");
    public static final UsuarioDTO INVALID_USERDTO = new UsuarioDTO(null,"Sousa",null,"11949793152","");
    public static final Usuario VALID_USER = new Usuario(1,"Nelson","Sousa","998.767.250-76","11949793152", LocalDate.now(),LocalDate.now(),"123deoliveira4@gmail.com", PerguntaAtivo.SIM);
    public static final Usuario DESACTIVATED_USER = new Usuario(1,"Nelson","Sousa","998.767.250-76","11949793152", LocalDate.now(),LocalDate.now(),"123deoliveira4@gmail.com", PerguntaAtivo.NAO);
    public static final Usuario INVALID_USER = new Usuario(null,"Nelson","Sousa","998.767.250-76","", LocalDate.now(),LocalDate.now(),"123deoliveira4", PerguntaAtivo.NAO);

    public static final Usuario INVALID_USER_UPDATE = new Usuario(1,"Nelson","Sousa","998.767.250-76","", LocalDate.now(),LocalDate.now(),"123deoliveira4", PerguntaAtivo.SIM);

    public static final UsuarioDTO NONEXISTENT_USERDTO = new UsuarioDTO("Mohamed","Sousa","998.666.250-76","11949793152","123@gmail.com");

    public static final ResponseEntity<UsuarioDTO> VALID_USERDTO_RESPONSE = ResponseEntity.status(HttpStatus.CREATED).body(VALID_USERDTO);
    public static final ResponseEntity<List<UsuarioDTO>> VALID_USERDTO_GETLISTRESPONSE = ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(VALID_USERDTO));
    public static final ResponseEntity<List<UsuarioDTO>> EMPTY_LIST = ResponseEntity.noContent().build();
    public static final ResponseEntity<Usuario> VALID_USER_GETRESPONSE = ResponseEntity.status(HttpStatus.OK).body(VALID_USER);
    public static final ResponseEntity<UsuarioDTO> VALID_USERDTO_GETRESPONSE = ResponseEntity.status(HttpStatus.OK).body(VALID_USERDTO);

    public static final ResponseEntity<UsuarioDTO> INVALID_USERDTO_GETRESPONSE = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    public static final ResponseEntity<Usuario> INVALID_USER_GETRESPONSE = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    public static final ResponseEntity<Usuario> NO_CONTENT = ResponseEntity.noContent().build();


}
