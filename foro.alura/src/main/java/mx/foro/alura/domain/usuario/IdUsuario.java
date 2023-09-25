package mx.foro.alura.domain.usuario;

import jakarta.validation.constraints.NotNull;


public record IdUsuario(
        @NotNull Long id
) {
}
