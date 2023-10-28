package org.example.view;


import org.example.controller.ClienteController;

import org.example.controller.InformeController;
import org.example.controller.PedidoController;
import org.example.model.Cliente;

import org.example.model.Pedido;

import java.util.Scanner;

public class MenuInforme {
    private InformeController informeController;
    private ClienteController clienteController;

    private PedidoController pedidoController;

    private boolean regresarMenuPrincipal;//**

    public MenuInforme(InformeController informeController, ClienteController clienteController, PedidoController pedidoController) {
        this.informeController = informeController;
        this.clienteController = clienteController;
        this.pedidoController = pedidoController;
        this.regresarMenuPrincipal = true;//**

    }

    public void mostrarMenuInforme() {
        Scanner scanner = new Scanner(System.in);
        do {//**

            System.out.println("Por favor ingrese la opción que desee: ");
            System.out.println("1. Mostrar cantidad de pedidos realizados por una sucursal");
            System.out.println("2. Mostrar cantidad de pedidos en estado Pendiente");
            System.out.println("3. Mostrar historial de pedidos finalizados de un cliente");

            System.out.println("4. Mostrar los estados de los pedidos");
            System.out.println("5. Salir del menu Informe");


            System.out.println("----------------------------");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Por favor ingrese el código de la sucursal para obtener la cantidad de pedidos realizados:");

                    String codigoSucursal = scanner.nextLine();

                    int cantidadPedidos = informeController.obtenerCantidadPedidosPorSucursal(codigoSucursal);
                    System.out.println("La cantidad de pedidos realizados por la sucursal con código " + codigoSucursal + " es: " + cantidadPedidos);
                    break;

                case "2":
                    int cantidadPedidosPendientes = informeController.contarPedidosEnEstadoPendiente();
                    System.out.println("La cantidad de pedidos en estado Pendiente es: " + cantidadPedidosPendientes);
                    break;

                case "3":
                    System.out.println("Por favor ingrese el cuit del cliente: ");
                    String cuit = scanner.nextLine();
                    Cliente clienteBuscado = clienteController.findOne(cuit);
                    int contador = informeController.obtenerHistorialDeUnCliente(cuit);
                    if (contador > 0) {
                        System.out.println("El cliente " + clienteBuscado.getNombre() + " " + clienteBuscado.getApellido() + " tiene: " + contador + " pedidos en su historial");
                    } else {
                        System.out.println("El cliente " + clienteBuscado.getNombre() + " " + clienteBuscado.getApellido() + " no tiene pedidos en su historial");
                    }
                    break;
                case "4":
                    System.out.println("Los estados de todos los pedidos son: ");
                    for (Pedido pedido : pedidoController.mostrarTodosLosPedidos()
                    ) {
                        System.out.println("El pedido numero: " + pedido.getNumeroPedido() + " tiene de estado: " + pedido.getEstadoPedido());
                    }
                    break;
                case "5":
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
