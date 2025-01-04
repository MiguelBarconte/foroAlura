package forum.api.foro.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String correo;
    private String nombre;
    private String clave;
    private Boolean estado;

    public Usuario(@Valid DtosUsuarioRegistro datosRegistro) {
        this.nombre = datosRegistro.nombre();
        this.correo = datosRegistro.correo();
        this.clave = datosRegistro.clave();
        this.estado = true;
    }

    public void bloquear(){
        this.estado = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return estado;
    }

    @Override
    public boolean isAccountNonLocked() {
        return estado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return estado;
    }

    @Override
    public boolean isEnabled() {
        return estado;
    }

    public void actualizarDtos(@Valid DtosUsuarioRepuesta datosActualizar) {
        this.nombre = datosActualizar.nombre();
        this.correo = datosActualizar.correo();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public Boolean getEstado() {
        return estado;
    }
}
