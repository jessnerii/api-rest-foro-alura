package mx.foro.alura.domain.topico;

import jakarta.validation.constraints.NotNull;
import mx.foro.alura.domain.status.StatusTopico;

public record DatosActualizarTopicos(@NotNull Long id, String titulo, String mensaje, StatusTopico status) {
}
