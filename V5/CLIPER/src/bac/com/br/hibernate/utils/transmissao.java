/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.utils;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author bruno
 */
public class transmissao {
    public void transferir(){
            File f = new File("C:\\Users\\bruno.CAMBUI-PR\\Desktop\\backup11-04-2018.sql");
            //Utils.envioftp("192.168.100.67", "/root/home/softhorus/treinamento/backups/",f, "root", "cmb.isis#24511");
            Utils.envioftp("teste1teste1.ddns.net", "c:\\",f, "bruno", "cambui@2018");
            System.out.println("transmitido com sucesso");
        
    }
}
