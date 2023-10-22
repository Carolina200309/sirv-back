package com.sirv.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comentarios")

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private User user;

    @Column(name = "texto",nullable = false)
    private String texto;

    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;

    @Column(name = "calificacion")
    private Integer calificacion;

}
