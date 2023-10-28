package org.example.service;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.repository.EmpleadoRepository;
import org.example.repository.PedidoRepository;
import org.example.repository.SectorRepository;
import org.example.repository.TransportistaRepository;


import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private SectorRepository sectorRepository;
    private TransportistaRepository transportistaRepository;
    private PedidoRepository pedidoRepository;

    private EmpleadoRepository empleadoRepository;

    private Pedido pedido;

    public PedidoService( PedidoRepository pedidoRepository) {
        this.sectorRepository = new SectorRepository();
        this.transportistaRepository = new TransportistaRepository();
        this.pedidoRepository=pedidoRepository;
        this.empleadoRepository = new EmpleadoRepository();
    }

    public void crearPedido(Pedido pedido) {
            pedidoRepository.crearPedido(pedido);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        List<Pedido> pedidosEncontrados = new ArrayList<>();
        for (Pedido pedido: pedidoRepository.obtenerTodosLosPedidos()
             ) {
            pedidosEncontrados.add(pedido);
        }
        return pedidosEncontrados;
    }

    public void agregarLineaDePedido(Pedido pedido, LineaPedido lineaPedido) {
        pedidoRepository.crearLineaPedido(pedido,lineaPedido);
    }


    public Pedido buscarPedidoPorNumero(String numero) {
        Pedido pedido = pedidoRepository.buscarPedidoPorNumero(numero);
        if (pedido == null) {
            return null;
        } else {
            return pedido;
        }
    }

    public void procesarPedido(String numero, String cuitEmpleado){
            pedidoRepository.procesarPedido(numero, cuitEmpleado);
    }

    public void completarPedido(String numero){
        pedidoRepository.completarPedido(numero);
    }

    public void enviarADespacho(String numero) {
        Pedido pedidoEncontrado = pedidoRepository.buscarPedidoPorNumero(numero);
        if (pedidoEncontrado != null) {
            pedidoRepository.enviarADespacho(numero);
        }
    }

    public void despacharPedido(String numero) {
        Pedido pedidoEncontrado = pedidoRepository.buscarPedidoPorNumero(numero);
        if (pedidoEncontrado != null) {
            pedidoRepository.despacharPedido(numero);

        }
    }

    public void enviarAEntrega(String numero, String cuitEmpleadoReceptor) {
        Pedido pedidoEncontrado = pedidoRepository.buscarPedidoPorNumero(numero);
        if (pedidoEncontrado != null) {
            pedidoRepository.enviarAEntrega(numero, cuitEmpleadoReceptor);
        }
    }

    public void entregarPedido(String numero, List<Integer> calificacionesProveedor) {
        Pedido pedidoEncontrado = pedidoRepository.buscarPedidoPorNumero(numero);
        if (pedidoEncontrado != null) {
            pedidoRepository.entregarPedido(numero, calificacionesProveedor);
        }
    }


    public void transitarPedido(String numero){
        pedidoRepository.transitarPedido(numero);
    }

    public int obtenerCantidadPedidosPorSucursal(String codigoSucursal) {
        return pedidoRepository.obtenerCantidadPedidosPorSucursal(codigoSucursal);
    }
    public int contarPedidosEnEstadoPendiente(){
        return pedidoRepository.contarPedidosEnEstadoPendiente();
    }


}


