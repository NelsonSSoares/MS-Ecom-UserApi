package nelsonssoares.ecomuserapi.outlayers.entrypoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static nelsonssoares.ecomuserapi.commons.constants.ControllersConstants.*;

@Tag(name = API_TAG, description = API_DESCRIPTION)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_BASE_URL, produces = API_PRODUCES)
//@SecurityRequirement(name = API_SECURITY_REQUIREMENT)
public class UserController {

    private final UserService userService;


    @Operation(summary = "Metodo para cadastrar novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadstrado com sucesso!!"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos!"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar usuário!"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO dto) {
        //Retornar entidade Usuario com ID
        return userService.save(dto);
    }



    @Operation(summary = "Metodo para atualizar usuário existente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso!!"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado!"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar usuário!"),
    })
    @PutMapping(value = ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") Integer id, @RequestBody @Valid UsuarioDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }



    @Operation(summary = "Metodo para reativar usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário reativado com sucesso!!"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "409", description = "Usuário já ativo!"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar usuário!"),
    })
    @PutMapping(value = ACTIVE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Usuario> activeUser(@PathVariable("id") Integer id) {
        return userService.reactiveUser(id);
    }



    @Operation(summary = "Metodo para excluir usuário existente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluido com sucesso!!"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado!"),
    })
    @DeleteMapping(value = ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") Integer id) {
        //Retornando 409 apos deletar usuario
        return userService.deleteUser(id);
    }


    @Operation(summary = "Busca todos os Usuários cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar usuário!")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UsuarioDTO>> findAll(Pageable paginacao) {
        return userService.findAll(paginacao);
    }



    @Operation(summary = "Busca usuario cadastrado por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar usuário!")
    })
    @GetMapping(value = ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @Operation(summary = "Busca usuarios cadastrados por Nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar usuário!")
    })
    @GetMapping(value = NAME)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UsuarioDTO>> findByName(@RequestParam("nome") String nome) {
        return userService.findByName(nome);
    }


    @Operation(summary = "Busca usuario cadastrado por CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar usuário!")
    })
    @GetMapping(value = CPF)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Usuario> findByCPF(@PathVariable("cpf") String cpf) {
        return userService.findByCpf(cpf);
    }


    @Operation(summary = "Busca usuario cadastrado por Email", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado!"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar usuário!")
    })
    @GetMapping(value = EMAIL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

}
