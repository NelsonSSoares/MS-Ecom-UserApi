package nelsonssoares.ecomuserapi.commons;

import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class UsuarioConstants {
    public static final UsuarioDTO VALID_USERDTO = new UsuarioDTO("Nelson","Sousa","998.767.250-76","11949793152","123deoliveira4@gmail.com");
    public static final UsuarioDTO INVALID_USERDTO = new UsuarioDTO(null,"Sousa","998.557.250-76","11949793152","123deoliveira4@gmail.com");
    public static final Usuario VALID_USER = new Usuario(1,"Nelson","Sousa","998.767.250-76","11949793152", LocalDate.now(),LocalDate.now(),"123deoliveira4@gmail.com", PerguntaAtivo.SIM);
    public static final Usuario DESACTIVATED_USER = new Usuario(1,"Nelson","Sousa","998.767.250-76","11949793152", LocalDate.now(),LocalDate.now(),"123deoliveira4@gmail.com", PerguntaAtivo.NAO);
    public static final Usuario INVALID_USER = new Usuario(1,"Nelson","Sousa","998.767.250-76","", LocalDate.now(),LocalDate.now(),"123deoliveira4", PerguntaAtivo.SIM);
    public static final ResponseEntity<UsuarioDTO> VALID_USERDTO_RESPONSE = ResponseEntity.status(HttpStatus.CREATED).body(VALID_USERDTO);

    public static final ResponseEntity<Usuario> VALID_USER_GETRESPONSE = ResponseEntity.status(HttpStatus.OK).body(VALID_USER);

}
