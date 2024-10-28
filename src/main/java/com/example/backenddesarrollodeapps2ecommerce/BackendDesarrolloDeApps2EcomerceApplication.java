package com.example.backenddesarrollodeapps2ecommerce;

import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.LogDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.TallesEnum;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.VentaEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.VentasService;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BackendDesarrolloDeApps2EcomerceApplication {

    public static <Usuario> void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(BackendDesarrolloDeApps2EcomerceApplication.class, args);
        VentasService ventasService = applicationContext.getBean(VentasService.class);
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
                    Body body = Utilities.convertDelivery(delivery);
                    String payload = body.getPayload();

                    //Llega un mensaje desde el modulo usuarios
                    System.out.println(body.getOrigin() + "y el string:" + Modules.USUARIO.toString() );
                    if(body.getOrigin().contentEquals(Modules.USUARIO.toString().toLowerCase())){
                        System.out.println("LLEGO UN MENSAJE DE USUARIOS");
                        if(body.getUseCase().contentEquals("Venta")){
                            System.out.println("LLEGO UNA VENTA");
                            try{
                                VentaEntity venta = Utilities.convertBody(body, VentaEntity.class);
                                ventasService.save(venta);
                            }catch (Exception e){
                                e.printStackTrace();
                                System.out.println("No se pudo convertir");
                            }

                        }
                    }
                    System.out.println(body.getPayload());
                    LogDAO logDAO = applicationContext.getBean(LogDAO.class);
                    LogEntity log = new LogEntity();
                    log.setMensaje("Llegó un mensaje desde:" + body.getOrigin());
                    logDAO.save(log);

                } catch (ConverterException e) {
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        });

        //Comienza a consumir utilizando un hilo secundario
        consumer.consume(consumerConnection, Modules.E_COMMERCE);

        Connection publisherConnection = broker.startConnection();

        //Crea la instancia para poder publicar un mensaje
        Publisher publisher = new Publisher(Modules.E_COMMERCE);

        //El token es el JWT que entrega Gestion_Interna
        //Types presenta 3 variables, String, JSON o Array, utilizado para un mejor manejo del mensaje.
        VentaEntity venta = new VentaEntity();
        venta.setFecha(new Date());
        venta.setProductos(List.of(1,2,3));
        venta.setCantidadDeProductos(3);
        venta.setMontoTotal(1000);
        venta.setNombreUsuario("carlitos");
        publisher.publish(publisherConnection,Utilities.convertClass(venta), Modules.E_COMMERCE, "Venta", "ss", Types.JSON);

        broker.endConnection(publisherConnection);


    }

}
