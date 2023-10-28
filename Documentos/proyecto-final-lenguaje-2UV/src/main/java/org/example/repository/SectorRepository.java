package org.example.repository;


import lombok.Getter;
import lombok.Setter;
import org.example.model.Sector;


import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class SectorRepository implements CRUD<Sector>{
    private List<Sector> sectores;

    public SectorRepository() {
        sectores = new ArrayList<>();
        upLoad();
    }

    @Override
    public void upLoad() {
        sectores.add(new Sector("1", "Pendiente"));
        sectores.add(new Sector("2", "En Proceso"));
        sectores.add(new Sector("3", "Completo"));
        sectores.add(new Sector("4", "Esperando Despacho"));
        sectores.add(new Sector("5", "Despacho"));
        sectores.add(new Sector("6", "Esperando Entrega"));
        sectores.add(new Sector("7", "Entrega"));
    }

    @Override
    public void save(Sector sector) {
        sectores.add(sector);
    }

    @Override
    public void upDate(Sector sector) {
        for (Sector sr : sectores) {
            if (sr.getCodigo().equals(sector.getCodigo())) {
                sr.setDescripcion(sector.getDescripcion());
            }
        }
    }

    public List<Sector> findAll() {
        List<Sector>sectoresHabilitados = new ArrayList<>();
        for(Sector sr : sectores){
            if(sr.getEstado() == Sector.Estado.HABILITADO){
                sectoresHabilitados.add(sr);
            }
        }
        return sectoresHabilitados;
    }


    @Override
    public Sector findOne(String id) {
        Sector sector = null;
        for (Sector sr : sectores){
            if (sr.getCodigo().equals(id)){
                sector = sr;
            }
        }
        return sector;
    }

    @Override
    public void delete(String id) {
        if (findOne(id) != null) {
            findOne(id).setEstado(Sector.Estado.DESHABILITADO);
        }
    }





}
