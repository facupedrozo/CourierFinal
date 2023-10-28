package org.example.view;

import org.example.controller.ProveedorController;
import org.example.model.Cliente;
import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;
import org.example.service.ProveedorService;

import java.util.Scanner;

public class MenuProveedor {
    ProveedorController proveedorController;
    private boolean regresarMenuPrincipal;//**

    public MenuProveedor(ProveedorController proveedorController) {
        this.proveedorController = proveedorController;
        this.regresarMenuPrincipal = true;//**

    }

    public void mostrarMenuProveedor() {
        Scanner leer = new Scanner(System.in);
        do {//**


            System.out.println("=============== Menu ====================");
            System.out.println("1.Crear proveedor");
            System.out.println("2.Ver todos los proveedores");
            System.out.println("3.Editar proveedor");
            System.out.println("4.Buscar proveedor por C.U.I.T.");
            System.out.println("5.Eliminar proveedor");
            System.out.println("6.Salir");
            System.out.println("=========================================");
            System.out.println("Ingrese una opcion: ");
            int opcion = leer.nextInt();
            leer.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el C.U.I.T: ");
                    String cuit = leer.nextLine();
                    if (proveedorController.findOne(cuit) == null) {
                        System.out.println("Ingrese el nombre del proveedor: ");
                        String nombre = leer.nextLine();
                        System.out.println("Ingrese la direccion del proveedor: ");
                        String direc = leer.nextLine();
                        System.out.println("Inrese el numero de telefono del proveedor: ");
                        String tel = leer.nextLine();
                        System.out.println("Inrese el numero de email del proveedor: ");
                        String email = leer.nextLine();
                        proveedorController.create(new Proveedor(cuit, nombre, direc, tel, email));
                        System.out.println(" Proveedor creado correctamente");
                    } else {
                        System.out.println("El proveedor ya existe");
                    }
                    break;

                case 2:
                    System.out.println("============== La lista de Proveedores actuales es: ===============");
                    for (Proveedor pr : proveedorController.findAll()) {
                        System.out.println(pr.toString());
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el C.U.I.T del proveedor a editar: ");
                    String cuitPr = leer.nextLine();
                    if (proveedorController.findOne(cuitPr) != null) {
                        System.out.println("Ingrese el nombre del proveedor: ");
                        String nombr = leer.nextLine();
                        System.out.println("Ingrese la direccion del proveedor: ");
                        String dire = leer.nextLine();
                        System.out.println("Inrese el numero de telefono del proveedor: ");
                        String telP = leer.nextLine();
                        System.out.println("Inrese el numero de email del proveedor: ");
                        String emailP = leer.nextLine();
                        proveedorController.upDate(new Proveedor(cuitPr, nombr, dire, telP, emailP));
                        System.out.println("Proveedor editado correctamente");
                    } else {
                        System.out.println("El proveedor ingresado no existe");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el C.U.I.T. del proveedor a Buscar: ");
                    String cuitP = leer.nextLine();
                    Proveedor proveedor = proveedorController.findOne(cuitP);
                    if (proveedor != null) {
                        System.out.println("El proveedor buscado es: " + proveedor.toString());
                    } else {
                        System.out.println("El cuit ingresado no corresponde a ningún proveedor");
                    }

                    break;

                case 5:
                    System.out.println("Ingrese el C.U.I.T. del proveedor a eliminar: ");
                    String cuitd = leer.nextLine();
                    Proveedor proveedorEliminado = proveedorController.findOne(cuitd);
                    if (proveedorEliminado != null) {
                        proveedorController.delete(cuitd);
                        System.out.println("Proveedor eliminado correctamente");
                    } else {
                        System.out.println("El proveedor ingresado no existe");
                    }
                    break;

                case 6:
                    System.out.println("Ha salido exitosamente");
                    this.regresarMenuPrincipal = true;
                    break;

                default:
                    System.out.println("La opcion ingresada no existe, intente nuevamente..");
            }
        } while (!regresarMenuPrincipal);//**
    }public void setRegresarMenuPrincipal(boolean regresarMenuPrincipal) {
        this.regresarMenuPrincipal = regresarMenuPrincipal;

    }
}