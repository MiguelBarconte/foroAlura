package forum.api.foro.domain.publicacion;

public record DtoPublicacionRegistro(
        String titulo,
        String descripcion,
        Long idUsuario
) {
}
