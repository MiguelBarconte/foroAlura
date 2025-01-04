package forum.api.foro.domain.respuesta;

import forum.api.foro.domain.publicacion.Publicacion;
import forum.api.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Table(name = "respuestas")
@Entity(name = "respuesta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idRespuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;
    private String respuesta;
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "publicacion_id")
    private Publicacion publicacion;


    public Respuesta(@Valid DtoRespuestaNueva nuevaResp, Usuario usuario, Publicacion publicacion) {
        this.respuesta = nuevaResp.repuesta();
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.fecha = ZonedDateTime.now(ZoneId.of("UTC-08:00")).toLocalDateTime();
    }

    public Long getIdRespuesta() {
        return idRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }
}
