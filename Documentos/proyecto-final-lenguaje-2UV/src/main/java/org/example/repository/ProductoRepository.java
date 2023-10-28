package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.ProveedorController;
import org.example.model.CategoriaProducto;
import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.service.ProveedorService;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProductoRepository implements CRUD<Producto>{
    private List<Producto> productos;
    ProveedorController proveedorController = new ProveedorController(new ProveedorService(new ProveedorRepository()));


    public ProductoRepository() {
        this.productos = new ArrayList<>();
        upLoad();
    }

    @Override
    public void upLoad() {
        CategoriaProducto informatica = new CategoriaProducto("Informatica");
        CategoriaProducto limpieza = new CategoriaProducto("Limpieza");
        CategoriaProducto hogar = new CategoriaProducto("Hogar");
        CategoriaProducto jardin = new CategoriaProducto("Jardin");
        CategoriaProducto electronica = new CategoriaProducto("Electronica");
        CategoriaProducto juguete = new CategoriaProducto("Juguete");

        Producto p1 = new Producto("1", "Monitor", 0.5, 0.6, 2, 3, informatica, proveedorController.findOne("0001"));
        Producto p2 = new Producto("2", "Mouse", 0.01, 0.005, 2, 0.198, informatica, proveedorController.findOne("0002"));
        Producto p3 = new Producto("3", "Pava eléctrica", 0.5, 0.04, 2, 2, electronica, proveedorController.findOne("0003"));
        Producto p4 = new Producto("4", "Peluche oso", 0.9, 1, 2, 0.6, juguete, proveedorController.findOne("0004"));
        Producto p5 = new Producto("5", "Escoba", 0.03, 2, 2, 0.5, hogar, proveedorController.findOne("0005"));
        Producto p6 = new Producto("6", "Silla", 1, 1.4, 2, 6.4, hogar, proveedorController.findOne("0001"));
        Producto p7 = new Producto("7", "Toalla", 1, 2, 2, 0.1, hogar, proveedorController.findOne("0002"));
        Producto p8 = new Producto("8", "Sábana", 2, 2.4, 2, 0.7, hogar, proveedorController.findOne("0003"));
        Producto p9 = new Producto("9", "Teclado", 0.05, 0.005, 2, 1, informatica, proveedorController.findOne("0004"));
        Producto p10 = new Producto("10", "Estante", 2, 1.1, 2, 10, hogar, proveedorController.findOne("0005"));
        Producto p11 = new Producto("11", "Detergente", 0.4, 0.05, 2, 0.2, limpieza, proveedorController.findOne("0001"));
        Producto p12 = new Producto("12", "Peluche Barney", 0.7, 1.7, 2, 0.4, juguete, proveedorController.findOne("0002"));
        Producto p13 = new Producto("13", "Mousepad", 0.4, 2, 2, 0.01, informatica, proveedorController.findOne("0003"));
        Producto p14 = new Producto("14", "Bombilla", 0.0001, 3, 2, 0.01, hogar, proveedorController.findOne("0004"));
        Producto p15 = new Producto("15", "Maceta", 0.5, 3, 2, 1.3, jardin, proveedorController.findOne("0005"));
        Producto p16 = new Producto("16", "Gnomo de jardín", 1, 3, 2, 3, jardin, proveedorController.findOne("0001"));
        Producto p17 = new Producto("17", "Licuadora", 0.3, 3, 2, 2.5, electronica, proveedorController.findOne("0002"));
        Producto p18 = new Producto("18", "Cafetera", 0.2, 3, 2, 2, electronica, proveedorController.findOne("0003"));
        Producto p19 = new Producto("19", "Muñeco Goku", 1.2, 3, 2, 1, juguete, proveedorController.findOne("0004"));
        Producto p20 = new Producto("20", "Muñeco Pikachu", 0.09, 3, 2, 0.6, juguete, proveedorController.findOne("0005"));


        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);
        productos.add(p7);
        productos.add(p8);
        productos.add(p9);
        productos.add(p10);
        productos.add(p11);
        productos.add(p12);
        productos.add(p13);
        productos.add(p14);
        productos.add(p15);
        productos.add(p16);
        productos.add(p17);
        productos.add(p18);
        productos.add(p19);
        productos.add(p20);

    }

    @Override
    public void save(Producto producto) {
        productos.add(producto);
    }

    @Override
    public void upDate(Producto producto) {
        if (findOne(producto.getCodigo()) != null){
            findOne(producto.getCodigo()).setNombre(producto.getNombre());
            findOne(producto.getCodigo()).setAltura(producto.getAltura());
            findOne(producto.getCodigo()).setAncho(producto.getAncho());
            findOne(producto.getCodigo()).setPeso(producto.getPeso());
            findOne(producto.getCodigo()).setLargo(producto.getLargo());
            findOne(producto.getCodigo()).setProveedor(producto.getProveedor());//**
        }
    }

    @Override
    public List<Producto> findAll() {
        List<Producto>productosHabilitados = new ArrayList<>();
        for (Producto pr : productos){
            if(pr.getEstado().equals(Producto.Estado.HABILITADO)){
                productosHabilitados.add(pr);
            }
        }
        return productosHabilitados;
    }



    @Override
    public Producto findOne(String id) {

        Producto producto = null;
        for (Producto pr : productos){
            if (pr.getCodigo().equals(id) || pr.getNombre().equals(id)){
                producto = pr;
            }
        }
        return producto;
    }

    @Override
    public void delete(String id) {
        for (Producto pr : productos){
            if (findOne(id) != null){
                pr.setEstado(Producto.Estado.DESHABILITADO);
            }
        }
    }

}
