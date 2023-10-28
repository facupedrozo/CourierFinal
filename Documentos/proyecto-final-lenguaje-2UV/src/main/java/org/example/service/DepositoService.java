package org.example.service;

import org.example.model.Cliente;
import org.example.model.Deposito;
import org.example.model.Sector;
import org.example.repository.DepositoRepository;

import java.util.ArrayList;
import java.util.List;

public class DepositoService implements CRUD<Deposito>{
    private DepositoRepository depositoRepository;

    public DepositoService(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    public void save(Deposito deposito) {
        if (findOne(deposito.getCodigo()) == null) {
            depositoRepository.save(deposito);
        }
    }

    public List<Deposito> findAll() {
       return depositoRepository.findAll();
    }


    public Deposito findOne(String codigo) {
        Deposito deposito = null;
        if (depositoRepository.findOne(codigo) != null) {
            deposito = depositoRepository.findOne(codigo);
        }
        return deposito;
    }


    public void upDate(Deposito deposito) {
        if (findOne(deposito.getCodigo()) != null) {
            depositoRepository.upDate(deposito);
        }
    }

    public void delete(String codigo) {
        if (findOne(codigo) != null) {
            depositoRepository.delete(codigo);
        }
    }

}
