/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Usuário
 */
public class Singleton {
    private static EntityManager conection;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aejn");
    
    //GETCONECTION
    public static EntityManager getconection(){
        if(conection == null){
            setconection();
        }
    return conection;
    }
    //SETCONECTION
    public static void setconection(){
        conection = emf.createEntityManager();
    }
}
