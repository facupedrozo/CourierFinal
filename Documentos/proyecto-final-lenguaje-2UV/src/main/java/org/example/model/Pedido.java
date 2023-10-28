package org.example.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class Pedido {


    private String numeroPedido;
    private LocalDate inicioPedido ;
    private LocalDate finPedido;
    private Cliente cliente;
    private Deposito depositoOrigen;
    private Deposito depositoDestino;
    private Seguimiento seguimiento;
    private boolean enTransito;
    private Transportista transportista;
    private List<LineaPedido> lineasPedidos;
    private String estadoPedido;
    private Empleado empleado;
    private Remito remito;

    public Pedido(String numeroPedido, Cliente cliente, Deposito depositoOrigen, Deposito depositoDestino, Transportista transportista, String estadoPedido, Seguimiento seguimiento) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.depositoOrigen = depositoOrigen;
        this.depositoDestino = depositoDestino;
        this.transportista = transportista;
        this.lineasPedidos = new ArrayList<>();
        this.estadoPedido = estadoPedido;
        this.seguimiento = seguimiento;

    }

    public Pedido() {
        this.lineasPedidos = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido='" + numeroPedido + '\'' +
                ", inicioPedido=" + inicioPedido +
                ", finPedido=" + finPedido +
                ", cliente=" + cliente +
                ", depositoOrigen=" + depositoOrigen +
                ", depositoDestino=" + depositoDestino +
                ", seguimiento=" + seguimiento +
                ", enTransito=" + enTransito +
                ", transportista=" + transportista +
                ", lineasPedidos=" + lineasPedidos +
                ", estadoPedido='" + estadoPedido + '\'' +
                ", empleado=" + empleado +
                '}';
    }
}
