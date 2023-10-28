package org.example.controller;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.service.PedidoService;

import java.util.List;

public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void crearPedido(Pedido pedido){
        pedidoService.crearPedido(pedido);
    }

    public void agregarLineaPedido(Pedido pedido, LineaPedido lineaPedido){
        pedidoService.agregarLineaDePedido(pedido, lineaPedido);
    }

    public void mostrarPedido(String nro){
        for (Pedido pedidos: pedidoService.obtenerTodosLosPedidos()
             ) {
            if (pedidos.getNumeroPedido().equals(nro)){
                System.out.println(pedidos.toString());
            }
        }
    }

    public List<Pedido> mostrarTodosLosPedidos(){
        return pedidoService.obtenerTodosLosPedidos();
    }

    public Pedido buscarPedidoPorNumero(String numero){
        return pedidoService.buscarPedidoPorNumero(numero);
    }

    public void procesarPedido(String numero,String cuitEmpleado) {
        pedidoService.procesarPedido(numero, cuitEmpleado);
    }

    public void completarPedido(String numero) {
        pedidoService.completarPedido(numero);
    }

    public void enviarADespacho(String numero) {
        pedidoService.enviarADespacho(numero);
    }
    public void despacharPedido(String numero) {
        pedidoService.despacharPedido(numero);
    }
    public void enviarAEntrega(String numero, String cuitEmpleadoReceptor){
        pedidoService.enviarAEntrega(numero, cuitEmpleadoReceptor);
    }
    public void entregarPedido(String numero, List<Integer> calificacionesProveedor) {
        pedidoService.entregarPedido(numero, calificacionesProveedor);
    }

    public void transitarPedido(String numero){
        pedidoService.transitarPedido(numero);
    }
    public int obtenerCantidadPedidoPorSucursal(String sucursal){
        return pedidoService.obtenerCantidadPedidosPorSucursal(sucursal);
    }
    public int contarPedidosEnEstadoPendiente(){
        return pedidoService.contarPedidosEnEstadoPendiente();
    }
}
