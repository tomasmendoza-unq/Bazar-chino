package com.bazarChino.tp_final_spring.Repository;

import com.bazarChino.tp_final_spring.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Long> {
}
