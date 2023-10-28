package org.example.view;

import org.example.controller.PedidoController;
import org.example.controller.TransportistaController;
import org.example.model.*;

import java.util.Scanner;

public class MenuTransportista {

    private TransportistaController transportistaController;
    private PedidoController pedidoController;
    private boolean regresarMenuPrincipal;//**


    public MenuTransportista(TransportistaController transportistaController, PedidoController pedidoController) {
        this.transportistaController = transportistaController;
        this.pedidoController = pedidoController;
        this.regresarMenuPrincipal = true;//**

    }

    public void mostrarMenuTransportista() {
        Scanner scanner = new Scanner(System.in);
        do {//**

            System.out.println(" ");
            System.out.println("Por favor ingrese la opción que desee: ");
            System.out.println(" ");
            System.out.println("1. Crear transportista");
            System.out.println("2. Ver transportistas");
            System.out.println("3. Buscar transportista por CUIT");
            System.out.println("4. Editar transportista");
            System.out.println("5. Eliminar transportista");
            System.out.println("6. Informar posición del pedido");
            System.out.println("7. Salir del Menu de transportista");
            System.out.println("----------------------------");

            String opcion = scanner.nextLine();


            switch (opcion) {
                case "1":
                    System.out.println("Por favor ingrese el CUIT");
                    String cuit = scanner.nextLine();
                    if (transportistaController.findOne(cuit) == null) {
                        System.out.println("Por favor ingrese el nombre");
                        String nombre = scanner.nextLine();
                        System.out.println("Por favor ingrese el teléfono");
                        String telefono = scanner.nextLine();
                        System.out.println("Por favor ingrese el email");
                        String email = scanner.nextLine();
                        System.out.println("Por favor ingrese el tipo de transportista");
                        System.out.println("1.Aereo");
                        System.out.println("2.Terrestre");
                        System.out.println("3.Maritimo");
                        TipoTransportista tipoTransportista = new TipoTransportista();
                        String opcionTipo = scanner.nextLine();
                        switch (opcionTipo) {
                            case "1":
                                tipoTransportista.setDescripcion("Aereo");
                                break;
                            case "2":
                                tipoTransportista.setDescripcion("Terrestre");
                                break;
                            case "3":
                                tipoTransportista.setDescripcion("Maritimo");
                                break;
                            default:
                                System.out.println("No existe ese tipo de transportista");
                        }
                        transportistaController.create(new Transportista(cuit, nombre, telefono, email, tipoTransportista));
                        System.out.println(" Transportista creado exitosamente ");
                    } else {
                        System.out.println("El transportista ya existe");
                    }

                    break;
                case "2":
                    System.out.println("============== La lista actual de Transportistas es: ===============");
                    for (Transportista tr : transportistaController.findAll()) {
                        System.out.println(tr.toString());
                    }
                    break;
                case "3":
                    for (Transportista tr : transportistaController.findAll()
                    ) {
                        System.out.println(tr.toString());
                    }
                    System.out.println("Por favor ingrese el CUIT del transportista");
                    String cuitTransportista = scanner.nextLine();

                    Transportista tr = transportistaController.findOne(cuitTransportista);
                    if (tr != null) {
                        System.out.println("El transportista buscado es: " + tr.toString());
                    } else {
                        System.out.println("El cuit ingresado no corresponde a ningún transportista");
                    }

                    break;
                case "4":
                    System.out.println("Por favor ingrese el CUIT del transportista");
                    String cuitTransportistaEditar = scanner.nextLine();
                    if (transportistaController.findOne(cuitTransportistaEditar) != null) {
                        System.out.println("Por favor ingrese el nuevo nombre");
                        String nuevoNombre = scanner.nextLine();
                        System.out.println("Por favor ingrese el nuevo teléfono");
                        String nuevoTelefono = scanner.nextLine();
                        System.out.println("Por favor ingrese el nuevo email");
                        String nuevoEmail = scanner.nextLine();
                        System.out.println("Por favor ingrese el tipo de transportista");
                        System.out.println("1.Aereo");
                        System.out.println("2.Terrestre");
                        System.out.println("3.Maritimo");
                        String nuevoTipo = scanner.nextLine();
                        switch (nuevoTipo) {
                            case "1":
                                transportistaController.findOne(cuitTransportistaEditar).getTipo().setDescripcion("Aereo");
                                break;
                            case "2":
                                transportistaController.findOne(cuitTransportistaEditar).getTipo().setDescripcion("Terrestre");
                                break;
                            case "3":
                                transportistaController.findOne(cuitTransportistaEditar).getTipo().setDescripcion("Maritimo");
                                break;
                            default:
                                System.out.println("No existe ese tipo de transportista");

                        }
                        System.out.println("Transportista editado correctamente");
                        transportistaController.upDate(new Transportista(cuitTransportistaEditar, nuevoNombre, nuevoTelefono, nuevoEmail, transportistaController.findOne(cuitTransportistaEditar).getTipo()));
                    } else {
                        System.out.println("No se encontró un transportista con el CUIT ingresado");
                    }

                    break;
                case "5":
                    System.out.println("Por favor ingrese el cuit del transportista a eliminar: ");
                    String cuitTransportistaEliminado = scanner.nextLine();
                    if (transportistaController.findOne(cuitTransportistaEliminado) != null) {
                        transportistaController.delete(cuitTransportistaEliminado);
                        System.out.println(" Transportista eliminado exitosamente");
                    } else {
                        System.out.println("El transportista ingresado no existe");
                    }
                    break;
                case "6":
                System.out.println("Por favor ingrese el numero del pedido para informar su posición");
                String nroPedido = scanner.nextLine();
                Pedido pedidoEncontrado = pedidoController.buscarPedidoPorNumero(nroPedido);

                if (pedidoEncontrado != null) {
                    System.out.println("Por favor ingrese su cuit para determinar si el pedido le corresponde");
                    String cuitTransportistaPedido = scanner.nextLine();

                    if (pedidoEncontrado.getTransportista() != null && pedidoEncontrado.getTransportista().getCuit().equals(cuitTransportistaPedido)) {
                        System.out.println("Por favor ingrese la nueva latitud: ");
                        double latitud = scanner.nextDouble();
                        System.out.println("Por favor ingrese la nueva longitud: ");
                        double longitud = scanner.nextDouble();
                        pedidoEncontrado.getSeguimiento().setLatitud(latitud);
                        pedidoEncontrado.getSeguimiento().setLongitud(longitud);
                        System.out.println(" la latitud del pedido es "+pedidoEncontrado.getSeguimiento().getLatitud());
                        System.out.println(" ");
                        System.out.println(" la longitud del pedido es "+pedidoEncontrado.getSeguimiento().getLongitud());
                    } else {
                        System.out.println("El pedido no le corresponde o el transportista no está definido.");
                    }
                } else {
                    System.out.println("No se encontró ningún pedido con el número ingresado.");
                }
                break;
                case "7":
                    System.out.println("Ha salido exitosamente");
                    this.regresarMenuPrincipal = true;
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }while (!regresarMenuPrincipal);//**


    }public void setRegresarMenuPrincipal(boolean regresarMenuPrincipal) {
        this.regresarMenuPrincipal = regresarMenuPrincipal;
    }
}
