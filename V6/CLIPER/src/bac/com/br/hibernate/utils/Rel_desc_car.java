/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.utils;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author bruno
 */
public class Rel_desc_car {
    private List<Get_setor> docs;
    private List<String> ids;

    public List<Get_setor> getDocs() {
        return docs;
    }

    public void setDocs(List<Get_setor> docs) {
        this.docs = docs;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
  
    
}
