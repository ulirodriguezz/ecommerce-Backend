package com.example.backenddesarrollodeapps2ecommerce.controller;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.ProductoService;
import com.example.backenddesarrollodeapps2ecommerce.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("")
public class ProductoController {


    @Autowired
    ProductoService prodService;
/*    @Autowired
    S3Service s3Service;*/
/*    @PostMapping("/productos/{idProducto}/imagen")
    public ResponseEntity<?> productPost(@RequestBody MultipartFile img, @PathVariable Long idProducto) {
        try {
            System.out.println("---IMAGEN....");
            System.out.println(img.getOriginalFilename());
            s3Service.saveFile(img,idProducto);
            return new ResponseEntity<>(new Mensaje("Producto registrado correctamente"), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }*/
    @PostMapping("/productos")
    public ResponseEntity<?> productPost(@RequestBody ProductoEntity product) {
        try {
            prodService.save(product);
            return new ResponseEntity<>(new Mensaje("Producto registrado correctamente"), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/productos")
    public ResponseEntity<?> productGetAll(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<ProductoEntity> resultList = prodService.getAllProducts(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No se encontraron productos"), HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/alertas")
    public ResponseEntity<?> buscarEnAlerta(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<ProductoEntity> resultList = prodService.buscarEnAlerta(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No hay productos en alerta de stock"), HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/productos/{idProducto}")
    public ResponseEntity<?> productGetAll(@PathVariable long idProducto ) {
        try {
            ProductoEntity resultado = this.prodService.getPorID(idProducto);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No se encontró el producto id: "+idProducto), HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PutMapping ("/productos/{idProd}")
    public ResponseEntity<?> productUpdate(@RequestBody ProductoEntity modificado, @PathVariable long idProd) {
        try {
            prodService.update(modificado,idProd);  
            return new ResponseEntity<>(new Mensaje("Producto modificado con exito"), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @DeleteMapping ("/productos/{idProd}")
    public ResponseEntity<?> productUpdate(@PathVariable long idProd) {
        try {
            prodService.deleteProducto(idProd);
            return new ResponseEntity<>(new Mensaje("Producto eliminado"), HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No se encontró el producto"), HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
