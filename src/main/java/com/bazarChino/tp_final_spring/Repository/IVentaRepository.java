package com.bazarChino.tp_final_spring.Repository;

import com.bazarChino.tp_final_spring.DTO.VentaClienteDTO;
import com.bazarChino.tp_final_spring.DTO.VentaFechaDTO;
import com.bazarChino.tp_final_spring.Models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta,Long> {

    @Query(value = "SELECT new com.bazarChino.tp_final_spring.DTO.VentaFechaDTO(SUM(v.total), COUNT(v)) FROM Venta v WHERE v.fecha_venta = :fecha_venta GROUP BY v.fecha_venta")
    List<VentaFechaDTO> findByFecha(@Param("fecha_venta")LocalDate fecha_venta);

    @Query(value = "SELECT new com.bazarChino.tp_final_spring.DTO.VentaClienteDTO(v.codigo_venta, SUM(v.total), COUNT(vp), c.apellido, c.nombre) " +
            "FROM Venta v " +
            "JOIN v.unCliente c " +
            "JOIN v.productos vp " +
            "GROUP BY v.codigo_venta, c.nombre, c.apellido " +
            "ORDER BY SUM(v.total) DESC")
    public List<VentaClienteDTO> findMaxVenta();
}
