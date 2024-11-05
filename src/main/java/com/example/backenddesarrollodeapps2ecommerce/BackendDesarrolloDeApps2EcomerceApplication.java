package com.example.backenddesarrollodeapps2ecommerce;

import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.ProductoDTO;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.Utilidades;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.VentaDTO;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.LogDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.ProductoDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.TallesEnum;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.ProductoService;
import com.example.backenddesarrollodeapps2ecommerce.service.VentasService;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BackendDesarrolloDeApps2EcomerceApplication {

    public static <Usuario> void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(BackendDesarrolloDeApps2EcomerceApplication.class, args);
        final VentasService ventasService = applicationContext.getBean(VentasService.class);
        Broker broker = new Broker(
                "3.142.225.39",
               5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );

        Connection consumerConnection = broker.startConnection();

        //Redefino el callback para los mensajes recibidos.
        Consumer consumer = new Consumer(new CallbackInterface() {
            @Override
            public void callback(String consumerTag, Delivery delivery) {
                try {
                    System.out.println("Callback...");
                    Body body = Utilities.convertDelivery(delivery);
                    String payload = body.getPayload();
                    if(body.getUseCase().contentEquals("Prueba")){
                        System.out.println(payload);
                    }


                    if(body.getOrigin().contentEquals(Modules.USUARIO.toString().toLowerCase())){
                        //Llega un mensaje desde el modulo usuarios

                        if(body.getUseCase().contentEquals("Venta")){
                            //El mensaje es una venta para registrar
                            try{
                                VentaEntity venta = Utilities.convertBody(body, VentaEntity.class);
                                ventasService.save(venta);
                                System.out.println(venta);
                            }catch (Exception e){
                                e.printStackTrace();
                                System.out.println("No se pudo convertir");
                            }
                        }
                        if(body.getUseCase().contentEquals("Productos")){
                            //Se está solicitando el listado de productos
                            System.out.println("ME PIDIERON LA LISTA");
                            List<String> mensajes = new ArrayList<>();
                            ProductoService productoService = applicationContext.getBean(ProductoService.class);
                            List<ProductoEntity> productos = productoService.getAllProducts(1);
                            for (ProductoEntity p : productos){
                                ProductoDTO dto = new ProductoDTO(p);
                                String m = Utilities.convertClass(dto);
                                mensajes.add(m);
                            }
                            String mensajeCompleto = String.join("--!--##-->>DELIMITER<<--##--!--", mensajes);
                            try{
                                Utilidades.enviarMensaje(mensajeCompleto,Modules.USUARIO,"Productos","Producto");
                                System.out.println(mensajeCompleto);
                                System.out.println("SE MANDO EL MENSAJE");
                            }catch (Exception e){
                                System.out.println("Ocurrio un error");
                                Utilidades.crearLog(applicationContext,"Ocurrió un error al enviar el lsitado de productos");
                            }
                        }
                        if(body.getUseCase().contentEquals("Pedidos")){
                            System.out.println("ME PIDIERON LA LISTA");
                            System.out.println("Username: "+ payload);
                            List<String> mensajes = new ArrayList<>();
                            List<VentaEntity> pedidos = new ArrayList<>();
                            String username = "";
                            try {
                               pedidos = ventasService.getVentasByUsername(payload);
                            }catch (EmptyResultDataAccessException e){
                                Utilidades.enviarMensaje("No se encontraron pedidos",Modules.USUARIO,"Pedidos","Error");
                            }
                            catch (Exception e){
                                Utilidades.enviarMensaje("Otro error",Modules.USUARIO,"Pedidos","Error");
                            }
                            if(pedidos.size() == 1){
                                Utilidades.enviarMensaje(Utilities.convertClass(new VentaDTO(pedidos.get(0))),Modules.USUARIO,"Pedidos",username);
                            }
                            else {
                                for(VentaEntity p: pedidos){
                                    VentaDTO dto = new VentaDTO(p);
                                    username = dto.getNombreUsuario();
                                    String m = Utilities.convertClass(dto);
                                    mensajes.add(m);
                                }
                                String mensajeCompleto = String.join("--!--##-->>DELIMITER<<--##--!--", mensajes);
                                try{
                                    Utilidades.enviarArray(mensajeCompleto,Modules.USUARIO,"Pedidos",username);
                                    System.out.println("SE MANDO EL MENSAJE");
                                }catch (Exception e){
                                    System.out.println("Ocurrio un error");
                                    Utilidades.crearLog(applicationContext,"Ocurrió un error al enviar el lsitado de pedidos");
                                }
                            }


                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        });

        //Comienza a consumir utilizando un hilo secundario
        consumer.consume(consumerConnection, Modules.E_COMMERCE);
        Utilidades.enviarMensaje("Hola",Modules.E_COMMERCE, "Prueba","");

    }



}
