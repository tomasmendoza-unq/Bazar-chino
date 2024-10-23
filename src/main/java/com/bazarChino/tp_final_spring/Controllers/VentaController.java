package com.bazarChino.tp_final_spring.Controllers;

import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.DTO.VentaClienteDTO;
import com.bazarChino.tp_final_spring.DTO.VentaDTO;
import com.bazarChino.tp_final_spring.DTO.VentaFechaDTO;
import com.bazarChino.tp_final_spring.Services.IVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getVentas(){
        return ResponseEntity.status(HttpStatus.OK).body(ventaService.getVentasDTO());
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<?> getVenta(@PathVariable Long codigo_venta){
        VentaDTO ventaDTO = ventaService.getVentaDTO(codigo_venta);

        return ResponseEntity.status(HttpStatus.OK).body(ventaDTO);
    }

    @GetMapping("/productos/{codigo_venta}")
    public ResponseEntity<?> getProductosByVenta(@PathVariable Long codigo_venta){
        List<ProductoDTO> productoDTOList = ventaService.getProductoDTOList(codigo_venta);

        return ResponseEntity.status(HttpStatus.FOUND).body(productoDTOList);
    }

    @GetMapping("/fecha/{fecha_venta}")
    public ResponseEntity<?> getByDate(@PathVariable LocalDate fecha_venta){

        List<VentaFechaDTO> ventaFechaDTOList = ventaService.getByDate(fecha_venta);

        return ResponseEntity.status(HttpStatus.OK).body(ventaFechaDTOList);
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<?> getMaxVenta(){
        VentaClienteDTO ventaClienteDTO = ventaService.getMaxVenta();

        return ResponseEntity.status(HttpStatus.OK).body(ventaClienteDTO);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearVenta(@Valid @RequestBody VentaDTO ventaDTO){
        ventaService.saveVenta(ventaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ventaDTO);
    }

    @PutMapping("/editar/{codigo_venta}")
    public ResponseEntity<?> editarVenta(@Valid @RequestBody VentaDTO ventaDTO, @PathVariable Long codigo_venta){
        ventaService.editVenta(ventaDTO, codigo_venta);

        return ResponseEntity.status(HttpStatus.OK).body(ventaDTO);
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long codigo_venta){
        ventaService.deleteVenta(codigo_venta);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Venta eliminada correctamente");
    }
}
