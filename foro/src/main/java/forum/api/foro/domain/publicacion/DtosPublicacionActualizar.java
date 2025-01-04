package forum.api.foro.domain.publicacion;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtosPublicacionActualizar(
        @NotNull
        Long id,
        String titulo,
        String descripcion,
        LocalDateTime fecha
) {
}
