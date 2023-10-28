package org.example.controller;

import org.example.model.Deposito;
import org.example.model.Sector;
import org.example.service.DepositoService;

import java.util.List;

public class DepositoController implements CRUD<Deposito>{
    private DepositoService depositoService;

    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @Override
    public void create(Deposito deposito) {
        depositoService.save(deposito);
    }

    @Override
    public Deposito findOne(String id) {
        return depositoService.findOne(id);
    }

    @Override
    public List<Deposito> findAll() {
        return depositoService.findAll();
    }


    @Override
    public void upDate(Deposito deposito) {
        depositoService.upDate(deposito);

    }

    @Override
    public void delete(String id) {
        depositoService.delete(id);
    }
}
