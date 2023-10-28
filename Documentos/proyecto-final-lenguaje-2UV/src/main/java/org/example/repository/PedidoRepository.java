package org.example.repository;

import org.example.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private List<Pedido> pedidos;
    private List<LineaPedido> lineaPedidos;
    private SectorRepository sectorRepository;
    private EmpleadoRepository empleadoRepository;
    public PedidoRepository() {
        pedidos = new ArrayList<>();
        this.sectorRepository = new SectorRepository();
        this.empleadoRepository = new EmpleadoRepository();
    }

    public void crearPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido buscarPedidoPorNumero(String numeroDePedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido().equals(numeroDePedido)) {
                return pedido;
            }
        }
        return null;
    }
    public void crearLineaPedido(Pedido pedido, LineaPedido lineaPedido){
        pedido.getLineasPedidos().add(lineaPedido);
    }
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidos;
    }
    public void procesarPedido(String numero, String cuitEmpleado){
        Pedido pedidoEncontrado = buscarPedidoPorNumero(numero);
        if (pedidoEncontrado!=null){
            pedidoEncontrado.setEmpleado(empleadoRepository.findOne(cuitEmpleado));
            pedidoEncontrado.setEstadoPedido(pedidoEncontrado.getDepositoOrigen().getSectores().get(1).getDescripcion());
        }

    }


    public void completarPedido(String numero){
        Pedido pedidoEncontrado = buscarPedidoPorNumero(numero);
        if (pedidoEncontrado!=null){
            pedidoEncontrado.setEstadoPedido(pedidoEncontrado.getDepositoOrigen().getSectores().get(2).getDescripcion());
        }
    }

    public void enviarADespacho(String numero){
        Pedido pedidoEncontrado = buscarPedidoPorNumero(numero);
        if (pedidoEncontrado!=null){
            pedidoEncontrado.setEstadoPedido(pedidoEncontrado.getDepositoOrigen().getSectores().get(3).getDescripcion());
        }
    }

public void despacharPedido(String numero) {
    Pedido pedidoEncontrado = buscarPedidoPorNumero(numero);
    if (pedidoEncontrado != null) {
        pedidoEncontrado.setEstadoPedido(pedidoEncontrado.getDepositoOrigen().getSectores().get(4).getDescripcion());

        if (pedidoEncontrado.getRemito() == null) {
            // Generar el remito y agregarlo al pedido
            LocalDate fechaEmision = LocalDate.now();
            Transportista transportista = pedidoEncontrado.getTransportista();
            Empleado empleadoEmisor = pedidoEncontrado.getEmpleado();
            Empleado empleadoReceptor = null; // El receptor se establecerá cuando se entregue el pedido
            Remito remito = new Remito(fechaEmision, transportista, empleadoEmisor, empleadoReceptor);
            pedidoEncontrado.setRemito(remito);
        }

        if (pedidoEncontrado.getSeguimiento() == null) {
            Seguimiento seguimiento = new Seguimiento(pedidoEncontrado);

            // Obtener la latitud y longitud del depósito de origen
            Deposito depositoOrigen = pedidoEncontrado.getDepositoOrigen();
            double latitud = depositoOrigen.getPosicion().getLatitud();
            double longitud = depositoOrigen.getPosicion().getLongitud();

            seguimiento.setLatitud(latitud);
            seguimiento.setLongitud(longitud);
            seguimiento.setNumeroDeRastreo(seguimiento.generarNumeroRastreo());

            pedidoEncontrado.setSeguimiento(seguimiento);
        }
    }
}

    public void enviarAEntrega(String numero, String cuitEmpleadoReceptor) {
        Pedido pedidoEncontrado = buscarPedidoPorNumero(numero);
        if (pedidoEncontrado != null) {
            pedidoEncontrado.setEstadoPedido(pedidoEncontrado.getDepositoDestino().getSectores().get(5).getDescripcion());

            if (pedidoEncontrado.getSeguimiento() != null) {
                // Obtener la latitud y longitud del depósito de destino  para e lseguimiento
                Deposito depositoDestino = pedidoEncontrado.getDepositoDestino();
                double latitud = depositoDestino.getPosicion().getLatitud();
                double longitud = depositoDestino.getPosicion().getLongitud();

                // Actualizamos la latitud y longitud en el seguimiento
                pedidoEncontrado.getSeguimiento().setLatitud(latitud);
                pedidoEncontrado.getSeguimiento().setLongitud(longitud);
                pedidoEncontrado.setEnTransito(false);
            }
            Remito remito = pedidoEncontrado.getRemito();

            if (remito != null) {
                // Buscar el empleado receptor por su CUIT
                Empleado empleadoReceptor = empleadoRepository.findOne(cuitEmpleadoReceptor);

                if (empleadoReceptor != null) {
                    // Asignar el empleado receptor al remito
                    remito.setEmpleadoReceptor(empleadoReceptor);
                }
            }
        }
    }

  public void entregarPedido(String numero, List<Integer> calificacionesProveedor) {
      Pedido pedidoEncontrado = buscarPedidoPorNumero(numero);
      if (pedidoEncontrado != null) {
          pedidoEncontrado.setEstadoPedido(pedidoEncontrado.getDepositoDestino().getSectores().get(6).getDescripcion());
          List<LineaPedido> lineasPedido = pedidoEncontrado.getLineasPedidos();
          if (lineasPedido.size() != calificacionesProveedor.size()) {
              System.out.println("Error: La cantidad de calificaciones no coincide con la cantidad de proveedores.");
              return;
          } else {
              pedidoEncontrado.setFinPedido(LocalDate.now());
          }

          // Establecemos la calificación del proveedor para cada línea de pedido
          for (int i = 0; i < lineasPedido.size(); i++) {
              LineaPedido lineaPedido = lineasPedido.get(i);
              lineaPedido.setCalificacionProveedor(calificacionesProveedor.get(i));
          }
      }


  }

    public void transitarPedido(String numero){
        Pedido pedido = buscarPedidoPorNumero(numero);
        if (pedido!=null){
            pedido.setEnTransito(true);
            pedido.setEstadoPedido("En transito");
        }
    }

    public int obtenerCantidadPedidosPorSucursal(String codigoSucursal) {
        int cantidadPedidos = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getDepositoOrigen().getCodigo().equals(codigoSucursal)) {
                cantidadPedidos++;
            }
        }
        return cantidadPedidos;
    }
//COMENTARIO EN PEDIDOREPOSITORY
public int contarPedidosEnEstadoPendiente() {
    int contador = 0;
    for (Pedido pedido : pedidos) {
        if (pedido.getEstadoPedido().equals("Pendiente")) {
            contador++;
        }
    }
    return contador;
}


}
