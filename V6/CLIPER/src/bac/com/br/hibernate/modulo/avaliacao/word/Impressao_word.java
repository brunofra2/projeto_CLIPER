package bac.com.br.hibernate.modulo.avaliacao.word;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.entidade.Avaliacao;
import bac.com.br.hibernate.utils.Msg;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.text.Document;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.wml.P;
import org.docx4j.wml.TextDirection;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

public class Impressao_word {

    static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            CTVMerge vmerge = CTVMerge.Factory.newInstance();
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                vmerge.setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                vmerge.setVal(STMerge.CONTINUE);
                // and the content should be removed
                for (int i = cell.getParagraphs().size(); i > 0; i--) {
                    cell.removeParagraph(0);
                }
                cell.addParagraph();
            }
            // Try getting the TcPr. Not simply setting an new one every time.
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setVMerge(vmerge);
            } else {
                // only set an new TcPr if there is not one already
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setVMerge(vmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }

    static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        for (int colIndex = fromCol; colIndex <= toCol; colIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);

            CTHMerge hmerge = CTHMerge.Factory.newInstance();
            if (colIndex == fromCol) {
                // The first merged cell is set with RESTART merge value
                hmerge.setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                hmerge.setVal(STMerge.CONTINUE);
                // and the content should be removed
                for (int i = cell.getParagraphs().size(); i > 0; i--) {
                    cell.removeParagraph(0);
                }
                cell.addParagraph();
            }
            // Try getting the TcPr. Not simply setting an new one every time.
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setHMerge(hmerge);
            } else {
                // only set an new TcPr if there is not one already
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setHMerge(hmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }

    static XWPFRun createrun(String texto, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(texto);
        run.setFontFamily("Times New Roman");
        run.setFontSize(12);
        run.setBold(true);
        return run;
    }

    private static XWPFRun setRun(XWPFRun run, String fontFamily, int fontSize, String colorRGB, String text, boolean bold, boolean addBreak) {
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setColor(colorRGB);
        run.setText(text);
        run.setBold(bold);
        //if (addBreak) run.addBreak();
        return run;
    }

    private static XWPFParagraph setstyle(XWPFTable table, int row, int col, boolean boo) {

        XWPFTableRow row2 = table.getRow(row);
        XWPFParagraph para = row2.getCell(col).addParagraph();
        if (boo == true) {
            para.setAlignment(ParagraphAlignment.CENTER);
        }
        para.setVerticalAlignment(TextAlignment.TOP);

        //setSingleLineSpacing(para);
        return para;
    }

    public static void setSingleLineSpacing(XWPFParagraph para) {
        CTPPr ppr = para.getCTP().getPPr();
        if (ppr == null) {
            ppr = para.getCTP().addNewPPr();
        }
        CTSpacing spacing = ppr.isSetSpacing() ? ppr.getSpacing() : ppr.addNewSpacing();

    }

    private static int getImageFormat(String imgFileName) {
        int format;
        if (imgFileName.endsWith(".emf")) {
            format = XWPFDocument.PICTURE_TYPE_EMF;
        } else if (imgFileName.endsWith(".wmf")) {
            format = XWPFDocument.PICTURE_TYPE_WMF;
        } else if (imgFileName.endsWith(".pict")) {
            format = XWPFDocument.PICTURE_TYPE_PICT;
        } else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg")) {
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        } else if (imgFileName.endsWith(".png")) {
            format = XWPFDocument.PICTURE_TYPE_PNG;
        } else if (imgFileName.endsWith(".dib")) {
            format = XWPFDocument.PICTURE_TYPE_DIB;
        } else if (imgFileName.endsWith(".gif")) {
            format = XWPFDocument.PICTURE_TYPE_GIF;
        } else if (imgFileName.endsWith(".tiff")) {
            format = XWPFDocument.PICTURE_TYPE_TIFF;
        } else if (imgFileName.endsWith(".eps")) {
            format = XWPFDocument.PICTURE_TYPE_EPS;
        } else if (imgFileName.endsWith(".bmp")) {
            format = XWPFDocument.PICTURE_TYPE_BMP;
        } else if (imgFileName.endsWith(".wpg")) {
            format = XWPFDocument.PICTURE_TYPE_WPG;
        } else {
            return 0;
        }
        return format;
    }

    public static void executar_impressao(Avaliacao ava) throws Exception {

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/YYYY");
        NumberFormat z = NumberFormat.getCurrencyInstance();
        XWPFDocument document = new XWPFDocument();

        List<BigInteger> lista = new ColaboradorDao().idade(ava);
        
        
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(720L));
        pageMar.setTop(BigInteger.valueOf(720L));
        pageMar.setRight(BigInteger.valueOf(720L));
        pageMar.setBottom(BigInteger.valueOf(1440L));

        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.TOP);
        setRun(paragraph.createRun(), "Times New Roman", 12, "000000", "ADM - Avaliação de Colaboradores", true, true);

        //create table
        XWPFTable table = document.createTable(24, 7);

        setRun(setstyle(table, 0, 0, false).createRun(), "Times New Roman", 8, "000000", "Nome: " + ava.getIdColaborador().getNome(), true, true);

        setRun(setstyle(table, 0, 3, false).createRun(), "Times New Roman", 8, "000000", "Registro: " + ava.getIdColaborador().getRegistro(), true, true);
        setRun(setstyle(table, 0, 5, false).createRun(), "Times New Roman", 8, "000000", "Admissão: " + dt.format(ava.getIdColaborador().getDataAdm()), true, true);
        setRun(setstyle(table, 0, 6, false).createRun(), "Times New Roman", 8, "000000", "Idade: "+lista.get(0).toString().replace("-", ""), true, true);

        //linha 2        
        setRun(setstyle(table, 1, 0, false).createRun(), "Times New Roman", 8, "000000", "Cargo Proposto: ", true, true);
        setRun(setstyle(table, 1, 2, false).createRun(), "Times New Roman", 8, "000000", ava.getIdCargo().getFuncao(), true, true);

        //linha 3
        setRun(setstyle(table, 2, 0, false).createRun(), "Times New Roman", 8, "000000", "Salarios :", true, true);
        setRun(setstyle(table, 2, 1, false).createRun(), "Times New Roman", 8, "000000", "Atual: ", true, true);
        setRun(setstyle(table, 2, 2, false).createRun(), "Times New Roman", 8, "000000", z.format(ava.getSalarioAtual()), true, true);
        setRun(setstyle(table, 2, 3, false).createRun(), "Times New Roman", 8, "000000", "Proposto: ", true, true);
        setRun(setstyle(table, 2, 4, false).createRun(), "Times New Roman", 8, "000000", z.format(ava.getSalarioProposto()), true, true);
        setRun(setstyle(table, 2, 5, false).createRun(), "Times New Roman", 8, "000000", "Percentual de aumento:", true, true);
        Double subtracao = ava.getSalarioProposto() - ava.getSalarioAtual();
        
        System.out.println(subtracao);
        
        //linha 4        
        setRun(setstyle(table, 3, 0, true).createRun(), "Times New Roman", 8, "000000", "Mudança de Adicionais:", true, true);

        //linha 5        
        setRun(setstyle(table, 4, 0, false).createRun(), "Times New Roman", 8, "000000", "Periculosidade: ( ) sim ( ) Não", true, true);
        setRun(setstyle(table, 4, 4, true).createRun(), "Times New Roman", 8, "000000", " Insalubridade : ( ) Na ( ) 10% ( ) 20% ( ) 40%", true, true);
        //setRun(setstyle(table, 4, 5, true).createRun(), "Times New Roman", 12, "000000", "( ) não", true, true);

        //linha 6        
        //setRun(setstyle(table, 5, 0, false).createRun(), "Times New Roman", 12, "000000", "Insalubridade:", true, true);
        // setRun(setstyle(table, 5, 3, true).createRun(), "Times New Roman", 12, "000000", "( ) Na", true, true);
        // setRun(setstyle(table, 5, 4, true).createRun(), "Times New Roman", 12, "000000", "( ) 10%:", true, true);
        // setRun(setstyle(table, 5, 5, true).createRun(), "Times New Roman", 12, "000000", "( ) 20%", true, true);
        // setRun(setstyle(table, 5, 6, true).createRun(), "Times New Roman", 12, "000000", "( ) 40%", true, true);
        //linha 7        
        setRun(setstyle(table, 5, 0, false).createRun(), "Times New Roman", 8, "000000", "Requisitos Legais", true, true);

        //linha 8
        setRun(setstyle(table, 6, 0, false).createRun(), "Times New Roman", 8, "000000", "Treinamentos :", true, true);
        setRun(setstyle(table, 6, 3, true).createRun(), "Times New Roman", 8, "000000",
                ava.getTreinamentos().contains("operacionais")
                ? "(X) Operacionais" : "( ) Operacionais", true, true);
        setRun(setstyle(table, 6, 4, true).createRun(), "Times New Roman", 8, "000000",
                ava.getTreinamentos().contains("seguranca")
                ? "(X) Segurança" : "( ) Segurança", true, true);
        setRun(setstyle(table, 6, 5, true).createRun(), "Times New Roman", 8, "000000",
                ava.getTreinamentos().contains("legais")
                ? "(X) Legais" : "( ) Legais", true, true);
        setRun(setstyle(table, 6, 6, true).createRun(), "Times New Roman", 8, "000000",
                ava.getTreinamentos().contains("operacionais")
                ? "(X) Descrição de cargo" : "( ) Descrição de Cargo", true, true);

        //linha 9
        //setRun(setstyle(table, 8, 0, false).createRun(), "Times New Roman", 12, "000000", "Treinamentos de Segurança", true, true);
        // setRun(setstyle(table, 8, 3, true).createRun(), "Times New Roman", 12, "000000", "( ) Atende", true, true);
        //setRun(setstyle(table, 8, 5, true).createRun(), "Times New Roman", 12, "000000", "( ) Não Atende", true, true);
        //linha 10
        setRun(setstyle(table, 7, 0, false).createRun(), "Times New Roman", 8, "000000", "Exames:", true, true);
        setRun(setstyle(table, 7, 3, true).createRun(), "Times New Roman", 8, "000000",
                ava.getExames().contains("audiometria")
                ? "(X) Audiometria" : "( ) Audiometria", true, true);
        setRun(setstyle(table, 7, 4, true).createRun(), "Times New Roman", 8, "000000",
                ava.getExames().contains("espirometria") ? "(X) Espirometria" : "( ) Espirometria", true, true);
        setRun(setstyle(table, 7, 5, true).createRun(), "Times New Roman", 8, "000000",
                ava.getExames().contains("acuidade") ? "(X) Acuidade Visual" : "( ) Acuidade visual", true, true);
        setRun(setstyle(table, 7, 6, true).createRun(), "Times New Roman", 8, "000000",
                ava.getExames().contains("RX") ? "(X) RX de torax" : "( ) RX de torax", true, true);

        //linha 11
        setRun(setstyle(table, 8, 0, true).createRun(), "Times New Roman", 8, "000000", "Termo de Ciência:", true, true);

        //linha 12
        setRun(setstyle(table, 9, 0, false).createRun(), "Times New Roman", 8, "000000", ""
                + "Declaro estar ciente de que, a contar da data " + dt.format(ava.getDataAvaliacao()) + ", encontrome em perido"
                + " experimental, pelo prazo de três meses, conforme clausula xxx do acordo coletivo e que somente"
                + " após esse periodo serei promovido há função de " + ava.getIdCargo().getFuncao() + ", mediante aprovação.", false, true);

        //linha 13
        setRun(setstyle(table, 10, 0, false).createRun(), "Times New Roman", 8, "000000", ""
                + "Assinatura do funcionario _________________________ data:     /  /   ", true, true);

        //linha14
        setRun(setstyle(table, 11, 0, true).createRun(), "Times New Roman", 8, "000000", ""
                + "Parecer da Supervisão", true, true);

        //linha 15
        setRun(setstyle(table, 12, 0, false).createRun(), "Times New Roman", 8, "000000", ava.getParecerSup() != null ? ava.getParecerSup() : "", false, true);

        String s = "2012-01-01";
        String sup = ava.getDataSup().toString();
        String res = "";
        if (sup.equals(s)) {
            res = "Assinatura do Supervisor: ____________________________ Data:   /  /  ";
        } else {
            res = "Assinatura do Supervisor: ____________________________ Data: " + dt.format(ava.getDataSup());
        }
        //linha 16
        setRun(setstyle(table, 13, 0, false).createRun(), "Times New Roman", 8, "000000", res, true, true);;;

        //linha 17
        setRun(setstyle(table, 14, 0, true).createRun(), "Times New Roman", 8, "000000", ""
                + "Parecer da Gerencia", true, true);

        String gen = ava.getDataGen().toString();
        res = "";
        if (gen.equals(s)) {
            res = "Assinatura do Gerente: ____________________________ Data:   /  /  ";
        } else {
            res = "Assinatura do Gerente: ____________________________ Data: " + dt.format(ava.getDataGen());
        }
        //linha 18
        setRun(setstyle(table, 15, 0, false).createRun(), "Times New Roman", 8, "000000", ava.getParecerGen() != null ? ava.getParecerGen() : "", false, true);

        //linha 19
        setRun(setstyle(table, 16, 0, false).createRun(), "Times New Roman", 8, "000000", res, true, true);

        //linha 20
        setRun(setstyle(table, 17, 0, true).createRun(), "Times New Roman", 8, "000000", "Aprovação:", true, true);

        //linha 21
        setRun(setstyle(table, 18, 0, false).createRun(), "Times New Roman", 8, "000000", ava.getParecerRh() != null ? ava.getParecerRh() : "", false, true);

        //linha 22
        String rh = ava.getDataRh().toString();
        System.out.println("s" + s + "rh" + rh);
        res = "";
        if (rh.equals(s)) {
            res = "Visto da diretoria/RH: ____________________________ Data:   /  /  ";
        } else {
            res = "Visto da diretoria/RH: ____________________________ Data: " + dt.format(ava.getDataRh());
        }
        setRun(setstyle(table, 19, 0, false).createRun(), "Times New Roman", 8, "000000", res, true, true);;

        //create and set column widths for all columns in all rows
        //most examples don't set the type of the CTTblWidth but this
        //is necessary for working in all office versions
        /*for (int col = 0; col < 7; col++) {
            CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
            tblWidth.setW(BigInteger.valueOf(1 * 1440));
            tblWidth.setType(STTblWidth.DXA);
            for (int row = 0; row < 24; row++) {
                CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
                if (tcPr != null) {
                    tcPr.setTcW(tblWidth);
                } else {
                    tcPr = CTTcPr.Factory.newInstance();
                    tcPr.setTcW(tblWidth);
                    table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
                }
            }
        }*/
        //MESCLAGENS DE CELULAS 
        mergeCellHorizontally(table, 0, 0, 2);
        mergeCellHorizontally(table, 0, 3, 4);
        mergeCellHorizontally(table, 1, 0, 1);
        mergeCellHorizontally(table, 1, 2, 6);
        mergeCellHorizontally(table, 3, 0, 6);
        mergeCellHorizontally(table, 4, 0, 3);
        mergeCellHorizontally(table, 4, 4, 6);
        mergeCellHorizontally(table, 5, 0, 2);
        mergeCellHorizontally(table, 5, 3, 6);
        mergeCellHorizontally(table, 6, 0, 2);
        //mergeCellHorizontally(table, 7, 0, 2);
        // mergeCellHorizontally(table, 7, 3, 4);
        // mergeCellHorizontally(table, 7, 5, 6);
        //mergeCellHorizontally(table, 8, 0, 2);
        //  mergeCellHorizontally(table, 8, 3, 4);
        //mergeCellHorizontally(table, 8, 5, 6);
        mergeCellHorizontally(table, 7, 0, 2);
        mergeCellHorizontally(table, 8, 0, 6);
        mergeCellHorizontally(table, 9, 0, 6);
        mergeCellHorizontally(table, 10, 0, 6);
        mergeCellHorizontally(table, 11, 0, 6);
        mergeCellHorizontally(table, 12, 0, 6);
        mergeCellHorizontally(table, 13, 0, 6);
        mergeCellHorizontally(table, 14, 0, 6);
        mergeCellHorizontally(table, 15, 0, 6);
        mergeCellHorizontally(table, 16, 0, 6);
        mergeCellHorizontally(table, 17, 0, 6);
        mergeCellHorizontally(table, 18, 0, 6);
        mergeCellHorizontally(table, 19, 0, 6);
        mergeCellHorizontally(table, 20, 0, 6);
        mergeCellHorizontally(table, 21, 0, 6);
        mergeCellHorizontally(table, 22, 0, 6);
        mergeCellHorizontally(table, 23, 0, 6);

        FileOutputStream out = new FileOutputStream("S:\\TI\\GESTÃO DE TI\\avaliacoes e termos\\teste.docx");
        document.write(out);
        out.close();

        System.out.println("create_table.docx written successully");
    }
}
