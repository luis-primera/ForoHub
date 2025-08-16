package Challenge.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopicos(Long id,
                                String titulo,
                                String mensaje,
                                LocalDateTime fecha_creacion) {
    public DatosListaTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion());
    }
}
