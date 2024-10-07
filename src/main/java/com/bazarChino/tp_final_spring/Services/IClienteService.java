package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.DTO.ClienteDTO;
import com.bazarChino.tp_final_spring.Models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {
    public ClienteDTO saveCliente(ClienteDTO nuevoCliente);

    public List<ClienteDTO> getClientes();

    public ClienteDTO getClienteDTOById(Long idCliente);

    public void deleteCliente(Long idCliente);

    public void editCliente(Long idCliente, ClienteDTO cliente);
}
