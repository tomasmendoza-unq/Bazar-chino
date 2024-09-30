package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.Models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {
    public void saveCliente(Cliente nuevoCliente);

    public List<Cliente> getClientes();

    public Cliente getCliente(Long idCliente);

    void deleteCliente(Long idCliente);

    void editCliente(Long idCliente, Cliente cliente);
}
