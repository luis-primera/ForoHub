package Challenge.ForoHub.domain.topico;

import Challenge.ForoHub.domain.curso.Curso;
import Challenge.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fecha_creacion=LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor_id;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso_id;

}
