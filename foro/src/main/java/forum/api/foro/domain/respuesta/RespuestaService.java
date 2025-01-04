package forum.api.foro.domain.respuesta;

import forum.api.foro.domain.publicacion.IPublicacionRepository;
import forum.api.foro.domain.publicacion.Publicacion;
import forum.api.foro.domain.usuario.IUsuarioRepository;
import forum.api.foro.domain.usuario.Usuario;
import forum.api.foro.infra.errors.ValidacionException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    private IRespuestaRepository repository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IPublicacionRepository publicacionRepository;

    public DtoRespuestaList responder(@Valid DtoRespuestaNueva nuevaResp) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(nuevaResp.idUsuario());
        Optional<Publicacion> optPublicacion = publicacionRepository.findById(nuevaResp.idPublicacion());
        if(optUsuario.isEmpty()){
            throw new ValidacionException("Usuario no existente");
        }
        if(optPublicacion.isEmpty()){
            throw new ValidacionException("Publicacion no existente");
        }
        Usuario usuario = optUsuario.get();
        Publicacion publicacion = optPublicacion.get();
        Respuesta respuesta = repository.save(new Respuesta(nuevaResp, usuario, publicacion));
        return new DtoRespuestaList(respuesta);
    }
}
