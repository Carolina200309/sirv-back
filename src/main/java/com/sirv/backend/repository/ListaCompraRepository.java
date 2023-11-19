package com.sirv.backend.repository;
import com.sirv.backend.model.Lista_Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaCompraRepository extends JpaRepository<Lista_Compra, Integer>  {
}
