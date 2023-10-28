package org.example.service;

import org.example.repository.InformeRepository;

public class InformeService {
    private InformeRepository informeRepository;

    public InformeService(InformeRepository informeRepository) {
        this.informeRepository = informeRepository;
    }

    public int obtenerCantidadPedidosPorSucursal(String codigoSucursal) {
        return informeRepository.obtenerCantidadPedidosPorSucursal(codigoSucursal);
    }

    public int contarPedidosEnEstadoPendiente() {
        return informeRepository.contarPedidosEnEstadoPendiente();
    }

    public int obtenerHistorialDeUnCliente(String cuit){
       return  informeRepository.obtenerHistorialDeUnCliente(cuit);
    }


    public void mostrarEstadoDePedidos(){

    }
}
