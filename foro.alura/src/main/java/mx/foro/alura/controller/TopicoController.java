package mx.foro.alura.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mx.foro.alura.domain.curso.IdCurso;
import mx.foro.alura.domain.topico.*;
import mx.foro.alura.domain.usuario.IdUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    public TopicoController (TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }


    @Transactional
    @PostMapping
    @Operation(summary = "Registra un topico")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(), topico.getFechaCreacion(),
                new IdUsuario(topico.getUsuario().getId()),
                new IdCurso(topico.getCurso().getId()));

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de topicos")
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
        //return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new));
    }

    @Operation(summary = "Actualiza los datos de un topico existente")
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopicos datosActualizarTopicos){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopicos.id());
        topico.actualizarTopicos(datosActualizarTopicos);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(), topico.getFechaCreacion(),
                new IdUsuario(topico.getUsuario().getId()),
                new IdCurso(topico.getCurso().getId())));
    }


    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un topico registrado - inactivo")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los registros del topico con ID")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosRopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(), topico.getFechaCreacion(),
                new IdUsuario(topico.getUsuario().getId()),
                new IdCurso(topico.getCurso().getId()));
        return ResponseEntity.ok(datosRespuestaTopico);
    }

}
