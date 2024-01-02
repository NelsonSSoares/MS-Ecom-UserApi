package nelsonssoares.ecomuserapi.domain.repository;

import nelsonssoares.ecomuserapi.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "select * from usuario u where u.cpf = :cpf", nativeQuery = true)
    Optional<Usuario> findByCpf(@Param("cpf") String cpf);

    //Optional<Usuario> findByCpf(String cpf);
    List<Usuario> findByNome(String nome);

    Optional<Usuario> findByEmail(String email);
}
