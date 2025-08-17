package Challenge.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListarTopicos(Long id,
                                 String titulo,
                                 String mensaje,
                                 LocalDateTime fecha_creacion) {
    public DatosListarTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion());
    }
}
