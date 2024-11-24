package com.example.backenddesarrollodeapps2ecommerce.scheduled;

import ar.edu.uade.Authenticator;
import ar.edu.uade.Broker;
import ar.edu.uade.Modules;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.Utilidades;
import com.rabbitmq.client.Connection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ManejadorDeSesiones {

    String tokenJWTModulo;
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public  void logingInterno( ){
        System.out.println("---LOGIN: Iniciando login a GI...");
        Broker broker = new Broker(
                "3.141.117.124",
                5672,
                "e_commerce",
                "8^3&927#!q4W&649^%"
        );
        String username = "\"e_commerce\"";
        String password = "\"8^3&927#!q4W&649^%\"";


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
            System.out.println("---LOGIN: ERROR en el login (CORE)");
           return;
        }
        Authenticator auth = new Authenticator(Modules.E_COMMERCE);
        try{
            response = auth.authenticate(connection,json);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("---LOGIN: ERROR en el login (Autenticacion)");
            return;
        }
        broker.endConnection(connection);
        System.out.println("---LOGIN: Login realizado. Token: "+ response);
        tokenJWTModulo = response;
    }

    public String getTokenJWTModulo() {
        return tokenJWTModulo;
    }
}
