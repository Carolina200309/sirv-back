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

public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos productos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;


    @Column(name = "id_comentario",nullable = false)
    private Integer id_comentario;

    @Column(name = "id_producto",nullable = false)
    private Integer id_producto;

    @Column(name = "id_usuario",nullable = false)
    private Integer id;
    @Column(name = "texto",nullable = false)
    private String texto;

    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;

    @Column(name = "calificacion",nullable = false)
    private String calificacion;

}
