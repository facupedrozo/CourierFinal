package org.example.view;

import org.example.controller.ProductoController;
import org.example.controller.ProveedorController;
import org.example.model.CategoriaProducto;
import org.example.model.Cliente;
import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;
import org.example.service.ProveedorService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuProducto {
    private String opcion;
    private ProductoController productoController;
    ProveedorController proveedorController;

    private boolean regresarMenuPrincipal;//**


    public MenuProducto(ProductoController productoController) {
        this.productoController = productoController;
        this.proveedorController = new ProveedorController(new ProveedorService(new ProveedorRepository()));
        this.regresarMenuPrincipal = true;//**

    }


    public void mostrarMenuProducto() {
        Scanner scanner = new Scanner(System.in);
        do {//**

            System.out.println("----------------------------");
            System.out.println("Por favor ingrese la opción que desee: ");
            System.out.println("----------------------------");
            System.out.println("1. Crear Producto");
            System.out.println("2. Ver Productos");
            System.out.println("3. Buscar producto por código");
            System.out.println("4. Editar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir del Menu de producto");
            System.out.println("----------------------------");
            this.opcion = scanner.nextLine();


            switch (opcion) {
                case "1":
                    String nombre = null;
                    Double peso = 0.0;
                    Double alturaProducto = 0.0;
                    Double anchoProducto = 0.0;
                    Double largoProducto = 0.0;

                    System.out.println("Por favor ingrese el codigo");
                    String codigo = scanner.nextLine();
                    if (productoController.findOne(codigo) == null) {
                        System.out.println("Por favor ingrese el nombre");
                        nombre = scanner.nextLine();
                        try {
                            System.out.println("Por favor ingrese el peso");
                            peso = scanner.nextDouble();
                            System.out.println("Por favor ingrese la altura");
                            alturaProducto = scanner.nextDouble();
                            System.out.println("Por favor ingrese el ancho del producto");
                            anchoProducto = scanner.nextDouble();
                            System.out.println("Por favor ingrese el largo del producto");
                            largoProducto = scanner.nextDouble();
                        } catch (Exception e) {
                            System.out.println("Se produjo un error con los datos introducidos");
                            break;
                        }

                    } else {
                        System.out.println("----------------------------");
                        System.out.println("El producto ingresado ya existe");
                        System.out.println("----------------------------");
                        break;
                    }
                    scanner.nextLine();
                    System.out.println("Por favor ingrese la Categoria del producto");
                    System.out.println("-----------------------------");
                    System.out.println("1.Informatica");
                    System.out.println("2.Hogar");
                    System.out.println("3.Limpieza");
                    System.out.println("4.Jardin");
                    System.out.println("5.Electronica");
                    System.out.println("6.Juguete");
                    System.out.println("----------------------------");


                    String tipoProducto = scanner.nextLine();
                    proveedorController.findAll();
                    System.out.println("----------------------------");
                    System.out.println("Por favor ingrese el cuit del proveedor");
                    String nombreProveedor = scanner.nextLine();

                    Proveedor proveedorDelProducto = proveedorController.findOne(nombreProveedor);
                    CategoriaProducto categoriaProducto = new CategoriaProducto();

                    switch (tipoProducto) {
                        case "1":
                            categoriaProducto.setNombre("Informatica");
                            break;
                        case "2":
                            categoriaProducto.setNombre("Hogar");
                            break;
                        case "3":
                            categoriaProducto.setNombre("Limpieza");
                            break;
                        case "4":
                            categoriaProducto.setNombre("Jardin");
                            break;
                        case "5":
                            categoriaProducto.setNombre("Electronica");
                            break;
                        case "6":
                            categoriaProducto.setNombre("Juguete");
                            break;
                        default:
                            System.out.println("No existe esa categoria de producto");
                            break;
                    }
                    if (proveedorDelProducto != null && categoriaProducto != null) {
                        productoController.create(new Producto(codigo, nombre, anchoProducto, alturaProducto, largoProducto, peso, categoriaProducto, proveedorDelProducto));
                        System.out.println("Producto creado exitosamente");
                    } else {
                        System.out.println("La creación del producto falló");
                    }


                    break;
                case "2":
                    System.out.println("============== La lista de Productos actuales es: ===============");
                    for (Producto pr : productoController.findAll()) {
                        System.out.println(pr.toString());
                    }
                    break;

                case "3":
                    System.out.println("Por favor ingrese el codigo del producto");
                    String codigoProducto = scanner.nextLine();
                    if (productoController.findOne(codigoProducto) != null)
                        System.out.println("El producto es: " + productoController.findOne(codigoProducto));
                    else System.out.println("El codigo ingresado no coincide con ningún producto");
                    break;
                case "4":
                    System.out.println("Por favor ingrese el coodigo del producto a editar: ");
                    String codigoProductoEditado = scanner.nextLine();
                    if (productoController.findOne(codigoProductoEditado) != null) {
                        System.out.println("Por favor ingrese el nuevo nombre");
                        String nuevoNombre = scanner.nextLine();
                        Double nuevoPeso = 0.0;
                        Double nuevaAltura = 0.0;
                        Double nuevoAncho = 0.0;
                        Double nuevoLargo = 0.0;
                        try {
                            System.out.println("Por favor ingrese el nuevo peso");
                            nuevoPeso = scanner.nextDouble();
                            System.out.println("Por favor ingrese la nueva altura");
                            nuevaAltura = scanner.nextDouble();
                            System.out.println("Por favor ingrese el nuevo ancho del producto");
                            nuevoAncho = scanner.nextDouble();
                            System.out.println("Por favor ingrese el nuevo largo del producto");
                            nuevoLargo = scanner.nextDouble();
                        } catch (Exception e) {
                            System.out.println("Se produjo un error con los datos introducidos");
                            break;
                        }

                        scanner.nextLine();
                        System.out.println("Ingrese la nueva categoria del producto: ");
                        System.out.println("-----------------------------");
                        System.out.println("1.Informatica");
                        System.out.println("2.Hogar");
                        System.out.println("3.Limpieza");
                        System.out.println("4.Jardin");
                        System.out.println("5.Electronica");
                        System.out.println("6.Juguete");

                        String opcionProducto = scanner.nextLine();
                        CategoriaProducto nuevaCategoria = new CategoriaProducto();
                        switch (opcionProducto) {
                            case "1":
                                nuevaCategoria.setNombre("Informatica");
                                break;
                            case "2":
                                nuevaCategoria.setNombre("Hogar");
                                break;
                            case "3":
                                nuevaCategoria.setNombre("Limpieza");
                                break;
                            case "4":
                                nuevaCategoria.setNombre("Jardin");
                                break;
                            case "5":
                                nuevaCategoria.setNombre("Electronica");
                                break;
                            case "6":
                                nuevaCategoria.setNombre("Juguete");
                                break;
                            default:
                                System.out.println("No existe esa categoria de producto");
                                break;
                        }
                        proveedorController.findAll();
                        for (Proveedor pr : proveedorController.findAll()) {
                            System.out.println(pr.toString());
                        }
                        System.out.println("Por favor ingrese el cuit del nuevo proveedor");
                        String cuitNuevoProveedor = scanner.nextLine();
                        Proveedor proveedorNuevo = proveedorController.findOne(cuitNuevoProveedor);

                        productoController.upDate(new Producto(codigoProductoEditado, nuevoNombre, nuevoAncho, nuevaAltura, nuevoLargo, nuevoPeso, nuevaCategoria, proveedorNuevo));
                        System.out.println("Producto editado correctamente");

                    } else {
                        System.out.println("El codigo ingresado es incorrecto");
                    }
                    break;
                case "5":
                    System.out.println("Por favor ingrese el codigo del producto a eliminar");
                    String codigoProductoEliminado = scanner.nextLine();
                    if (productoController.findOne(codigoProductoEliminado) != null) {
                        productoController.delete(codigoProductoEliminado);
                    } else {
                        System.out.println("El codigo ingresado es incorrecto");
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
