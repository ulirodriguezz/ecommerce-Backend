package com.example.backenddesarrollodeapps2ecommerce;

import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.LogDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.TallesEnum;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BackendDesarrolloDeApps2EcomerceApplication {

    public static <Usuario> void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(BackendDesarrolloDeApps2EcomerceApplication.class, args);

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

                    //Los datos enviados desde el módulo de origen se encuentran en el atributo payload del body.
                    String datos = body.getPayload();
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


    }

}
