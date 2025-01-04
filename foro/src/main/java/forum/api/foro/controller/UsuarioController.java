package forum.api.foro.controller;

import forum.api.foro.domain.usuario.DtosUsuarioRegistro;
import forum.api.foro.domain.usuario.DtosUsuarioRepuesta;
import forum.api.foro.domain.usuario.IUsuarioRepository;
import forum.api.foro.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DtosUsuarioRepuesta> registrar(@RequestBody @Valid DtosUsuarioRegistro datosRegistro, UriComponentsBuilder uriCompBuild){
        Usuario usuario = repository.save(new Usuario(datosRegistro));
        URI url = uriCompBuild.path("/usuarios/{id}")
                .buildAndExpand(usuario.getIdUsuario()).toUri();
        return ResponseEntity.created(url).body(new DtosUsuarioRepuesta(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DtosUsuarioRepuesta>> listaUsuarios(@PageableDefault(sort = "nombre") Pageable paginacion){
        return ResponseEntity.ok(repository.findByEstadoTrue(paginacion)
                .map(DtosUsuarioRepuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtosUsuarioRepuesta> datosUsuario(@PathVariable Long id) {
        Usuario u = repository.getReferenceById(id);
        DtosUsuarioRepuesta datos = new DtosUsuarioRepuesta(u);
        return ResponseEntity.ok(datos);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DtosUsuarioRepuesta> actualizarUsuario(@Valid @RequestBody DtosUsuarioRepuesta datosActualizar){
        Usuario usuario = repository.getReferenceById(datosActualizar.idUsuario());
        usuario.actualizarDtos(datosActualizar);
        return ResponseEntity.ok(new DtosUsuarioRepuesta(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaciente(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.bloquear();
    }

}
