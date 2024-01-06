package nelsonssoares.ecomuserapi.domain.repository;

import nelsonssoares.ecomuserapi.domain.entities.Endereco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findAllByUsuarioId(Integer id);

    @Query(value = "SELECT e.* FROM endereco e JOIN usuario u ON e.usuario_id = u.id WHERE u.ativo = 'SIM'", nativeQuery = true)
    List<Endereco> findActiveAddresses(Pageable paginacao);
}
