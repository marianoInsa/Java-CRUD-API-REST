package com.insa.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insa.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
