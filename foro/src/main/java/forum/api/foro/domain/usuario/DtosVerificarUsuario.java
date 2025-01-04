package forum.api.foro.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DtosVerificarUsuario(
        @NotNull
        String nombre,
        @NotNull
        String clave
) {
}
