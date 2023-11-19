package com.sirv.backend.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaCompraDTO {

        private Integer id_lista;
        private List<ProductoDTO> productos;
}
