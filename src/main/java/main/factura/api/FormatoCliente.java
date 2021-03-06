/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.factura.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.factura.*;
import main.factura.FormatoJSON;
import org.json.JSONObject;

/**
 *
 * @author danie
 */
public class FormatoCliente implements Formato {
    
    private static String HEROKU = "https://aremfinalapi.herokuapp.com/cliente?factura=";
    
    @Override
    public String getResult(String datos) {
        JSONObject json;
        URL restApi = null;
        String res = "<p>";
        Factura fac = new FormatoJSON();
        json = fac.convertir(datos);
        System.out.println(json);        
        
        try {
            restApi = new URL(HEROKU + json.toString().replace("[", "").replace("]", ""));
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormatoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(restApi.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null){
                System.out.println(inputLine);
                res += inputLine;
                res += "</br>";
            }
            res += "</p>";
        }catch (IOException x) {
            System.err.println(x);
        }
        
        return res;    
    }
    
}
