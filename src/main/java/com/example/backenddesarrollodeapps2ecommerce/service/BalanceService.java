package com.example.backenddesarrollodeapps2ecommerce.service;

import com.example.backenddesarrollodeapps2ecommerce.model.dao.BalanceDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.BalanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    BalanceDAO balanceDAO;

    public BalanceEntity getBalance(){
        try{
            return balanceDAO.getBalance();
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error("Ocurrió un error");
        }
    }
}
