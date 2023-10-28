package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Deposito;
import org.example.model.Posicion;
import org.example.model.Sector;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class DepositoRepository implements CRUD<Deposito>{
    private List<Deposito> depositos;
    private SectorRepository sectorRepository;
    public DepositoRepository() {
        this.depositos = new ArrayList<>();
        this.sectorRepository = new SectorRepository();
        upLoad();
    }

    @Override
    public void upLoad() {
        Deposito deposito1 = new Deposito("1","Deposito China","Calle 1","34543534","depositoCH@gmail.com", "Asia",new Posicion(39.900853,116.399813));
        Deposito deposito2 = new Deposito("2","Deposito Australia","San martin 2324","1232312","depositoAU@gmail.com", "Oceania", new Posicion(-22.593680,144.544854));
        Deposito deposito3 = new Deposito("3","Deposito Argentina","San juan 2324","4132","depositoAR@gmail.com", "America",  new Posicion(-34.537211,-58.547629));
        Deposito deposito4 = new Deposito("4", "Deposito Sudafrica", "Av. Colón 1234", "3512345678", "depositoSA@gmail.com", "Africa", new Posicion(-30.849319,24.201486));
        Deposito deposito5 = new Deposito("5", "Deposito España", "Av. San Martín 4321", "2612345678", "depositoES@gmail.com", "Europa",new Posicion(39.514578,-2.490630));


        deposito1.getSectores().addAll(sectorRepository.findAll());
        deposito2.getSectores().addAll(sectorRepository.findAll());
        deposito3.getSectores().addAll(sectorRepository.findAll());
        deposito4.getSectores().addAll(sectorRepository.findAll());
        deposito5.getSectores().addAll(sectorRepository.findAll());

        depositos.add(deposito1);
        depositos.add(deposito2);
        depositos.add(deposito3);
        depositos.add(deposito4);
        depositos.add(deposito5);
    }

    @Override
    public void save(Deposito deposito) {
        depositos.add(deposito);
    }

    @Override
    public void upDate(Deposito deposito) {
        if (findOne(deposito.getCodigo()) == null){
            deposito.setNombre(deposito.getNombre());
            deposito.setDireccion(deposito.getDireccion());
            deposito.setTelefono(deposito.getTelefono());
            deposito.setEmail(deposito.getEmail());
            deposito.setSectores(deposito.getSectores());
        }
    }

    @Override
    public List<Deposito> findAll() {
        List<Deposito>depositosHabilitados = new ArrayList<>();
        for(Deposito dr : depositos){
            if(dr.getEstado() == Deposito.Estado.HABILITADO){
                depositosHabilitados.add(dr);
            }
        }
        return depositosHabilitados;
    }



    @Override
    public Deposito findOne(String id) {
        Deposito deposito = null;
        for (Deposito dr : depositos){
            if (dr.getCodigo().equals(id)){
                deposito = dr;
            }
        }
        return deposito;
    }

    @Override
    public void delete(String id) {
        if (findOne(id) != null) {
            findOne(id).setEstado(Deposito.Estado.DESHABILITADO);
        }
    }
    public List<Sector> buscarSectoresDeUnDepositoPorCodigo(String codigo){
        List<Sector> sectoresDelDeposito = new ArrayList<>();
        Deposito deposito = findOne(codigo);
        for (Sector sector: deposito.getSectores()
        ) {
            sectoresDelDeposito.add(sector);
        }
        return sectoresDelDeposito;
    }
}
