package org.example.repository;

import org.example.model.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorRepository implements CRUD<Proveedor>{
    public List<Proveedor> proveedores;

    public ProveedorRepository() {
        proveedores = new ArrayList<>();
        upLoad();
    }

    @Override
    public void upLoad() {
        Proveedor p1 = new Proveedor("0001","Apple","villa lugano", "123","manza@gmail");
        Proveedor p2 = new Proveedor("0002","Yamaha","Villa Cabello", "345","sancor@gmail");
        Proveedor p3 = new Proveedor("0003","Nike","villa cariñito", "678","nike@gmail");
        Proveedor p4 = new Proveedor("0004","Adidas","villa miseria", "910","adid@gmail");
        Proveedor p5 = new Proveedor("0005","Honda","villa 327", "012","honda@gmail");
        proveedores.add(p1);
        proveedores.add(p2);
        proveedores.add(p3);
        proveedores.add(p4);
        proveedores.add(p5);
    }

    @Override
    public void save(Proveedor proveedor) {
        proveedores.add(proveedor);

    }

    @Override
    public void upDate(Proveedor proveedor) {
        if(findOne(proveedor.getCuit()) != null){
            findOne(proveedor.getCuit()).setNombre(proveedor.getNombre());
            findOne(proveedor.getCuit()).setDireccion(proveedor.getDireccion());
            findOne(proveedor.getCuit()).setTelefono(proveedor.getTelefono());
            findOne(proveedor.getCuit()).setEmail(proveedor.getEmail());
        }

    }

    @Override
    public List<Proveedor> findAll() {
        List<Proveedor>proveedoresHabilitados = new ArrayList<>();
        for(Proveedor pr : proveedores){
            if(pr.getEstado() == Proveedor.Estado.Habilitado){
                proveedoresHabilitados.add(pr);
            }
        }
        return proveedoresHabilitados;
    }



    @Override
    public Proveedor findOne(String cuit) {
        Proveedor proveedor = null;
        for (Proveedor pr : proveedores){
            if (pr.getCuit().equals(cuit)){
                proveedor = pr;
            }
        }
        return proveedor;
    }

    @Override
    public void delete(String cuit) {
        if(findOne(cuit) != null){
            findOne(cuit).setEstado(Proveedor.Estado.Invalido);
        }
    }
}
