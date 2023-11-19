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
    @Column(name = "id_lista",nullable = false)
    private Integer id_lista;


    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    //create INDEX idx_id_lista ON lista_compra (id_lista);
    @ManyToMany
    @JoinTable(name = "producto_lista_compra",
            joinColumns = @JoinColumn(name = "id_lista"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Producto> productos;

}

