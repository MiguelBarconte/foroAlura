package forum.api.foro.controller;

import forum.api.foro.domain.respuesta.*;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaService service;

    @Autowired
    private IRespuestaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoRespuestaList> responder(@RequestBody @Valid DtoRespuestaNueva nuevaResp, UriComponentsBuilder uriCompBuild){
        DtoRespuestaList detalles = service.responder(nuevaResp);
        URI url = uriCompBuild.path("/respuestas/{id}")
                .buildAndExpand(detalles.idResp()).toUri();
        return ResponseEntity.created(url).body(detalles);
    }

    @GetMapping("/{publicacion_id}")
    public ResponseEntity<List<DtoRespuestaList>> ver(@PathVariable Long publicacion_id) {
        List<Respuesta> r = repository.respuestaDePublicacion(publicacion_id);
        List<DtoRespuestaList> datos = r.stream()
                .map(DtoRespuestaList::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id){
        repository.deleteById(id);
    }


}
