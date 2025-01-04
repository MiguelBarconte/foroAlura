package forum.api.foro.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DtosUsuarioRegistro(
        @NotNull
        String correo,
        @NotNull
        String nombre,
        @NotNull
        String clave
) {
}
