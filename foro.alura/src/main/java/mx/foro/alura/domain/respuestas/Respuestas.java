package mx.foro.alura.domain.respuestas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mx.foro.alura.domain.topico.Topico;
import mx.foro.alura.domain.usuario.Usuario;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuestas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Respuestas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    private Boolean solucion;

    @PrePersist
    public void prePersist(){
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }
}
