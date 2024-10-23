package com.bazarChino.tp_final_spring.Repository;

import com.bazarChino.tp_final_spring.Models.VentaProducto;
import com.bazarChino.tp_final_spring.embeddables.VentaProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVentaProductoRepository extends JpaRepository<VentaProducto, VentaProductoId> {

    @Query("SELECT vp FROM VentaProducto vp WHERE vp.venta.codigo_venta = :codigoVenta")
    List<VentaProducto> findByVentaCodigo(@Param("codigoVenta") Long codigoVenta);

}
