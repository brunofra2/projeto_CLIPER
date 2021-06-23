/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.profissiografia.excel;

import bac.com.br.hibernate.utils.Msg;
import java.io.File;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author bruno
 */
public class impressao_cargo {
    public void gerar_tabela(){
        try {
            File file = new File("D://teste.xls");
            WorkbookSettings wbseting = new WorkbookSettings();
            WritableWorkbook workbook = Workbook.createWorkbook(file,wbseting);
            workbook.createSheet("teste de tabela", 0);
            WritableSheet excelsheet = workbook.getSheet(0);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao gerar tabela "+e.getMessage());
        }
    }
}
