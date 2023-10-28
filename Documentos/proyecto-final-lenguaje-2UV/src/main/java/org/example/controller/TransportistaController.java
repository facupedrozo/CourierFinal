package org.example.controller;

import org.example.model.Transportista;
import org.example.service.TransportistaService;

import java.util.List;

public class TransportistaController {
    private TransportistaService transportistaService;

    public TransportistaController(TransportistaService transportistaService) {
        this.transportistaService = transportistaService;
    }

    public void create(Transportista transportista){
        transportistaService.save(transportista);
    }

    public Transportista findOne(String cuit){
        return transportistaService.findOne(cuit);
    }

    public void delete(String cuit){
        transportistaService.delete(cuit);
    }

    public List<Transportista> findAll(){
       return transportistaService.findAll();
    }

    public void upDate(Transportista transportista) {
        transportistaService.upDate(transportista);
    }

}
