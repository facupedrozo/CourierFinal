package org.example.controller;

import org.example.model.Empleado;
import org.example.service.EmpleadoService;

import java.util.List;

public class EmpleadoController implements  CRUD<Empleado> {
    EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @Override
    public void create(Empleado empleado) {
        empleadoService.save(empleado);
    }

    @Override
    public Empleado findOne(String id) {
        return empleadoService.findOne(id);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoService.findAll();
    }


    @Override
    public void upDate(Empleado empleado) {
        empleadoService.upDate(empleado);

    }

    @Override
    public void delete(String id) {
        empleadoService.delete(id);

    }
}
