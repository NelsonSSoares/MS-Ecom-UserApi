package nelsonssoares.ecomuserapi.outlayers.entrypoints;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.EnderecoDTO;
import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import nelsonssoares.ecomuserapi.services.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static nelsonssoares.ecomuserapi.constants.ControllersConstants.*;

@Tag(name = API_TAG)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_BASE_URL+ADDRESS, produces = API_PRODUCES)
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Endereco> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        return enderecoService.salvar(enderecoDTO);
    }

}
