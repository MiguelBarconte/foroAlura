package forum.api.foro.controller;

import forum.api.foro.domain.publicacion.*;
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
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService service;

    @Autowired
    private IPublicacionRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoPublicacionList> crear(@RequestBody @Valid DtoPublicacionRegistro nuevaPublicacion, UriComponentsBuilder uriCompBuild){
        DtoPublicacionList detalles = service.publicar(nuevaPublicacion);
        URI url = uriCompBuild.path("/publicaciones/{id}")
                .buildAndExpand(detalles.idPublicacion()).toUri();
        return ResponseEntity.created(url).body(detalles);
    }

    @GetMapping
    public ResponseEntity<Page<DtoPublicacionList>> publicaciones(@PageableDefault(sort = "titulo") Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion)
                .map(DtoPublicacionList::new));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoPublicacionList> ver(@PathVariable Long id) {
        Publicacion p = repository.getReferenceById(id);
        DtoPublicacionList datos = new DtoPublicacionList(p);
        return ResponseEntity.ok(datos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DtoPublicacionList> editar(@Valid @RequestBody DtosPublicacionActualizar dtoAct){
        Publicacion publicacion = repository.getReferenceById(dtoAct.id());
        publicacion.actualizar(dtoAct);
        return  ResponseEntity.ok(new DtoPublicacionList(publicacion));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id){
        repository.deleteById(id);
    }


}
