package org.example.view;

import org.example.controller.*;
import org.example.model.*;
import org.example.repository.*;
import org.example.service.*;
import org.example.util.CalcularDistancia;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPedido {
    private boolean regresarMenuPrincipal;//**
    PedidoController pedidoController;
     DepositoController depositoController;
     SectorRepository sectorRepository;
     TransportistaController transportistaController;
     ProductoController productoController;

     EmpleadoController empleadoController;
     EmpleadoRepository empleadoRepository;
     EmpleadoService empleadoService;



    ClienteController clienteController;


    public MenuPedido(PedidoController pedidoController, DepositoController depositoController, SectorRepository sectorRepository, TransportistaController transportistaController, ProductoController productoController, ClienteController clienteController, EmpleadoRepository empleadoRepository, EmpleadoService empleadoService,EmpleadoController empleadoController) {
        this.pedidoController = pedidoController;
        this.depositoController = depositoController;
        this.sectorRepository = sectorRepository;
        this.transportistaController = transportistaController;
        this.productoController = productoController;
        this.clienteController = clienteController;
        this.empleadoRepository = empleadoRepository;
        this.empleadoService = new EmpleadoService(empleadoRepository);
        this.empleadoController=empleadoController;

    }

    public void mostrarMenuPedido() {
        int seguirAgregandoProductos;

        Scanner scanner = new Scanner(System.in);

            do {//**
                System.out.println("Por favor ingrese la opción que desee: ");
                System.out.println("1. Crear Pedido");
                System.out.println("2. Procesar pedido");
                System.out.println("3. Completar pedido");
                System.out.println("4. Enviar a despacho");
                System.out.println("5. Despachar pedido");
                System.out.println("6. Transitar pedido");
                System.out.println("7. Enviar a sucursal destino");
                System.out.println("8. Entregar pedido");
                System.out.println("9. Calcular distancia del pedido");
                System.out.println("10.Ver todos los pedidos ");
                System.out.println("11. Salir del menu pedido");
                System.out.println("----------------------------");

                int opcion = scanner.nextInt();
                scanner.nextLine();


                switch (opcion) {
                    case 1:
                        System.out.println("Por favor ingrese el numero del pedido");
                        String numeroPedido = scanner.nextLine();
                        Pedido pedidoExiste=pedidoController.buscarPedidoPorNumero(numeroPedido);
                        if(pedidoExiste!=null){
                            System.out.println(" El pedido ya existe no se puede crear un pedido con el mismo numero ");
                            break;
                        }


                        System.out.println("Por favor seleccione el cliente del pedido");
                        for (Cliente cl : clienteController.findAll()
                        ) {
                            System.out.println(cl.toString());
                        }

                        String cuitCliente = scanner.next();
                        Cliente cliente = clienteController.findOne(cuitCliente);

                        System.out.println("Por favor seleccione el deposito de origen");
                        System.out.println("----------------------------");
                        for (Deposito dp : depositoController.findAll()
                        ) {
                            System.out.println(dp.toString());
                        }

                        System.out.println("----------------------------");
                        scanner.nextLine();
                        String codigoDeposito = scanner.nextLine();
                        Deposito depositoOrigen = depositoController.findOne(codigoDeposito);

                        System.out.println("Por favor seleccione el deposito de destino");
                        System.out.println("----------------------------");
                        for (Deposito dp : depositoController.findAll()
                        ) {
                            System.out.println(dp.toString());
                        }
                        System.out.println("----------------------------");
                        String codigoDepositoDestino = scanner.nextLine();
                        Deposito depositoDestino = depositoController.findOne(codigoDepositoDestino);

                        System.out.println("Por favor seleccione el transportista");
                        for (Transportista tr : transportistaController.findAll()
                        ) {
                            System.out.println(tr.toString());
                        }
                        String cuitTransportista = scanner.nextLine();

                        Transportista transportista = transportistaController.findOne(cuitTransportista);

                        if ((transportista != null && cliente != null && depositoOrigen != null && depositoDestino != null)) {
                            String estadoPedido = depositoOrigen.getSectores().get(0).getDescripcion();
                            Seguimiento seguimiento = new Seguimiento(LocalDate.of(2023, 10, 20), depositoOrigen.getPosicion().getLatitud(), depositoOrigen.getPosicion().getLongitud());
                            Pedido pedido = new Pedido(numeroPedido, cliente, depositoOrigen, depositoDestino, transportista, estadoPedido, seguimiento);
                            pedido.setInicioPedido(LocalDate.now());

                            do {
                                System.out.println("Por favor ingrese el producto");
                                for (Producto pr : productoController.findAll()
                                ) {
                                    System.out.println(pr.toString());
                                }
                                String codigoProducto = scanner.nextLine();
                                int cantidadProducto = 0;
                                try {
                                    System.out.println("Por favor ingrese la cantidad");
                                    cantidadProducto = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Se produjo un error con la cantidad introducida");
                                }

                                scanner.nextLine();
                                LineaPedido lineaPedido = new LineaPedido(productoController.findOne(codigoProducto), cantidadProducto);
                                if (lineaPedido.getProducto() != null) {
                                    pedido.getLineasPedidos().add(lineaPedido);
                                }
                                System.out.println("----------------------------");
                                System.out.println("Desea seguir agregando productos?");
                                System.out.println("1.SI");
                                System.out.println("2.NO");
                                System.out.println("----------------------------");
                                seguirAgregandoProductos = scanner.nextInt();
                                scanner.nextLine();
                            } while (seguirAgregandoProductos == 1);
                            if (pedido.getLineasPedidos().isEmpty()) {
                                System.out.println("Error, vuelva a intentarlo nuevamente");
                                break;
                            }
                            pedidoController.crearPedido(pedido);
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("Error, vuelva a intentarlo nuevamente");
                            System.out.println("----------------------------");
                            break;
                        }

                        break;

                    case 2:
                        System.out.println("Por favor ingrese el numero del pedido para procesarlo");
                        String nroPedido = scanner.nextLine();
                        System.out.println("Por favor asigne el empleado a cargo");
                        empleadoController.findAll();
                        for (Empleado empleadoRecorrido : empleadoController.findAll()
                        ) {
                            System.out.println(empleadoRecorrido.toString());
                        }
                        String cuitEmpleado = scanner.nextLine();

                        Pedido pedidoEncontrado = pedidoController.buscarPedidoPorNumero(nroPedido);


                        if ((pedidoEncontrado != null && empleadoController.findOne(cuitEmpleado) != null) && pedidoEncontrado.getEstadoPedido().equals("Pendiente")) {

                            pedidoController.procesarPedido(nroPedido, cuitEmpleado);
                            System.out.println("El pedido fue procesado");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se pudo procesar, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;

                    case 3:
                        System.out.println("Por favor ingrese el numero del pedido para completarlo");
                        String nroPedidoCompletar = scanner.nextLine();
                        Pedido pedidoCompletarPedido = pedidoController.buscarPedidoPorNumero(nroPedidoCompletar);
                        if ((pedidoCompletarPedido != null) && pedidoCompletarPedido.getEstadoPedido().equals("En Proceso")) {
                            pedidoController.completarPedido(nroPedidoCompletar);
                            System.out.println("El pedido fue completado");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se pudo completar, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;
                    case 4:
                        System.out.println("Por favor ingrese el numero del pedido para enviarlo a despacho");
                        String nroPedidoEnviarADespacho = scanner.nextLine();
                        Pedido pedidoEnviarADespacho = pedidoController.buscarPedidoPorNumero(nroPedidoEnviarADespacho);
                        if ((pedidoEnviarADespacho != null) && pedidoEnviarADespacho.getEstadoPedido().equals("Completo")) {
                            pedidoController.enviarADespacho(nroPedidoEnviarADespacho);
                            System.out.println("El pedido fue enviado a despacho");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se pudo enviar a despacho, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;
                    case 5:
                        System.out.println("Por favor ingrese el número del pedido para despacharlo");
                        String nroPedidoDespacho = scanner.nextLine();
                        Pedido pedidoDespacho = pedidoController.buscarPedidoPorNumero(nroPedidoDespacho);
                        if ((pedidoDespacho != null) && pedidoDespacho.getEstadoPedido().equals("Esperando Despacho")) {
                            pedidoController.despacharPedido(nroPedidoDespacho);
                            System.out.println("El pedido fue despachado");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se encontró, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;
                    case 6:
                        System.out.println("Por favor ingrese el número del pedido para enviarlo a transito");
                        String nroPedidoTransito = scanner.nextLine();
                        Pedido pedidoTransito = pedidoController.buscarPedidoPorNumero(nroPedidoTransito);
                        if ((pedidoTransito != null) && pedidoTransito.getEstadoPedido().equals("Despacho")) {
                            pedidoController.transitarPedido(nroPedidoTransito);
                            System.out.println("El pedido fue enviado a transito");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se encontró, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }

                        break;
                    case 7:
                        System.out.println(" Por favor ingrese el numero del pedido para enviarlo a entrega ");
                        String numeroPedidoEnviarAEntrega = scanner.nextLine();
                        System.out.println("Por favor ingrese el CUIT del empleado receptor:");
                        for (Empleado ep : empleadoController.findAll()
                        ) {
                            System.out.println(ep.toString());
                        }
                        String cuitEmpleadoReceptor = scanner.nextLine();
                        Pedido pedidoEnviarAEntrega = pedidoController.buscarPedidoPorNumero(numeroPedidoEnviarAEntrega);

                        if ((pedidoEnviarAEntrega != null && empleadoController.findOne(cuitEmpleadoReceptor) != null) && pedidoEnviarAEntrega.getEstadoPedido().equals("En transito")) {

                            pedidoController.enviarAEntrega(numeroPedidoEnviarAEntrega, cuitEmpleadoReceptor);
                            System.out.println("El pedido fue enviado a entrega");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se encontró, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;

                    case 8:
                        System.out.println("Por favor ingrese el número del pedido para entregarlo");
                        String nroPedidoEntregar = scanner.nextLine();
                        Pedido pedidoEntregar = pedidoController.buscarPedidoPorNumero(nroPedidoEntregar);
                        if ((pedidoEntregar != null) && pedidoEntregar.getEstadoPedido().equals("Esperando Entrega")) {
                            List<LineaPedido> lineasPedido = pedidoEntregar.getLineasPedidos();
                            List<Integer> calificacionesProveedor = new ArrayList<>();

                            for (LineaPedido lineaPedido : lineasPedido) {
                                int calificacion = 0;
                                try {
                                    System.out.println("Por favor ingrese la calificación del proveedor del producto " + lineaPedido.getProducto().getNombre() + " :");
                                    calificacion = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Se produjo un error en la calificación");
                                    break;
                                }
                                scanner.nextLine(); // Consumir el salto de línea
                                calificacionesProveedor.add(calificacion);
                            }

                            pedidoController.entregarPedido(nroPedidoEntregar, calificacionesProveedor);
                            System.out.println("El pedido fue entregado");
                            System.out.println(pedidoEntregar.toString());
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se encontró, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;
                    case 9:
                        CalcularDistancia calcularDistancia = new CalcularDistancia();
                        System.out.println("Por favor ingrese el número del pedido para calcular la distancia");
                        String nroPedidoDistancia = scanner.nextLine();
                        Pedido pedidoDistancia = pedidoController.buscarPedidoPorNumero(nroPedidoDistancia);
                        if (pedidoDistancia != null) {

                            System.out.println("El pedido se encuentra a "
                                    + Math.round(calcularDistancia.calcularDistancia(pedidoDistancia.getSeguimiento().getLatitud()
                                    , pedidoDistancia.getSeguimiento().getLongitud()
                                    , pedidoDistancia.getDepositoDestino().getPosicion().getLatitud()
                                    , pedidoDistancia.getDepositoDestino().getPosicion().getLongitud())) + " kms de la sucursal destino");
                        } else {
                            System.out.println("----------------------------");
                            System.out.println("El pedido no se encontró, intentelo nuevamente");
                            System.out.println("----------------------------");
                        }
                        break;
                    case 10:
                        List<Pedido> pedidos = pedidoController.mostrarTodosLosPedidos();
                        for (Pedido pedido : pedidos) {
                            System.out.println(pedido.toString()); // O utiliza el método que imprime la información relevante del pedido
                        }
                        break;
                    case 11:
                        System.out.println("Ha salido exitosamente");
                        this.regresarMenuPrincipal = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese una opción válida del menú.");
                        break;
                }
            }while (!regresarMenuPrincipal);//**

    }
    public void setRegresarMenuPrincipal(boolean regresarMenuPrincipal) {
        this.regresarMenuPrincipal = regresarMenuPrincipal;
    }


}





