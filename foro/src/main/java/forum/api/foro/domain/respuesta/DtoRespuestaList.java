package forum.api.foro.domain.respuesta;

import forum.api.foro.domain.publicacion.Publicacion;
import forum.api.foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtoRespuestaList(
        @NotNull
        Long idResp,
        String respuesta,
        LocalDateTime fecha,
        Long idUsuario,
        String nombreUsuario,
        Long idPublicacion,
        String tituloPublicacion
) {


    public DtoRespuestaList(Respuesta respuesta) {
        this(respuesta.getIdRespuesta()
                , respuesta.getRespuesta()
                , respuesta.getFecha()
                , respuesta.getUsuario().getIdUsuario()
                , respuesta.getUsuario().getNombre()
                , respuesta.getPublicacion().getIdpublicacion()
                , respuesta.getPublicacion().getTitulo());
    }
}
