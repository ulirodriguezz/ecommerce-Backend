package com.example.backenddesarrollodeapps2ecommerce.controller;
import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.Utilidades;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.CompraEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.BalanceService;
import com.example.backenddesarrollodeapps2ecommerce.service.ComprasService;
import com.example.backenddesarrollodeapps2ecommerce.service.VentasService;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class AnaliticaController {
    @Autowired
    VentasService ventasService;
    @Autowired
    ComprasService comprasService;
    @Autowired
    BalanceService balanceService;
    @GetMapping("/ventas")
    public ResponseEntity<?> ventasGetAll(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<VentaEntity> resultList = ventasService.getAllVentas(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No se encontraron ventas"), HttpStatus.NOT_FOUND);
        }catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PostMapping("/ventas")
    public ResponseEntity<?> postVentas(@RequestBody VentaEntity venta) {
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );
        try {
            Utilidades.enviarMensaje(broker,Utilities.convertClass(venta),Modules.USUARIO,Modules.E_COMMERCE,"Venta","Venta");
            return new ResponseEntity<>(new Mensaje("venta registrada"), HttpStatus.OK);
        }catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error al registrar"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping("/compras")
    public ResponseEntity<?> comprasGetAll(/*@PathVariable int page*/) {
        try {
            int page = 1;
            List<CompraEntity> resultList = comprasService.getAllCompras(page);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No se encontraron compras"), HttpStatus.NOT_FOUND);
        }catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PostMapping("/compras")
    public ResponseEntity<?> postCompras(@RequestBody CompraEntity compra) {
        try {
            comprasService.save(compra);
            return new ResponseEntity<>(new Mensaje("Compra registrada"), HttpStatus.OK);
        }catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error al registrar"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {

        try {
            BalanceEntity resutlado = balanceService.getBalance();
            Utilidades.enviarMensaje("Mensaje de prueba",Modules.USUARIO,"Prueba","null");
            return new ResponseEntity<>(resutlado, HttpStatus.OK);

        }catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
