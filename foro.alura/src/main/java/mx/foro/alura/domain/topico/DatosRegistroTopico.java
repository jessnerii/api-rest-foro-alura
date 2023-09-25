package mx.foro.alura.domain.topico;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.foro.alura.domain.curso.IdCurso;
import mx.foro.alura.domain.status.StatusTopico;
import mx.foro.alura.domain.usuario.IdUsuario;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,

        @NotNull
        StatusTopico status,
        @NotNull
        @Valid
        IdUsuario usuario,
        @NotNull
        @Valid
        IdCurso curso
)
{

}
