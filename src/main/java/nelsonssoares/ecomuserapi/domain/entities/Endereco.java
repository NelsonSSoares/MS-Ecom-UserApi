package nelsonssoares.ecomuserapi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.enums.Pais;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 45)
    private String apelido;

    @NotEmpty(message = "${}")
    @Column(length = 45)
    private String rua;

    @NotEmpty(message = "${}")
    @Column(length = 45)
    private String numero;

    @Column(length = 45)
    private String complemento;

    @NotEmpty(message ="${}")
    private String cep;

    @NotEmpty(message = "${}")
    @Column(length = 45)
    private String bairro;

    @NotEmpty(message = "${}")
    @Column(length = 45)
    private String cidade;

    @NotEmpty(message = "Deve conter somente 2 caracteres ex: SP")
    @Column(length = 2)
    private String estado;

    @NotNull(message = "${}")
    @Enumerated(EnumType.STRING)
    private Pais pais;

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuarioId;
    private Integer usuarioId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name ="endereco_padrao")
    private PerguntaAtivo enderecoPadrao;

}
