package mx.foro.alura.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record IdUsuario(
        @NotBlank @Pattern(regexp = "^\\d+$") String id
) {
}
