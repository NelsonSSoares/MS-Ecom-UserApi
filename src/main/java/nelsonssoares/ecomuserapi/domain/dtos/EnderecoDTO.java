package nelsonssoares.ecomuserapi.domain.dtos;

import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;


public record EnderecoDTO(Integer usuarioId,

                          String apelido,

                          String rua,

                          String numero,

                          String complemento,

                          String cep,

                          String bairro,

                          String cidade,

                          String estado,

                          String pais,
                          PerguntaAtivo enderecoPadrao) {
}
