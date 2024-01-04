package nelsonssoares.ecomuserapi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nelsonssoares.ecomuserapi.domain.entities.enums.PerguntaAtivo;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "usuario",uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf","email"})})
    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @Column(length = 255)
        @NotEmpty(message = "Este campo não pode ser nulo ou vazio, deve conter de 2 até 255 caracteres.")
        private String nome;

        @Column(length = 255)
        @NotEmpty(message = "Este campo não pode ser nulou ou vazio, deve conter de 2 até 255 caracteres.")
        private String sobrenome;

        @Column(length = 14)
        @NotEmpty(message = "Informe CPF Válido! EX: 123.456.789-10")
        @CPF(message = "Informe CPF Válido! EX: 123.456.789-10")
        private String cpf;

        // @Pattern(regexp =
        // "(/^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$/)"
        // ,message = "{campo.telefone.válido}")
        @Column(length = 20)
        private String telefone;

        @Column(name = "data_criacao")
        @NotNull
        private LocalDate dataCriacao;

        @Column(name = "data_modificacao")
        @NotNull
        @DateTimeFormat(pattern = "YYYY-MM-DD")
        private LocalDate dataModificacao;

        @Email
        @NotEmpty(message = "Informe um e-mail válido! Ex: 123deoliveira4@gmail.com")
        @Column(length = 255)
        private String email;

        @NotNull
        @Enumerated(EnumType.ORDINAL)
        private PerguntaAtivo ativo;
}
