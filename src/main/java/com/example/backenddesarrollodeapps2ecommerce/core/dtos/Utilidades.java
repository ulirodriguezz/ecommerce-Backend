package com.example.backenddesarrollodeapps2ecommerce.core.dtos;

import ar.edu.uade.*;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.LogDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.LogEntity;
import com.rabbitmq.client.Connection;
import netscape.javascript.JSObject;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Type;

public final class Utilidades {
    private static String USER = "{ user : e_commerce, password : 8^3&927#!q4W&649^% }";

    public static void enviarMensaje(Broker broker, String mensaje, Modules moduloPublisher, Modules moduloDestino, String usecase, String target) throws Exception {

        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(moduloPublisher);

        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "ss", Types.JSON,target,"600",USER);

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

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.JSON,target,"600",USER);

        broker.endConnection(publisherConnection);

    }
    public static void enviarMensaje(String mensaje, Modules moduloDestino,Modules moduloOrigen, String usecase, String target) throws Exception {
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );
        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(moduloOrigen);
        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.JSON,target,"600",USER);

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

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.ARRAY,target,"600",USER);

        broker.endConnection(publisherConnection);

    }
    public static void enviarMensaje(Broker broker, String mensaje, Modules moduloPublisher, Modules moduloDestino, String usecase, String target, String status) throws Exception {

        Connection publisherConnection = broker.startConnection();

        Publisher publisher = new Publisher(moduloPublisher);

        System.out.println("Mensaje: "+ mensaje);

        publisher.publish(publisherConnection,mensaje, moduloDestino, usecase, "", Types.JSON,target,status,USER);

        broker.endConnection(publisherConnection);

    }
    public static void logingInterno(Broker broker, String username, String password){
        username = "\""+username+"\"";
        password = "\""+password+"\"";


        String json = "{\"user\" : "+username+ "," +
                " \"password\" : "+password+"," +
                "  \"case\": \"login\"," +
                " \"origin\": \"e_commerce\"}";
        String response = "";
        Connection connection ;
        try {
            connection = broker.startConnection();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR EN EL LOGIN CORE");
            return;
        }
        Authenticator auth = new Authenticator(Modules.E_COMMERCE);
        try{
            response = auth.authenticate(connection,json);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR EN EL LOGIN CORE (Response)");
        }
        broker.endConnection(connection);
        System.out.println("Response: "+response);
    }
    public static void registerInterno(Broker broker, String username, String email, String password, String nombre, String apellido){
        username = "\""+username+"\"";
        email = "\""+email+"\"";
        password = "\""+password+"\"";
        nombre = "\""+nombre+"\"";
        apellido = "\""+apellido+"\"";

        String json = "{\"user\" : "+username+ "," +
                " \"password\" : "+password+"," +
                " \"nombre\": "+nombre+"," +
                " \"apellido\" : "+apellido+"," +
                "\"email\" : " +email+","+
                "  \"case\": \"login\"," +
                " \"origin\": \"e_commerce\"}";
        String response = "";
        Connection connection ;
        try {
            connection = broker.startConnection();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR EN EL LOGIN CORE");
            return;
        }
        Authenticator auth = new Authenticator(Modules.E_COMMERCE);
        try{
            response = auth.authenticate(connection,json);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR EN EL LOGIN CORE (Response)");
        }
        broker.endConnection(connection);
        System.out.println(response);
    }
    public static void crearLog(ApplicationContext applicationContext, String mensaje){
        LogDAO logDAO = applicationContext.getBean(LogDAO.class);
        LogEntity log = new LogEntity();
        log.setMensaje(mensaje);
        logDAO.save(log);
    }
}
