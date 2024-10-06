package com.example.backenddesarrollodeapps2ecommerce.controller;

import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProveedorEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ProveedorController {
    @Autowired
    ProveedoresService provService;

    @PostMapping("/proveedores")
    public ResponseEntity<?> proveedoresPost(@RequestBody ProveedorEntity prov) {
        try {
            provService.save(prov);
            return new ResponseEntity<>("Proveedor registrado correctamente", HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>("Error al registrar", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping("/proveedores")
    public ResponseEntity<?> proveedoresGetAll(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<ProveedorEntity> resultList = provService.getAllProveedores(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("No hay proveedores", HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error interno", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping ("/proveedores/{idProveedor}")
    public ResponseEntity<?> productGetAll(@PathVariable long idProveedor ) {
        try {
            ProveedorEntity resultado = this.provService.getPorID(idProveedor);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("No se encontró el proveedor id: "+idProveedor, HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error interno", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PutMapping ("/proveedores/{idProveedor}")
    public ResponseEntity<?> productUpdate(@RequestBody ProveedorEntity modificado, @PathVariable long idProveedor) {
        try {
            provService.update(modificado,idProveedor);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("No se encontró el proveedor", HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error interno", HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @DeleteMapping ("/proveedores/{idProveedor}")
    public ResponseEntity<?> productUpdate(@PathVariable long idProveedor) {
        try {
            provService.deleteProveedor(idProveedor);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("No se encotntró al proveedor ID: "+idProveedor, HttpStatus.NOT_FOUND);
        }
        catch (Throwable e) {
            return new ResponseEntity<>("Error interno", HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
