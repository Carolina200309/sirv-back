package com.sirv.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private User user;
    @Column(name = "id_factura",nullable = false)
    private Integer id_factura;

    @Column(name = "total_cancelado",nullable = false)
    private Float total_cancelado;

    @Column(name = "fecha_factura",nullable = false)
    private LocalDateTime fecha_factura;

    @Column(name = "estado_compra",nullable = false)
    private String estado_compra;

    @Column(name = "metodo_pago",nullable = false)
    private String metodo_pago;

}
