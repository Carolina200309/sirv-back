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
@Table(name = "lista_compra")
public class Lista_Compra
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "producto_lista_compra",
            joinColumns = @JoinColumn(name = "id_lista"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Productos> productos;

    @Column(name = "id_lista",nullable = false)
    private Integer id_lista;

    @Column(name = "id_producto",nullable = false)
    private Integer id_producto;

    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unidad",nullable = false)
    private Float precio_unidad;

    @Column(name = "subtotal",nullable = false)
    private Float subtotal;

    @Column(name = "fecha_compra",nullable = false)
    private LocalDateTime fecha_compra;

    @Column(name = "estado_compra",nullable = false)
    private String estado_compra;


}

