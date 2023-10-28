package org.example.view;

import org.example.controller.DepositoController;
import org.example.model.Cliente;
import org.example.model.Deposito;
import org.example.model.Posicion;

import java.util.Scanner;

public class MenuDeposito {

    private DepositoController depositoController;
    private String opcion;
    private boolean regresarMenuPrincipal;//**

    public MenuDeposito(DepositoController depositoController) {
        this.depositoController = depositoController;
    }

    public void mostrarMenuDeposito() {
        Scanner scanner = new Scanner(System.in);
        do {//**
        System.out.println("Por favor ingrese la opción que desee: ");
        System.out.println("1. Crear depósito");
        System.out.println("2. Ver depósitos");
        System.out.println("3. Buscar depósito por código");
        System.out.println("4. Editar depósito");
        System.out.println("5. Eliminar depósito");
        System.out.println("6. Salir del Menú de depósito");
        System.out.println("----------------------------");
        this.opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Por favor ingrese el código del depósito");
                    String codigo = scanner.nextLine();
                    if (depositoController.findOne(codigo) == null) {
                        System.out.println("Por favor ingrese el nombre del depósito");
                        String nombre = scanner.nextLine();
                        System.out.println("Por favor ingrese la dirección del depósito");
                        String direccion = scanner.nextLine();
                        System.out.println("Por favor ingrese el teléfono del depósito");
                        String telefono = scanner.nextLine();
                        System.out.println("Por favor ingrese el email del depósito");
                        String email = scanner.nextLine();
                        System.out.println("Por favor ingrese el continente del depósito");
                        String continente = scanner.nextLine();
                        System.out.println("Por favor ingrese la latitud del depósito");
                        Double lat = scanner.nextDouble();
                        System.out.println("Por favor ingrese la longitud del depósito");
                        Double lon = scanner.nextDouble();

                        depositoController.create(new Deposito(codigo, nombre, direccion, telefono, email, continente, new Posicion(lat, lon)));
                    } else {
                        System.out.println("El depósito ingresado ya existe.");
                    }
                    break;
                case "2":
                    for (Deposito dp: depositoController.findAll()) {
                        System.out.println(dp.toString());
                    }
                    break;
                case "3":
                    System.out.println("Por favor ingrese el código del depósito");
                    String codigoDeposito = scanner.nextLine();
                    if (depositoController.findOne(codigoDeposito) != null) {
                        System.out.println(depositoController.findOne(codigoDeposito).toString());
                    } else {
                        System.out.println("No existe un depósito con ese código");
                    }
                    break;
                case "4":
                    System.out.println("Por favor ingrese el código del depósito a editar");
                    String codigoDepositoEditar = scanner.nextLine();
                    if (depositoController.findOne(codigoDepositoEditar) != null) {
                        System.out.println("Por favor ingrese el nuevo nombre del depósito");
                        String nuevoNombre = scanner.nextLine();
                        System.out.println("Por favor ingrese la nueva dirección del depósito");
                        String nuevaDireccion = scanner.nextLine();
                        System.out.println("Por favor ingrese el nuevo teléfono del depósito");
                        String nuevoTelefono = scanner.nextLine();
                        System.out.println("Por favor ingrese el nuevo email del depósito");
                        String nuevoEmail = scanner.nextLine();
                        System.out.println("Por favor ingrese el nuevo continente del depósito");
                        String nuevoContinente = scanner.nextLine();
                        System.out.println("Por favor ingrese la nueva latitud del depósito");
                        Double nlat = scanner.nextDouble();
                        System.out.println("Por favor ingrese la nueva longitud del depósito");
                        Double nlon = scanner.nextDouble();
                        System.out.println("Deposito editado correctamente");
                        depositoController.upDate(new Deposito(codigoDepositoEditar, nuevoNombre, nuevaDireccion, nuevoTelefono, nuevoEmail, nuevoContinente, new Posicion(nlat, nlon)));
                    } else {
                        System.out.println("El código del depósito ingresado es inexistente");
                    }
                    break;
                case "5":
                    System.out.println("Por favor ingrese el código del depósito a eliminar");
                    String codigoDepositoEliminar = scanner.nextLine();
                    Deposito depositoEliminar = depositoController.findOne(codigoDepositoEliminar);
                    if (depositoEliminar!=null){
                        depositoController.delete(depositoEliminar.getCodigo());
                        System.out.println("Deposito eliminado correctamente");
                    } else {
                        System.out.println("No se encontró ningun deposito con el codigo ingresado");
                    }


                    break;
                case "6":
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
