package org.example.service;

import org.example.model.Cliente;
import org.example.model.Deposito;
import org.example.model.Empleado;
import org.example.repository.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService implements CRUD<Empleado>{
    private EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = new EmpleadoRepository();
    }


    @Override
    public void save(Empleado empleado) {
        if (findOne(empleado.getCUIT())!=null) {
            empleadoRepository.save(empleado);
        }
    }

    @Override
    public void upDate(Empleado empleado) {
        if(findOne(empleado.getCUIT()) != null){
            empleadoRepository.upDate(empleado);
        }
    }

    @Override
    public Empleado findOne(String cuit) {
        Empleado empleado = null;
        if (empleadoRepository.findOne(cuit)!= null) {
           empleado = empleadoRepository.findOne(cuit);

        }
        return empleado;
    }


    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }


    @Override
    public void delete(String cuit) {
        if (findOne(cuit)!= null){
            empleadoRepository.delete(cuit);
        }
    }
}
