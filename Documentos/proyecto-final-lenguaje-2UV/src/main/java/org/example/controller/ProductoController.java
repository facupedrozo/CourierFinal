package org.example.controller;

import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.service.ProductoService;

import java.util.List;

public class ProductoController implements CRUD<Producto>{
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }



    public void create(Producto producto){
        productoService.save(producto);
    }

    public Producto findOne(String codigo){
        return productoService.findOne(codigo);
    }

    public List<Producto> findAll(){
        return productoService.findAll();
    }

    public void delete(String codigo){
        productoService.delete(codigo);
    }

    public void upDate(Producto producto){
        productoService.upDate(producto);
    }


}
