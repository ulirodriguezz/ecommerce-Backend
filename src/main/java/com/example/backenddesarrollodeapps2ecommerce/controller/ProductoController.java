package com.example.backenddesarrollodeapps2ecommerce.controller;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ProductoController {
    @Autowired
    ProductoService prodService;

    @PostMapping("/productos")
    public ResponseEntity<?> productPost(@RequestBody ProductoEntity product) {
        try {
            prodService.save(product);
            return new ResponseEntity<>("Producto guardado correctamente", HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/productos")
    public ResponseEntity<?> productGetAll(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<ProductoEntity> resultList = prodService.getAllProducts(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/alertas")
    public ResponseEntity<?> buscarEnAlerta(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<ProductoEntity> resultList = prodService.buscarEnAlerta(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("No hay productos en alerta de stock", HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/productos/{idProducto}")
    public ResponseEntity<?> productGetAll(@PathVariable long idProducto ) {
        try {
            ProductoEntity resultado = this.prodService.getPorID(idProducto);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("No se encontró el producto id: "+idProducto, HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PutMapping ("/productos/{idProd}")
    public ResponseEntity<?> productUpdate(@RequestBody ProductoEntity modificado, @PathVariable long idProd) {
        try {
            prodService.update(modificado,idProd);  
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @DeleteMapping ("/productos/{idProd}")
    public ResponseEntity<?> productUpdate(@PathVariable long idProd) {
        try {
            prodService.deleteProducto(idProd);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("Mnsj Error", HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
