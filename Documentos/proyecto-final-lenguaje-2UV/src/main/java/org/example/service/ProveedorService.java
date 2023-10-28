package org.example.service;

import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService implements CRUD<Proveedor> {
    ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public void save(Proveedor proveedor) {
        if (findOne(proveedor.getCuit()) == null) {
            proveedorRepository.save(proveedor);
        }
    }

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }



    public Proveedor findOne(String cuit) {
        Proveedor proveedor = null;
        if (proveedorRepository.findOne(cuit) != null) {
            proveedor = proveedorRepository.findOne(cuit);
        }
        return proveedor;
    }



    public void upDate(Proveedor proveedor) {
        if (findOne(proveedor.getCuit()) != null) {
            proveedorRepository.upDate(proveedor);
        }
    }

    public void delete(String cuit) {
        if (findOne(cuit) != null) {
            proveedorRepository.delete(cuit);
        }
    }
}
