package com.sirv.backend.service;

import com.sirv.backend.dto.model.ProductoDTO;
import com.sirv.backend.model.Producto;
import com.sirv.backend.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductoService {

    public final ProductoRepository productoRepository;

    public ProductoDTO mapToDTO(Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion(), producto.getCuidados(), producto.getTalla(),producto.getMaterial(),producto.getColor());
    }

    public List<ProductoDTO> getProductos() {
        return productoRepository.findAll().parallelStream().map(this::mapToDTO).toList();
    }

    public ProductoDTO getProducto(int id) {
        return mapToDTO(productoRepository.findById(id).orElseThrow());
    }

    public Producto createProducto(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCuidados(dto.getCuidados());
        producto.setTalla(dto.getTalla());
        producto.setMaterial(dto.getMaterial());
        producto.setColor(Producto.Color.valueOf(dto.getColor().toUpperCase()));

        productoRepository.save(producto);
        return producto;
    }
    public void updateProducto(Integer id, ProductoDTO dto) {

        Optional<Producto> optionalProducto = productoRepository.findById(id);

        if (optionalProducto.isPresent())
        {
            Producto producto = optionalProducto.get();

            producto.setNombre(dto.getNombre());
            producto.setPrecio(dto.getPrecio());
            producto.setDescripcion(dto.getDescripcion());
            producto.setCuidados(dto.getCuidados());
            producto.setTalla(dto.getTalla());
            producto.setMaterial(dto.getMaterial());
            producto.setColor(Producto.Color.valueOf(dto.getColor().toUpperCase()));

            productoRepository.save(producto);
        } else
        {

            throw new ProductoNotFoundException("No se encontró el producto con ID: " + id);
        }
    }
    public class ProductoNotFoundException extends RuntimeException {
        public ProductoNotFoundException(String message) {
            super(message);
        }
    }
    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

}
