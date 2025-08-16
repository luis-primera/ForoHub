package Challenge.ForoHub.controller;

import Challenge.ForoHub.domain.topico.DatosListaTopicos;
import Challenge.ForoHub.domain.topico.DatosRegistroTopico;
import Challenge.ForoHub.domain.topico.Topico;
import Challenge.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public void registrar(@Valid @RequestBody DatosRegistroTopico datos) {
        //System.out.println(new Topico(datos));
        //repository.save(new Topico(datos));
    }

    @GetMapping
    public List<DatosListaTopicos> listar(){
        return repository.findAll().stream().map(DatosListaTopicos::new).toList();
    }
}
