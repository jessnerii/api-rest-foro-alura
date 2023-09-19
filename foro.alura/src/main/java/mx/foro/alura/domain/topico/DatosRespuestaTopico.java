package mx.foro.alura.domain.topico;

import mx.foro.alura.domain.curso.IdCurso;
import mx.foro.alura.domain.status.StatusTopico;
import mx.foro.alura.domain.usuario.IdUsuario;

public record DatosRespuestaTopico (Long id, String titulo, String mensaje, StatusTopico status,
                                    java.time.LocalDateTime fechaCreacion, IdUsuario autor, IdCurso curso) {
}
