package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ClienteDTO;
import com.bazarChino.tp_final_spring.DTO.ProductoDTO;
import com.bazarChino.tp_final_spring.Exception.ResourceNotFound;
import com.bazarChino.tp_final_spring.Models.Cliente;
import com.bazarChino.tp_final_spring.Models.Producto;
import com.bazarChino.tp_final_spring.Repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    IClienteRepository cliRepository;

    @Override
    public ClienteDTO saveCliente(ClienteDTO nuevoCliente) {
        Cliente cliente = new Cliente(nuevoCliente.getNombre(),nuevoCliente.getApellido(),nuevoCliente.getDni());
        Cliente savedCliente = cliRepository.save(cliente);

        return this.crearDTO(savedCliente);
    }

    public ClienteDTO crearDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getDni(),
                cliente.getApellido(),
                cliente.getNombre()
        );
    }

    @Override
    public List<ClienteDTO> getClientes() {
        List<ClienteDTO> listaClientesDTO = new ArrayList<>();

        cliRepository.findAll().forEach(cliente -> {
            listaClientesDTO.add(this.crearDTO(cliente));
        });

        return listaClientesDTO;
    }



    @Override
    public ClienteDTO getClienteDTOById(Long idCliente) {
        Cliente cliente = this.getClienteById(idCliente);

        return this.crearDTO(cliente);

    }

    private Cliente getClienteById(Long idCliente) {
        return cliRepository.findById(idCliente).orElseThrow(() ->
                new ResourceNotFound(idCliente, "No se encontró al cliente con ID: " + idCliente)
        );
    }

    @Override
    public void deleteCliente(Long idCliente) {
        Cliente cliente = this.getClienteById(idCliente);
        cliRepository.delete(cliente);
    }

    private void updateClienteFromDTO(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDni(clienteDTO.getDni());
    }

    @Override
    public void editCliente(Long idCliente, ClienteDTO clienteDTO) {
        Cliente cli = this.getClienteById(idCliente);
        this.updateClienteFromDTO(cli, clienteDTO);


        cliRepository.save(cli);
    }
}
