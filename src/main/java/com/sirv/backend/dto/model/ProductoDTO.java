package com.sirv.backend.dto.model;

import com.sirv.backend.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private float precio;
    private String descripcion;

    private Producto.Color color;

    public String getColor() {
        return color.name().toLowerCase();
    }
}
