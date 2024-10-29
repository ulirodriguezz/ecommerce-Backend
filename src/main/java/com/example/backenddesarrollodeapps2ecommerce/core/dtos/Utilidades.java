package com.example.backenddesarrollodeapps2ecommerce.core.dtos;

import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.LogDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import com.rabbitmq.client.Connection;
import org.springframework.context.ApplicationContext;

public final class Utilidades {

    public static void enviarMensaje(Broker broker, String mensaje,Modules moduloPublisher, Modules moduloDestino, String usecase) throws Exception {

        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(moduloPublisher);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "ss", Types.JSON);

        broker.endConnection(publisherConnection);

    }
    public static void enviarMensaje(String mensaje, Modules moduloDestino, String usecase) throws Exception {
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );
        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(Modules.USUARIO);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "ss", Types.JSON);

        broker.endConnection(publisherConnection);

    }
    public static void crearLog(ApplicationContext applicationContext, String mensaje){
        LogDAO logDAO = applicationContext.getBean(LogDAO.class);
        LogEntity log = new LogEntity();
        log.setMensaje(mensaje);
        logDAO.save(log);
    }
}
