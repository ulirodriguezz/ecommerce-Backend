package com.example.backenddesarrollodeapps2ecommerce.scheduled;

import ar.edu.uade.Modules;
import ar.edu.uade.Utilities;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.BalanceDTO;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.Utilidades;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;
import com.example.backenddesarrollodeapps2ecommerce.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BalanceSender {
    @Autowired
    BalanceService balanceService;
    @Autowired
    ManejadorDeSesiones manejadorDeSesiones;
    boolean error;
    public BalanceSender(){
        this.error = false;
    }
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24 * 15)
    public void enviarBalance(){
       BalanceDTO balance = new BalanceDTO(balanceService.getBalance());
        try {
            Utilidades.enviarMensaje(Utilities.convertClass(balance),Modules.GESTION_FINANCIERA,"balance","balance",manejadorDeSesiones.getTokenJWTModulo());
        } catch (Exception e) {
            System.out.println("ERROR AL ENVIAR BALANCE");
            this.error = true;
        }
    }
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    private void reintentar()
    {
        if(this.error == true){
            System.out.println("---BALANCE: Reenviando el balance");
            this.error = false;
            enviarBalance();
        }
    }

}
