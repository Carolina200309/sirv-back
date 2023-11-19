package com.sirv.backend.service;

import com.sirv.backend.dto.model.ListaCompraDTO;
import com.sirv.backend.dto.model.ProductoDTO;
import com.sirv.backend.model.Lista_Compra;
import com.sirv.backend.model.Producto;
import com.sirv.backend.model.User;
import com.sirv.backend.repository.ListaCompraRepository;
import com.sirv.backend.repository.ProductoRepository;
import com.sirv.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service

public class ListaCompraService {

    private final ListaCompraRepository listaCompraRepository;
    private final ProductoService productoService; // Agrega una instancia de ProductoService

    private ListaCompraDTO mapToDTO(Lista_Compra lista_compra) {
        List<ProductoDTO> productoDTOList = lista_compra.getProductos()
                .stream()
                .map(productoService::mapToDTO)
                .collect(Collectors.toList());

        return new ListaCompraDTO(lista_compra.getId_lista(), productoDTOList);
    }

    public ListaCompraDTO getListaCompra(int id_lista) {
        return mapToDTO(listaCompraRepository.findById(id_lista).orElseThrow());
    }

    public ListaCompraDTO createListaCompra(List<ProductoDTO> productos) {

        Lista_Compra nuevaListaCompra = new Lista_Compra();
        listaCompraRepository.save(nuevaListaCompra);

        return mapToDTO(nuevaListaCompra);

    }
public ListaCompraDTO agregarProductoAListaCompra(int id_lista, ProductoDTO productoDTO, int id) {


    Lista_Compra listaCompra = listaCompraRepository.findById(id_lista)
            .orElseThrow(() -> new ListaCompraNotFoundException("No se encontró la lista de compra con ID: " + id_lista));

    Producto nuevoProducto = productoService.createProducto(productoDTO);


    listaCompra.getProductos().add(nuevoProducto);


    Lista_Compra listaCompraActualizada = listaCompraRepository.save(listaCompra);

    return mapToDTO(listaCompraActualizada);
}

    public ListaCompraDTO limpiarListaCompra(int id_lista) {
        Lista_Compra listaCompra = listaCompraRepository.findById(id_lista)
                .orElseThrow(() -> new ListaCompraNotFoundException("No se encontró la lista de compra con ID: " + id_lista));

        listaCompra.getProductos().clear();

        Lista_Compra listaCompraActualizada = listaCompraRepository.save(listaCompra);

        return mapToDTO(listaCompraActualizada);
    }


    public class ListaCompraNotFoundException extends RuntimeException {
        public ListaCompraNotFoundException(String message) {
            super(message);
        }
    }
}

