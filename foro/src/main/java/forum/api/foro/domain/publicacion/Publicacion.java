package forum.api.foro.domain.publicacion;

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

@Table(name = "publicaciones")
@Entity(name = "publicacion")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idpublicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpublicacion;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    private Usuario usuario;

    public Publicacion(@Valid DtoPublicacionRegistro nuevaPublicacion, Usuario usuario) {
        this.titulo = nuevaPublicacion.titulo();
        this.descripcion = nuevaPublicacion.descripcion();
        this.usuario = usuario;
        this.fecha = ZonedDateTime.now(ZoneId.of("UTC-08:00")).toLocalDateTime();
    }

    public void actualizar(@Valid DtosPublicacionActualizar dtoAct) {
        if(dtoAct.descripcion() != null){
            this.descripcion = dtoAct.descripcion();
            this.fecha = ZonedDateTime.now(ZoneId.of("UTC-08:00")).toLocalDateTime();
        }
        if(dtoAct.titulo() != null){
            this.titulo = dtoAct.titulo();
            this.fecha = ZonedDateTime.now(ZoneId.of("UTC-08:00")).toLocalDateTime();
        }
    }

    public Long getIdpublicacion() {
        return idpublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
