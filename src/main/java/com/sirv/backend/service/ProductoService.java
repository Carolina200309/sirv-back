package com.sirv.backend.service;

import com.sirv.backend.dto.model.ProductoDTO;
import com.sirv.backend.model.Producto;
import com.sirv.backend.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    private ProductoDTO mapToDTO(Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion(), producto.getColor());
    }

    public List<ProductoDTO> getProductos() {
        return productoRepository.findAll().parallelStream().map(this::mapToDTO).toList();
    }

    public ProductoDTO getProducto(int id) {
        return mapToDTO(productoRepository.findById(id).orElseThrow());
    }

    public void createProducto(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        producto.setColor(Producto.Color.valueOf(dto.getColor().toUpperCase()));

        productoRepository.save(producto);
    }

    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

}
