package org.example.view;

import org.example.controller.*;
import org.example.repository.*;
import org.example.service.*;

import java.util.Scanner;

public class MenuPrincipal {

    MenuCliente menuCliente;
    MenuTransportista menuTransportista;
    MenuProducto menuProducto;
    MenuPedido menuPedido;
    MenuInforme menuInforme;
    MenuProveedor menuProveedor;
    MenuDeposito menuDeposito;
    private String opcion;

    public MenuPrincipal(MenuCliente menuCliente, MenuTransportista menuTransportista, MenuProducto menuProducto, MenuPedido menuPedido,MenuInforme menuInforme,MenuProveedor menuProveedor, MenuDeposito menuDeposito) {
        this.menuCliente = menuCliente;
        this.menuTransportista = menuTransportista;
        this.menuProducto = menuProducto;
        this.menuPedido = menuPedido;
        this.menuInforme=menuInforme;
        this.menuProveedor=menuProveedor;
        this.menuDeposito=menuDeposito;
    }

    public void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("*** BIENVENIDO AL SISTEMA DE LyDE ***");
            System.out.println("----------------------------");
            System.out.println("Por favor ingrese la opción que desea gestionar: ");
            System.out.println("----------------------------");
            System.out.println("1. Clientes");
            System.out.println("2. Proveedores");
            System.out.println("3. Transportistas");
            System.out.println("4. Productos");
            System.out.println("5. Pedidos");
            System.out.println("6. Depositos");
            System.out.println("7. Informes");
            System.out.println("8. Salir");
            System.out.println("----------------------------");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    menuCliente.mostrarMenuCliente();
                    break;
                case "2":
                    menuProveedor.mostrarMenuProveedor();
                    break;
                case "3":
                    menuTransportista.mostrarMenuTransportista();
                    break;
                case "4":
                    menuProducto.mostrarMenuProducto();
                    break;
                case "5":
                    menuPedido.mostrarMenuPedido();
                    break;
                case "6":
                    menuDeposito.mostrarMenuDeposito();
                case "7":
                    menuInforme.mostrarMenuInforme();
                    break;
                case "8":
                    System.out.println("Ha salido exitosamente");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }
}


