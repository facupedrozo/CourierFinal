package org.example.repository;

import org.example.model.TipoTransportista;
import org.example.model.Transportista;

import java.util.ArrayList;
import java.util.List;

public class TransportistaRepository implements CRUD<Transportista>{
    private List<Transportista> transportistas;
    private List<TipoTransportista> tipoTransportistas;
    public TransportistaRepository() {
        this.transportistas = new ArrayList<>();
        this.tipoTransportistas= new ArrayList<>();
        upLoad();
    }
    @Override
    public void upLoad() {
        TipoTransportista terrestre = new TipoTransportista("Terrestre");
        TipoTransportista aereo = new TipoTransportista("Aereo");
        TipoTransportista maritimo = new TipoTransportista("Maritimo");

        tipoTransportistas.add(aereo);
        tipoTransportistas.add(maritimo);
        tipoTransportistas.add(terrestre);

        Transportista t1 = new Transportista("12321","Correo Argentino","12342", "dsads@gmail.com", terrestre);
        Transportista t2 = new Transportista("3213","OCA","4352", "ds@gmail.com",aereo);
        Transportista t3 = new Transportista("232","OCASA","7653432", "asd@gmail.com",aereo);
        Transportista t4 = new Transportista("34","AMAZON","4235234", "34@gmail.com",maritimo);
        Transportista t5 = new Transportista("676","DHL","12321", "dsadds@gmail.com",terrestre);

        transportistas.add(t1);
        transportistas.add(t2);
        transportistas.add(t3);
        transportistas.add(t4);
        transportistas.add(t5);

    }

    @Override
    public void save(Transportista transportista) {
        transportistas.add(transportista);
    }

    @Override
    public void upDate(Transportista transportista) {
        Transportista transportistaExistente = findOne(transportista.getCuit());
        if (transportistaExistente != null) {
            transportistaExistente.setNombre(transportista.getNombre());
            transportistaExistente.setTelefono(transportista.getTelefono());
            transportistaExistente.setEmail(transportista.getEmail());
            transportistaExistente.setTipo(transportista.getTipo());
        }
    }

    @Override
    public List<Transportista> findAll() {
        List<Transportista>transportistasHabilitados = new ArrayList<>();
        for(Transportista tr : transportistas){
            if(tr.getEstado() == Transportista.Estado.HABILITADO){
                transportistasHabilitados.add(tr);
            }
        }
        return transportistasHabilitados;
    }


    @Override
    public Transportista findOne(String id) {
        Transportista transportista = null;
        for (Transportista tr : transportistas){
            if (tr.getCuit().equals(id)){
                transportista = tr;
            }
        }
        return transportista;
    }

    @Override
    public void delete(String id) {
        if (findOne(id) != null) {
            findOne(id).setEstado(Transportista.Estado.DESHABILITADO);
        }
    }
}
