package nelsonssoares.ecomuserapi.outlayers.entrypoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nelsonssoares.ecomuserapi.domain.dtos.UsuarioDTO;
import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import nelsonssoares.ecomuserapi.services.UsuarioService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static nelsonssoares.ecomuserapi.constants.ControllersConstants.*;

@Tag(name = API_TAG, description = API_DESCRIPTIONgit )
@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_BASE_URL, produces = API_PRODUCES)
//@SecurityRequirement(name = API_SECURITY_REQUIREMENT)
public class UsuarioController {

    private final UsuarioService usuarioService;


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
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody  @Valid UsuarioDTO dto) {
        //Retornar entidade Usuario com ID
        return usuarioService.salvar(dto);
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
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Integer id, @RequestBody @Valid UsuarioDTO userDTO) {
        return usuarioService.atualizarUsuario(id, userDTO);
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
    public ResponseEntity<Usuario> ativarUsuario(@PathVariable("id") Integer id) {
        return usuarioService.reativarUsuario(id);
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
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable("id") Integer id) {
        //Retornando 409 apos deletar usuario
        return usuarioService.deletarUsuario(id);
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
    public ResponseEntity<List<UsuarioDTO>> buscarTodos(Pageable paginacao) {
        return usuarioService.buscarTodos(paginacao);
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
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Integer id) {
        return usuarioService.buscarPorId(id);
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
    public ResponseEntity<List<UsuarioDTO>> buscarPorNome(@PathVariable("nome") String nome) {
        return usuarioService.encontrarPorNome(nome);
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
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable("cpf") String cpf) {
        //Retornar 409 quando usuario ja estiver ativo
        return usuarioService.encontrarPorCpf(cpf);
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
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable("email") String email) {
        return usuarioService.findByEmail(email);
    }

}
