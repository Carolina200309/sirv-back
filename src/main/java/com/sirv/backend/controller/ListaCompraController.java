package com.sirv.backend.controller;

import com.sirv.backend.dto.model.ListaCompraDTO;
import com.sirv.backend.dto.model.ProductoDTO;
import com.sirv.backend.service.ListaCompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista_compra")
public class ListaCompraController {

    private final ListaCompraService listaCompraService;

    public ListaCompraController(ListaCompraService listaCompraService) {
        this.listaCompraService = listaCompraService;
    }


    @PostMapping("/crear")
    @Secured("DISTRIBUIDOR")
    public ResponseEntity<ListaCompraDTO> crearListaCompra(@RequestBody List<ProductoDTO> productosDTO) {
        ListaCompraDTO listaCompraCreada = listaCompraService.createListaCompra(productosDTO);
        return new ResponseEntity<>(listaCompraCreada, HttpStatus.CREATED);
    }

    @GetMapping("/{idListaCompra}")
    public ResponseEntity<ListaCompraDTO> obtenerListaCompra(@PathVariable int idListaCompra) {
        ListaCompraDTO listaCompraDTO = listaCompraService.getListaCompra(idListaCompra);
        return ResponseEntity.ok(listaCompraDTO);
    }


}