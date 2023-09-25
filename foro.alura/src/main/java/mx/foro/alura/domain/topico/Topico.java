package mx.foro.alura.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import mx.foro.alura.domain.curso.Curso;
import mx.foro.alura.domain.respuestas.Respuestas;
import mx.foro.alura.domain.status.StatusTopico;
import mx.foro.alura.domain.usuario.Usuario;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private boolean activo;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "topico_id")
    private List<Respuestas> respuestas;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.activo = true;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.status = datosRegistroTopico.status();
        this.usuario = new Usuario(datosRegistroTopico.usuario());
        this.curso = new Curso(datosRegistroTopico.curso());
    }

    public Topico() {
        this.respuestas = new ArrayList<>();
        this.status = StatusTopico.NO_RESPONDIDO;
    }

    public void actualizarTopicos(@Valid DatosActualizarTopicos datosActualizarTopicos) {
        if (datosActualizarTopicos.titulo() != null) {
            this.titulo = datosActualizarTopicos.titulo();
        }
        if (datosActualizarTopicos.mensaje() != null) {
            this.mensaje = datosActualizarTopicos.mensaje();
        }
        if (datosActualizarTopicos.status() != null) {
            this.status = datosActualizarTopicos.status();
        }
    }

    public void desactivarTopico() {
        this.activo = false;
    }


}
