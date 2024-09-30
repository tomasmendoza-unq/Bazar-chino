package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.Models.Cliente;
import com.bazarChino.tp_final_spring.Repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    IClienteRepository cliRepository;

    @Override
    public void saveCliente(Cliente nuevoCliente) {
        cliRepository.save(nuevoCliente);
    }

    @Override
    public List<Cliente> getClientes() {
        return cliRepository.findAll();
    }

    @Override
    public Cliente getCliente(Long idCliente) {
        return cliRepository.findById(idCliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long idCliente) {
        cliRepository.deleteById(idCliente);
    }

    @Override
    public void editCliente(Long idCliente, Cliente cliente) {
        Cliente cli = this.getCliente(idCliente);

        this.saveCliente(cli);
    }
}
