package com.sirv.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToMany(mappedBy = "productos")
    private List<Lista_Compra> lista_compra;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarios;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "precio",nullable = false)
    private Float precio;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String cuidados;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private String Material;


    @Enumerated(EnumType.STRING)
    private Color color;

    public enum Color {
        NEGRO, BLANCO, NARANJA, GRIS;
    }

}
