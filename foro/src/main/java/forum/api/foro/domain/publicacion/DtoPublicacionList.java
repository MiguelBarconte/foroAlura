package forum.api.foro.domain.publicacion;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtoPublicacionList(
        @NotNull
        Long idPublicacion,
        String titulo,
        String descripcion,
        Long idUsuario,
        LocalDateTime fecha
) {
    public DtoPublicacionList(Publicacion registroPublicacion) {
        this(registroPublicacion.getIdpublicacion()
                , registroPublicacion.getTitulo()
                , registroPublicacion.getDescripcion()
                , registroPublicacion.getUsuario().getIdUsuario()
                , registroPublicacion.getFecha());
    }

}
