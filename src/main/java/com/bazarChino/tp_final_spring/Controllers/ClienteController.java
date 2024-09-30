package com.bazarChino.tp_final_spring.Controllers;

import com.bazarChino.tp_final_spring.Models.Cliente;
import com.bazarChino.tp_final_spring.Repository.IClienteRepository;
import com.bazarChino.tp_final_spring.Services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    IClienteService cliService;

    @PostMapping("/clientes/crear")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente nuevoCliente){
        cliService.saveCliente(nuevoCliente);

        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return cliService.getClientes();
    }

    @GetMapping("/clientes/{id_cliente}")
    public Cliente getCliente(@PathVariable Long id_cliente){
        return cliService.getCliente(id_cliente);
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id_cliente){
        cliService.deleteCliente(id_cliente);
        return new ResponseEntity<>("Se elimino el cliente con el id " + id_cliente, HttpStatus.OK);
    }

    @PutMapping("/clientes/editar/{id_cliente}")
    public ResponseEntity<String> editCliente(@PathVariable Long id_cliente, @RequestBody Cliente cliente){
        cliService.editCliente(id_cliente,cliente);

        return new ResponseEntity<>("Se edito el usuario con el id: " + id_cliente, HttpStatus.OK);
    }

}
