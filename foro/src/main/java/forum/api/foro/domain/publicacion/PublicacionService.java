package forum.api.foro.domain.publicacion;

import forum.api.foro.domain.respuesta.IRespuestaRepository;
import forum.api.foro.domain.usuario.IUsuarioRepository;
import forum.api.foro.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicacionService {

    @Autowired
    private IPublicacionRepository repository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRespuestaRepository respuestaService;

    public DtoPublicacionList publicar(@Valid DtoPublicacionRegistro nuevaPublicacion) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(nuevaPublicacion.idUsuario());
        if (optUsuario.isEmpty()) {
            throw new ValidationException("Usuario no encontrado");
        }
        Usuario usuario = optUsuario.get();
        var registroPublicacion = new Publicacion(nuevaPublicacion, usuario);
        repository.save(registroPublicacion);
        return new DtoPublicacionList(registroPublicacion);
    }
}
