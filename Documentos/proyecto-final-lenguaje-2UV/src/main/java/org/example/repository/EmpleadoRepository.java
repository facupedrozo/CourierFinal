package org.example.repository;

import org.example.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository implements CRUD<Empleado>{
    private List<Empleado> empleados;
    private DepositoRepository depositoRepository;

    public EmpleadoRepository() {
        this.empleados = new ArrayList<>();
        this.depositoRepository = new DepositoRepository();
        upLoad();
    }


    @Override
    public void upLoad() {
        Empleado empleado1 = new Empleado("123", "Juan", "Perez", "Calle 23", "232323", depositoRepository.findOne("1"));
        Empleado empleado2 = new Empleado("456", "María", "López", "Avenida 45", "454545", depositoRepository.findOne("2"));
        Empleado empleado3 = new Empleado("789", "Pedro", "Gómez", "Calle 67", "676767", depositoRepository.findOne("3"));
        Empleado empleado4 = new Empleado("101", "Laura", "Rodríguez", "Avenida 89", "898989", depositoRepository.findOne("4"));
        Empleado empleado5 = new Empleado("121", "Carlos", "Sánchez", "Calle 10", "101010", depositoRepository.findOne("5"));


        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);
        empleados.add(empleado4);
        empleados.add(empleado5);
    }

    @Override
    public void save(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public void upDate(Empleado empleado) {
        if (findOne(empleado.getCUIT()) != null) {
            findOne(empleado.getCUIT()).setNombre(empleado.getNombre());
            findOne(empleado.getCUIT()).setApellido(empleado.getApellido());
            findOne(empleado.getCUIT()).setDireccion(empleado.getDireccion());
            findOne(empleado.getCUIT()).setTelefono(empleado.getTelefono());
        }
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado>empleadosHabilitados = new ArrayList<>();
        for(Empleado er : empleados){
            if(er.getEstado() == Empleado.Estado.HABILITADO){
                empleadosHabilitados.add(er);
            }
        }
        return empleadosHabilitados;
    }



    @Override
    public Empleado findOne(String id) {
        Empleado empleado = null;
        for (Empleado er:empleados){
            if (er.getCUIT().equals(id)){
                empleado = er;
            }
        }
        return empleado;
    }

    @Override
    public void delete(String id) {
        if(findOne(id) != null){
            findOne(id).setEstado(Empleado.Estado.DESHABILITADO);
        }
    }
}
