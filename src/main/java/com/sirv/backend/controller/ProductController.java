package com.sirv.backend.controller;

import com.sirv.backend.dto.model.ProductoDTO;
import com.sirv.backend.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductoService productoService;

    @PostMapping
    @Secured("ADMINISTRADOR")
    public ResponseEntity<?> createProduct(@RequestBody ProductoDTO productoDTO) {
        try {
            productoService.createProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public List<ProductoDTO> getAll() {
        return productoService.getProductos();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductoDTO> getProducto(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productoService.getProducto(id));

        } catch (NoSuchElementException ignored) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
