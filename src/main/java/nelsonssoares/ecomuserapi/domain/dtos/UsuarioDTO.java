package nelsonssoares.ecomuserapi.domain.dtos;

public record UsuarioDTO(
        String nome,
        String sobrenome,
        String cpf,
        String telefone,
        String email) {
}
