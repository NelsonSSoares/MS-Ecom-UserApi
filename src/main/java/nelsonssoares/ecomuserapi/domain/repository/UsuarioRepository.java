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

    @Query(value = "select * from usuario u where u.nome like %:nome%", nativeQuery = true)
    List<Usuario> findByNome(@Param("nome") String nome);

    @Query(value = "select * from usuario u where u.email = :email", nativeQuery = true)
    Optional<Usuario> findByEmail(@Param("email") String email);
}
