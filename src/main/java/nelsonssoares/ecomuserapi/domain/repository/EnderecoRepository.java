package nelsonssoares.ecomuserapi.domain.repository;

import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByUsuarioId(Integer usuarioId);
}
