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
    @Column(name = "id_producto",nullable = false)
    private Integer id_producto;

    @ManyToMany(mappedBy = "productos")
    private List<Lista_Compra> lista_compra;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarios;

    @Column(name = "nombre_producto",nullable = false)
    private String nombre_producto;

    @Column(name = "precio",nullable = false)
    private Float precio;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Color color;

    public enum Color {
        NEGRO, BLANCO, NARANJA, GRIS;
    }

}
