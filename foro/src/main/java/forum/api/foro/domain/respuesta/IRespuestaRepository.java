package forum.api.foro.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRespuestaRepository extends JpaRepository<Respuesta,Long> {

    @Query(value = """
            SELECT p.* FROM publicaciones p
            WHERE p.idPublicacion IN(
                SELECT r.publicacion_id FROM Respuestas r
                WHERE r.publicacion_id = :id_publicacion
            )
            """, nativeQuery = true)
    List<Respuesta> respuestaDePublicacion(Long id_publicacion);


}
