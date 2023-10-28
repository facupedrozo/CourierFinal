package org.example.service;

import org.example.model.Cliente;
import org.example.model.Deposito;
import org.example.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

public class  ClienteService implements CRUD<Cliente>{
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    public void save(Cliente cliente){
        if (clienteRepository.findOne(cliente.getCuit())!=null) {
        }else {clienteRepository.save(cliente);

        }
    }


    public List<Cliente>findAll(){
        return clienteRepository.findAll();
    }


    public Cliente findOne(String cuit){
        Cliente cliente = null;
        if (clienteRepository.findOne(cuit)!=null) {
            cliente = clienteRepository.findOne(cuit);

        }
        return cliente;

    }


    public void upDate(Cliente cliente){
        if(findOne(cliente.getCuit()) != null){
            clienteRepository.upDate(cliente);
        }
    }
    public void delete(String cuit){
        if (findOne(cuit)!= null){
            clienteRepository.delete(cuit);
        }
    }
}

