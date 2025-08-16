package Challenge.ForoHub.domain.curso;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    public Curso(DatosCurso datos){
        this.id = null;
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

}
