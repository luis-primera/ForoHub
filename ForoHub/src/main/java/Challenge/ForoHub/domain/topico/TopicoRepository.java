package Challenge.ForoHub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTitulo(String titulo);
    boolean existsByMensaje(String mensaje);
    boolean existsByTituloAndIdNot(String titulo, Long id);
    boolean existsByMensajeAndIdNot(String mensaje, Long id);

}
