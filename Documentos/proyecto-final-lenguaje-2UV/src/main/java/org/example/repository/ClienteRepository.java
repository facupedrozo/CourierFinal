package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Cliente;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ClienteRepository implements CRUD<Cliente>{

    private List<Cliente> clientes;
    private boolean clienteExistente;
    public ClienteRepository() {
        this.clientes = new ArrayList<>();
        upLoad();
    }

    public void upLoad(){
        Cliente c1 = new Cliente("123","Eric", "Puch", "Josesito 232","3243242");
        Cliente c2 = new Cliente("32323","Juan", "Mendez", "San jose 34354","23435");
        Cliente c3 = new Cliente("234324","Elric", "Jalil", "Calle 150","34543543");
        Cliente c4 = new Cliente("3453","Carlos", "Perez", "Calle 32","653634");
        Cliente c5 = new Cliente("54634","Gustavo", "Lopez", "calle 09","6435345");
        Cliente c6 = new Cliente("534534","Pablo", "Gonzalez", "Bolivar 232","43543");
        Cliente c7 = new Cliente("543534","Martin", "Khea", "Calle 90","65454");


        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        clientes.add(c4);
        clientes.add(c5);
        clientes.add(c6);
        clientes.add(c7);


    }

    public void save(Cliente cliente) {
        clientes.add(cliente);
    }

    public void delete(String id) {
        Cliente clienteExistente = findOne(id);
        if (clienteExistente != null) {
            clienteExistente.setEstado(Cliente.Estado.DESHABILITADO);
        }
    }
    public List<Cliente> findAll() {
        List<Cliente>clientesHabilitados = new ArrayList<>();
        for(Cliente cr : clientes){
            if(cr.getEstado() == Cliente.Estado.HABILITADO){
                clientesHabilitados.add(cr);
            }
        }
        return clientesHabilitados;
    }


    public Cliente findOne(String id) {
        Cliente cliente = null;
        for (Cliente cr : clientes){
            if (cr.getCuit().equals(id)){
                cliente = cr;
            }
        }
        return cliente;
    }
    public void upDate(Cliente cliente) {
        Cliente clienteExistente = findOne(cliente.getCuit());
        if (clienteExistente != null) {
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setTelefono(cliente.getTelefono());
    }
}







}
