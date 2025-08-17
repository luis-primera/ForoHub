package Challenge.ForoHub.controller;

import Challenge.ForoHub.domain.curso.Curso;
import Challenge.ForoHub.domain.curso.CursoRepository;
import Challenge.ForoHub.domain.topico.*;
import Challenge.ForoHub.domain.usuario.Usuario;
import Challenge.ForoHub.domain.usuario.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody DatosRegistroTopico datos) {

        // Verificar duplicados
        if (repository.existsByTitulo(datos.titulo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ya existe un tópico con este título\"}");
        }

        if (repository.existsByMensaje(datos.mensaje())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ya existe un tópico con este mensaje\"}");
        }

        Usuario autor = usuarioRepository.findById(datos.autor_id())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(datos.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));


        Topico topico = new Topico(datos, autor, curso);
        repository.save(topico);

        DatosListaTopicos detalle = new DatosListaTopicos(topico);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(detalle);
    }

    @GetMapping
    public List<DatosListaTopicos> listar(){
        return repository.findAll().stream().map(DatosListaTopicos::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopicos> obtenerTopico(@PathVariable Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Tópico no encontrado"));

        DatosListaTopicos datos = new DatosListaTopicos(topico);
        return ResponseEntity.ok(datos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizar(@RequestBody @Valid DatosActualizarTopico datos) throws Exception {
        Topico topico = repository.findById(datos.id())
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Tópico no encontrado"));

        // Validar duplicados ignorando el mismo id
        if (datos.titulo() != null && repository.existsByTituloAndIdNot(datos.titulo(), topico.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ya existe un tópico con este título\"}");
        }

        if (datos.mensaje() != null && repository.existsByMensajeAndIdNot(datos.mensaje(), topico.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ya existe un tópico con este mensaje\"}");
        }

        // Si pasó las validaciones, actualizar
        if (datos.titulo() != null) topico.setTitulo(datos.titulo());
        if (datos.mensaje() != null) topico.setMensaje(datos.mensaje());

        DatosListaTopicos detalle = new DatosListaTopicos(topico);

        return ResponseEntity.ok(detalle);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Tópico no encontrado\"}");
        }

        repository.deleteById(id);
        return ResponseEntity.ok("{\"mensaje\": \"Tópico eliminado correctamente\"}");
    }


}
