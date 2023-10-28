package org.example.repository;

import org.example.model.Remito;

import java.util.List;

public class RemitoRepository {
    private List<Remito> remitos;
    private TransportistaRepository transportistaRepository;
    private EmpleadoRepository empleadoRepository;

    public RemitoRepository(){
        this.empleadoRepository = new EmpleadoRepository();
        this.transportistaRepository = new TransportistaRepository();
        cargarRemitos();
    }


    public void cargarRemitos(){
//        Remito remito1 = new Remito("1", LocalDate.now(), transportistaRepository.buscarTransportistaPorCuit(), empleado1, empleado2);
//        Remito remito2 = new Remito("2", LocalDate.now(), transportista2, empleado2, empleado1);
//        Remito remito3 = new Remito("3", LocalDate.now(), transportista1, empleado1, empleado2);
//        Remito remito4 = new Remito("4", LocalDate.now(), transportista2, empleado2, empleado1);
//        Remito remito5 = new Remito("5", LocalDate.now(), transportista1, empleado1, empleado2);
//
//
//        remitos.add(remito1);
//        remitos.add(remito2);
//        remitos.add(remito3);
//        remitos.add(remito4);
//        remitos.add(remito5);
    }
}
