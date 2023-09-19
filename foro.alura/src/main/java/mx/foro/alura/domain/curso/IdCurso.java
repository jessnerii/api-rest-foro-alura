package mx.foro.alura.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record IdCurso(@NotBlank @Pattern(regexp = "^\\d+$") String id){
}
