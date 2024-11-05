package com.example.backenddesarrollodeapps2ecommerce.core;

import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.LogDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import com.rabbitmq.client.Connection;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Type;

public final class Utilidades {

    public static void enviarMensaje(Broker broker, String mensaje, Modules moduloPublisher, Modules moduloDestino, String usecase, String target) throws Exception {

        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(moduloPublisher);

        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "ss", Types.JSON,target,"600");

        broker.endConnection(publisherConnection);

    }
    public static void enviarMensaje(String mensaje, Modules moduloDestino, String usecase, String target) throws Exception {
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );
        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(Modules.E_COMMERCE);
        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.JSON,target,"600");

        broker.endConnection(publisherConnection);

    }
    public static void enviarArray(String mensaje, Modules moduloDestino, String usecase, String target) throws Exception {
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );
        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(Modules.E_COMMERCE);
        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.ARRAY,target,"600");

        broker.endConnection(publisherConnection);

    }
    public static void enviarMensaje(Broker broker, String mensaje, Modules moduloPublisher, Modules moduloDestino, String usecase, String target, String status) throws Exception {

        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(moduloPublisher);

        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.JSON,target,status);

        broker.endConnection(publisherConnection);

    }
    public static void crearLog(ApplicationContext applicationContext, String mensaje){
        LogDAO logDAO = applicationContext.getBean(LogDAO.class);
        LogEntity log = new LogEntity();
        log.setMensaje(mensaje);
        logDAO.save(log);
    }
}
