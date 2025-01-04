package forum.api.foro.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {

    Page<Usuario> findByEstadoTrue(Pageable paginacion);

    UserDetails findByNombre(String subject);
}
