/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author bruno
 */
public class Loggin {
    static Logger log = Logger.getLogger(Loggin.class);
    
    public static void arquivo_log(String s){
        log.setLevel(Level.WARN);
        log.debug("teste de log");
        log.warn(s);
    }
    
}
