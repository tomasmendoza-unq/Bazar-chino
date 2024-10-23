package com.bazarChino.tp_final_spring.Controllers;

import com.bazarChino.tp_final_spring.DTO.ClienteDTO;
import com.bazarChino.tp_final_spring.Models.Cliente;
import com.bazarChino.tp_final_spring.Repository.IClienteRepository;
import com.bazarChino.tp_final_spring.Services.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    IClienteService cliService;

    @GetMapping
    public List<ClienteDTO> getClientes(){
        return cliService.getClientes();
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<?> getCliente(@PathVariable Long id_cliente){
        ClienteDTO clienteDTO = cliService.getClienteDTOById(id_cliente);
        return ResponseEntity.status(HttpStatus.FOUND).body(clienteDTO);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDTO nuevoCliente){
        cliService.saveCliente(nuevoCliente);

        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id_cliente){
        cliService.deleteCliente(id_cliente);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino el cliente con el id: " + id_cliente);
    }

    @PutMapping("/editar/{id_cliente}")
    public ResponseEntity<?> editCliente(@PathVariable Long id_cliente, @Valid @RequestBody ClienteDTO cliente){
        cliService.editCliente(id_cliente,cliente);

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

}
