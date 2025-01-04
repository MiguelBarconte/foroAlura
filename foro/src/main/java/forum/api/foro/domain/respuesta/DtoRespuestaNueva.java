package forum.api.foro.domain.respuesta;

public record DtoRespuestaNueva(
        String repuesta,
        Long idUsuario,
        Long idPublicacion

) {
}
