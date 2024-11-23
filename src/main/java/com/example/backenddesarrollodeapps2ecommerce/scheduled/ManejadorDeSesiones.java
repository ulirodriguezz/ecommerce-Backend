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
    Broker broker = new Broker(
            "3.142.225.39",
            5672,
            "e_commerce",
            "8^3&927#!q4W&649^%"
    );
    String tokenJWTModulo;
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void logingInterno(){
        System.out.println("---LOGIN: Loggeando al modulo en GI");
        String username = "\"e_commerce\"";
        String password = "\"\"8^3&927#!q4W&649^%\"\"";


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
            System.out.println("---LOGIN: Error en el login (CORE)");
            tokenJWTModulo = response;
            return;
        }
        Authenticator auth = new Authenticator(Modules.E_COMMERCE);
        try{
            response = auth.authenticate(connection,json);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("---LOGIN: Error en el login (atenticacion)");
            tokenJWTModulo = response;
            return;
        }
        broker.endConnection(connection);
        System.out.println("---LOGIN: Modulo loggeado con exito");
        tokenJWTModulo = response;
    }

    public String getTokenJWTModulo() {
        return tokenJWTModulo;
    }
}
