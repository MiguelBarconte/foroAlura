package forum.api.foro.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DtosUsuarioRepuesta(
        @NotNull
        Long idUsuario,
        String nombre,
        String correo

) {
    public DtosUsuarioRepuesta(Usuario usuario) {
        this(usuario.getIdUsuario()
                , usuario.getNombre()
                , usuario.getCorreo());
    }
}
