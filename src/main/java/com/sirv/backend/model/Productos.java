package com.sirv.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Productos
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto",nullable = false)
    private Integer id_producto;

    @ManyToMany(mappedBy = "productos")
    private List<Lista_Compra> lista_compra;

    @OneToMany(mappedBy = "productos")
    private List<Comentarios> comentarios;

    @Column(name = "nombre_producto",nullable = false)
    private String nombre_producto;

    @Column(name = "precio",nullable = false)
    private Float precio;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

}
