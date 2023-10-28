package org.example.controller;

import org.example.model.Cliente;
import org.example.service.ClienteService;

import java.util.List;

public class ClienteController implements CRUD<Cliente>{

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente findOne(String cuit){
        return clienteService.findOne(cuit);

    }

    public List<Cliente> findAll() {
        return clienteService.findAll();
    }


    public void delete(String cuit) {
        clienteService.delete(cuit);
    }

    public  void create(Cliente cliente){
        clienteService.save(cliente);
    }
    public void upDate(Cliente cliente) {
        clienteService.upDate(cliente);
    }
}
