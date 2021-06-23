/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.profissiografia.excel;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.entidade.Atividades;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.entidade.ItemSeguranca;
import bac.com.br.hibernate.entidade.Risco;
import bac.com.br.hibernate.utils.Msg;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author bruno
 */
public class Impressao_ppp {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;

    public void criar_excel(Colaborador c) {
        try {

           // File file = new File("D:\\" + c.getRegistro() + ".xls");
            File file = new File("S:\\RH\\PPP_CLIPER\\" + c.getRegistro() + ".xls");
            WorkbookSettings wbseting = new WorkbookSettings();
            WritableWorkbook workbook = Workbook.createWorkbook(file, wbseting);
            workbook.createSheet(c.getRegistro() + " - " + c.getNome(), 0);
            WritableSheet excelsheet = workbook.getSheet(0);
            criarcabecalho(excelsheet, workbook, c);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao criar arquivo excel" + e.getMessage());
        }
        /* 
         try {
         FileOutputStream out = new FileOutputStream(new File("D:\\"+c.getRegistro()+".xlsx"));
            
         out.flush();
         out.close();A
         Msg.informacao(null, "documento criado com sucesso");
         } catch (Exception e) {
         e.printStackTrace();
         Msg.erro(null, "erro "+e.getMessage());
         }*/
    }

    private void ajustar_colunas(WritableSheet sheet) {
        try {
            sheet.setColumnView(0, 10);
            sheet.setColumnView(1, 13);
            sheet.setColumnView(2, 10);
            sheet.setColumnView(3, 9);
            sheet.setColumnView(4, 13);
            sheet.setColumnView(5, 6);
            sheet.setColumnView(6, 11);
            sheet.setColumnView(7, 8);
            sheet.setColumnView(8, 10);
            sheet.setColumnView(9, 11);
            sheet.setColumnView(10, 13);
            sheet.setColumnView(11, 13);

        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao setar tamanho das colunas" + e.getMessage());
        }

    }

    private void mesclagemdecelulas(WritableSheet sheet) {
        try {

            //CABEÇALHO
            sheet.mergeCells(5, 0, 7, 0);
            sheet.mergeCells(5, 1, 7, 1);
            sheet.mergeCells(3, 2, 8, 2);
            sheet.mergeCells(5, 3, 7, 3);
            sheet.mergeCells(2, 4, 10, 4);
            sheet.mergeCells(2, 5, 10, 5);
            sheet.mergeCells(2, 6, 10, 6);

            // SEÇÃO ADMINISTRATITIVA
            sheet.mergeCells(0, 8, 11, 8);
            sheet.mergeCells(0, 9, 3, 9);
            sheet.mergeCells(4, 9, 6, 9);
            sheet.mergeCells(0, 10, 1, 10);
            sheet.mergeCells(2, 10, 6, 10);
            sheet.mergeCells(0, 11, 2, 11);
            sheet.mergeCells(3, 11, 6, 11);
            sheet.mergeCells(4, 12, 5, 12);
            sheet.mergeCells(6, 12, 7, 12);
            sheet.mergeCells(8, 12, 9, 12);
            sheet.mergeCells(2, 13, 4, 13);
            sheet.mergeCells(0, 14, 1, 14);
            sheet.mergeCells(2, 14, 3, 14);
            sheet.mergeCells(4, 14, 6, 14);

            // SEÇÃO CAT
            sheet.mergeCells(0, 16, 11, 16);
            sheet.mergeCells(0, 17, 1, 17);
            sheet.mergeCells(2, 17, 3, 17);
            sheet.mergeCells(4, 17, 5, 17);
            sheet.mergeCells(6, 17, 7, 17);
            sheet.mergeCells(8, 17, 9, 17);
            sheet.mergeCells(10, 17, 11, 17);

            sheet.mergeCells(0, 18, 1, 18);
            sheet.mergeCells(2, 18, 3, 18);
            sheet.mergeCells(4, 18, 5, 18);
            sheet.mergeCells(6, 18, 7, 18);
            sheet.mergeCells(8, 18, 9, 18);
            sheet.mergeCells(10, 18, 11, 18);

            sheet.mergeCells(0, 19, 1, 19);
            sheet.mergeCells(2, 19, 3, 19);
            sheet.mergeCells(4, 19, 5, 19);
            sheet.mergeCells(6, 19, 7, 19);
            sheet.mergeCells(8, 19, 9, 19);
            sheet.mergeCells(10, 19, 11, 19);

        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao mesclar celulas" + e.getMessage());
        }
    }

    private void criarcabecalho(WritableSheet sheet, WritableWorkbook w, Colaborador c) {
        try {
            WritableFont n9 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
            WritableFont n14 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
            WritableFont n14_1 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
            WritableCellFormat negrito = new WritableCellFormat();
            WritableCellFormat italico = new WritableCellFormat();
            WritableCellFormat normal = new WritableCellFormat();
            negrito.setAlignment(Alignment.CENTRE);
            negrito.setFont(n14);
            italico.setAlignment(Alignment.CENTRE);
            italico.setFont(n9);
            normal.setAlignment(Alignment.CENTRE);
            normal.setFont(n14_1);
            File imagem = new File("c:\\CLIPER\\inss.png");
            BufferedImage input = ImageIO.read(imagem);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(input, "PNG", baos);
            sheet.addImage(new WritableImage(5,0,10,10,baos.toByteArray()));
            Label linha1 = new Label(5, 1, "PREVIDÊNCIA SOCIAL", italico);
            Label linha2 = new Label(3, 2, "INSTITUTO NACIONAL DO SEGURO SOCIAL", italico);
            Label linha3 = new Label(5, 3, "ANEXO I", negrito);
            Label linha4 = new Label(2, 4, "INSTRUÇÃO NORMATIVA Nº 85/PRES/INSS, DE 18 DE FEVEREIRO DE 2016", negrito);
            Label linha5 = new Label(2, 5, "(Substitui o anexo da IN nº 77/PRES/INSS, de 21 de Janeiro de 2015)", normal);
            Label linha6 = new Label(2, 6, "PREFIL PROFISSIOGRAFICO PREFIDENCIARIO - PPP", negrito);
            sheet.addCell(linha1);
            sheet.addCell(linha2);
            sheet.addCell(linha3);
            sheet.addCell(linha4);
            sheet.addCell(linha5);
            sheet.addCell(linha6);
            ajustar_colunas(sheet);
            mesclagemdecelulas(sheet);
            criar_solicitacao(sheet, c, estilos(), normal());
            criar_cat(sheet, estilos(), normal());
            Integer count = 20;
            Integer ppp = criar_lotacao(sheet, c, count, estilos(), normal());
            Integer risco = criar_profissiografia(sheet, c, ppp, estilos(), normal());
            Integer rodape = criar_risco(sheet, c, risco, estilos(), normal());
            rodape(sheet, c, rodape, estilos(), normal());
            w.write();
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro" + e.getMessage());
        }
    }

    private WritableCellFormat estilos() {
        WritableFont n11 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, true);
        WritableCellFormat negrito = new WritableCellFormat(n11);
        try {

            negrito.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao setar estilo" + e.getMessage());
        }
        return negrito;
    }

    private WritableCellFormat normal() {
        WritableFont n10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, true);
        WritableCellFormat normal = new WritableCellFormat(n10);

        try {
            normal.setBorder(Border.ALL, BorderLineStyle.THIN);
            normal.setWrap(true);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro"+e.getMessage());
        }
        return normal;
    }

    private WritableCellFormat borda(int i) {
        WritableCellFormat retorno = null;
        try {
            WritableFont n10 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD, true);
            if (i == 0) {
                WritableCellFormat esquerda = new WritableCellFormat(n10);
                esquerda.setBorder(Border.LEFT, BorderLineStyle.THIN);
                retorno = esquerda;
            } else {
                WritableCellFormat direita = new WritableCellFormat(n10);
                direita.setBorder(Border.RIGHT, BorderLineStyle.THIN);
                retorno = direita;
            }
        } catch (Exception e) {
        }
        return retorno;
    }

    private void criar_solicitacao(WritableSheet sheet, Colaborador c, WritableCellFormat negrito, WritableCellFormat normal) {
        try {
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
            Label texto1 = new Label(0, 8, "I - SEÇÃO DE DADOS ADMINISTRATIVOS", negrito);
            Label texto2 = new Label(0, 9, "1 - CNPJ do domicilio Tributario/CEI", normal);
            Label texto3 = new Label(4, 9, c.getFuncaoId().getDomicilioId().getCnpj(), normal);
            Label texto4 = new Label(0, 10, "2 - Nome Empresarial", normal);
            Label texto5 = new Label(2, 10, c.getFuncaoId().getDomicilioId().getNome(), negrito);
            Label texto6 = new Label(7, 10, "3 - CNAE", normal);
            Label texto7 = new Label(8, 10, c.getFuncaoId().getDomicilioId().getCnae(), normal);
            Label texto8 = new Label(0, 11, "4 - Nome do Trabalhador", normal);
            Label texto9 = new Label(3, 11, c.getNome(), negrito);
            Label texto10 = new Label(0, 12, "BR/PDH", normal);
            Label texto11 = new Label(1, 12, "NA", normal);
            Label texto12 = new Label(2, 12, "6 - NIT", normal);
            Label texto13 = new Label(4, 12, c.getPis(), normal);
            Label texto14 = new Label(6, 12, "7 - data do nascimento", normal);
            Label texto15 = new Label(8, 12, form.format(c.getDataNasc()), normal);
            Label texto16 = new Label(0, 13, "Sexo(F/M)", normal);
            Label texto17 = new Label(1, 13, c.getSexo(), normal);
            Label texto18 = new Label(2, 13, "9 - CTPS (Nº Série e uf)", normal);
            Label texto19 = new Label(5, 13, c.getCtps(), normal);
            Label texto20 = new Label(6, 13, c.getSerie(), normal);
            Label texto21 = new Label(6, 13, c.getEstado(), normal);

            //bordas
            Label texto22 = new Label(11, 9, "", borda(1));
            Label texto23 = new Label(11, 10, "", borda(1));
            Label texto24 = new Label(11, 11, "", borda(1));
            Label texto25 = new Label(11, 12, "", borda(1));
            Label texto26 = new Label(11, 13, "", borda(1));
            Label texto27 = new Label(11, 14, "", borda(1));
            Label texto28 = new Label(11, 15, "", borda(1));

            Label texto29 = new Label(0, 14, "", borda(0));
            Label texto30 = new Label(0, 15, "", borda(0));

            sheet.addCell(texto1);
            sheet.addCell(texto2);
            sheet.addCell(texto3);
            sheet.addCell(texto4);
            sheet.addCell(texto5);
            sheet.addCell(texto6);
            sheet.addCell(texto7);
            sheet.addCell(texto8);
            sheet.addCell(texto9);
            sheet.addCell(texto10);
            sheet.addCell(texto11);
            sheet.addCell(texto12);
            sheet.addCell(texto13);
            sheet.addCell(texto14);
            sheet.addCell(texto15);
            sheet.addCell(texto16);
            sheet.addCell(texto17);
            sheet.addCell(texto18);
            sheet.addCell(texto19);
            sheet.addCell(texto20);
            sheet.addCell(texto21);
            sheet.addCell(texto22);
            sheet.addCell(texto23);
            sheet.addCell(texto24);
            sheet.addCell(texto25);
            sheet.addCell(texto26);
            sheet.addCell(texto27);
            sheet.addCell(texto28);
            sheet.addCell(texto29);
            sheet.addCell(texto30);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao setar campos administrativos" + e.getMessage());
        }
    }

    private void criar_cat(WritableSheet sheet, WritableCellFormat negrito, WritableCellFormat normal) {
        try {
            Label texto1 = new Label(0, 16, "12 | CAT REGISTRADA", negrito);
            Label texto2 = new Label(0, 17, "12.1 - Data do Registro", normal);
            Label texto3 = new Label(2, 17, "12.2 - Numero da Cat", normal);
            Label texto4 = new Label(4, 17, "12.1 - Data do Registro", normal);
            Label texto5 = new Label(6, 17, "12.2 - Numero da Cat", normal);
            Label texto6 = new Label(8, 17, "12.2 - Data do Registro", normal);
            Label texto7 = new Label(10, 17, "12.2 - Numero da CAT", normal);

            Label texto8 = new Label(0, 18, "", normal);
            Label texto9 = new Label(2, 18, "", normal);
            Label texto10 = new Label(4, 18, "", normal);
            Label texto11 = new Label(6, 18, "", normal);
            Label texto12 = new Label(8, 18, "", normal);
            Label texto13 = new Label(10, 18, "", normal);

            Label texto14 = new Label(0, 19, "", normal);
            Label texto15 = new Label(2, 19, "", normal);
            Label texto16 = new Label(4, 19, "", normal);
            Label texto17 = new Label(6, 19, "", normal);
            Label texto18 = new Label(8, 19, "", normal);
            Label texto19 = new Label(10, 19, "", normal);

            sheet.addCell(texto1);
            sheet.addCell(texto2);
            sheet.addCell(texto3);
            sheet.addCell(texto4);
            sheet.addCell(texto5);
            sheet.addCell(texto6);
            sheet.addCell(texto7);

            sheet.addCell(texto8);
            sheet.addCell(texto9);
            sheet.addCell(texto10);
            sheet.addCell(texto11);
            sheet.addCell(texto12);
            sheet.addCell(texto13);

            sheet.addCell(texto14);
            sheet.addCell(texto15);
            sheet.addCell(texto16);
            sheet.addCell(texto17);
            sheet.addCell(texto18);
            sheet.addCell(texto19);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao criar cats" + e.getMessage());
        }
    }

    private Integer criar_profissiografia(WritableSheet sheet, Colaborador c, Integer ppp, WritableCellFormat negrito, WritableCellFormat normal) {
        try {
            WritableFont times10pts = new WritableFont(WritableFont.ARIAL, 10);
            times = new WritableCellFormat(times10pts);
            times.setWrap(true);
            sheet.mergeCells(0, ppp + 1, 11, ppp + 1);
            Label titulo = new Label(0, ppp + 1, "14 | PROFISSIOGRAFIA", negrito);
            sheet.mergeCells(0, ppp + 2, 1, ppp + 2);
            Label subtitulo = new Label(0, ppp + 2, "14.1 Periodo", negrito);
            sheet.mergeCells(2, ppp + 2, 11, ppp + 2);
            Label subtitulo2 = new Label(2, ppp + 2, "14.2 Descricao das atividades", negrito);
            sheet.addCell(titulo);
            sheet.addCell(subtitulo);
            sheet.addCell(subtitulo2);

            SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
            ppp = ppp + 3;
            for (Historico his : c.getHistoricoList()) {
                Label datas;
                if (his.getPeriodoFim() == null) {
                    sheet.mergeCells(0, ppp, 1, ppp);
                    datas = new Label(0, ppp, form.format(his.getPeriodoInicio()) + " a " + "Presente data", normal);

                } else {

                    sheet.mergeCells(0, ppp, 1, ppp);
                    datas = new Label(0, ppp, form.format(his.getPeriodoInicio()) + " a " + form.format(his.getPeriodoFim()), normal);

                }
                sheet.addCell(datas);

                sheet.mergeCells(2, ppp, 11, ppp);
                for (Atividades ati : his.getIdCargo().getAtividadesList()) {
                   
                    Label atividade = new Label(2, ppp, his.getIdCargo().getFuncao() + ": " + ati.getDescricao(), normal);
                    // atividade.setCellFormat(times);

                    CellView cs = new CellView();
                    cs.setSize(1000);
                    sheet.addCell(atividade);
                    sheet.setRowView(ppp, cs);

                }
                ppp++;

                Label texto28 = new Label(11, ppp, "", borda(1));
                Label texto29 = new Label(0, ppp, "", borda(0));

                sheet.addCell(texto28);
                sheet.addCell(texto29);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao criar profissiografia");
        }
        return ppp;
    }

    private Integer criar_lotacao(WritableSheet sheet, Colaborador c, Integer count, WritableCellFormat negrito, WritableCellFormat normal) {
        try {

            sheet.mergeCells(0, count, 11, count);
            Label titulo = new Label(0, count, "13 | LOTAÇÃO E ATRIBUIÇÃO", negrito);
            sheet.mergeCells(0, count + 1, 1, count + 1);
            Label periodo = new Label(0, count + 1, "13.1 - Perido", normal);
            sheet.mergeCells(2, count + 1, 3, count + 1);
            Label cnpj = new Label(2, count + 1, "13.2 - CNPJ/CEI", normal);
            sheet.mergeCells(4, count + 1, 5, count + 1);
            Label setor = new Label(4, count + 1, "13.3 - Setor", normal);
            sheet.mergeCells(6, count + 1, 6, count + 1);
            Label cargo = new Label(6, count + 1, "13.4 - Cargo", normal);
            sheet.mergeCells(7, count + 1, 8, count + 1);
            Label funcao = new Label(7, count + 1, "13.5 - Função", normal);
            sheet.mergeCells(9, count + 1, 9, count + 1);
            Label cbo = new Label(9, count + 1, "13.6 - CBO", normal);
            sheet.mergeCells(10, count + 1, 11, count + 1);
            Label gfip = new Label(10, count + 1, "código GFIP", normal);
            sheet.addCell(titulo);
            sheet.addCell(cnpj);
            sheet.addCell(periodo);
            sheet.addCell(setor);
            sheet.addCell(cargo);
            sheet.addCell(funcao);
            sheet.addCell(cbo);
            sheet.addCell(gfip);
            count = count + 2;
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
            for (Historico his : c.getHistoricoList()) {
                Label datas;
                if (his.getPeriodoFim() == null) {

                    sheet.mergeCells(0, count, 1, count);
                    datas = new Label(0, count, form.format(his.getPeriodoInicio()) + " a " + "Presente data", normal);
                } else {

                    sheet.mergeCells(0, count, 1, count);
                    datas = new Label(0, count, form.format(his.getPeriodoInicio()) + " a " + form.format(his.getPeriodoFim()), normal);
                }
                sheet.mergeCells(2, count, 3, count);
                Label cei = new Label(2, count, his.getIdCargo().getDomicilioId().getCnpj(), normal);
                sheet.mergeCells(4, count, 5, count);
                Label set = new Label(4, count, his.getIdCargo().getLotacaoId().getTitulo(), normal);
                sheet.mergeCells(6, count, 6, count);
                Label car = new Label(6, count, "NA", normal);
                sheet.mergeCells(7, count, 8, count);
                Label fun = new Label(7, count, his.getIdCargo().getFuncao(), normal);
                sheet.mergeCells(9, count, 9, count);
                Label cbos = new Label(9, count, "NA", normal);
                sheet.mergeCells(10, count, 11, count);
                Label gfi = new Label(10, count, "NA", normal);

                sheet.addCell(datas);
                sheet.addCell(cei);
                sheet.addCell(set);
                sheet.addCell(car);
                sheet.addCell(fun);
                sheet.addCell(cbos);
                sheet.addCell(gfi);
                count++;

                Label texto28 = new Label(11, count, "", borda(1));
                Label texto29 = new Label(0, count, "", borda(0));

                sheet.addCell(texto28);
                sheet.addCell(texto29);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro");
        }
        return count;
    }

    private Integer criar_risco(WritableSheet sheet, Colaborador c, Integer risco, WritableCellFormat negrito, WritableCellFormat normal) {
        try {
            sheet.mergeCells(0, risco + 1, 11, risco + 1);
            Label titulo = new Label(0, risco + 1, "II SEÇÃO DE RESGISTROS AMBIENTAIS:", negrito);
            sheet.mergeCells(0, risco + 2, 11, risco + 2);
            Label subtitulo = new Label(0, risco + 2, "15 | EXPOSIÇÃO A FATORES DE RISCOS", negrito);
            sheet.mergeCells(0, risco + 3, 1, risco + 3);
            Label periodo = new Label(0, risco + 3, "15.1 - Periodo", normal);
            sheet.mergeCells(2, risco + 3, 2, risco + 3);
            Label tipo = new Label(2, risco + 3, "15.2 - Tipo", normal);
            sheet.mergeCells(3, risco + 3, 4, risco + 3);
            Label fator = new Label(3, risco + 3, "15.3 - Fator de Risco", normal);
            sheet.mergeCells(5, risco + 3, 6, risco + 3);
            Label inten = new Label(5, risco + 3, "15.4 - Intenc./Conc.", normal);
            sheet.mergeCells(7, risco + 3, 8, risco + 3);
            Label tecni = new Label(7, risco + 3, "15.5 - Técnica Utilizada", normal);
            sheet.mergeCells(9, risco + 3, 9, risco + 3);
            Label epc = new Label(9, risco + 3, "15.6 - EPC Eficaz (S/N)", normal);
            sheet.mergeCells(10, risco + 3, 10, risco + 3);
            Label epi = new Label(10, risco + 3, "15.7 - EPI Eficaz (S/N)", normal);
            sheet.mergeCells(11, risco + 3, 11, risco + 3);
            Label ca = new Label(11, risco + 3, "15.8 - CA EPI", normal);

            sheet.addCell(titulo);
            sheet.addCell(subtitulo);
            sheet.addCell(periodo);
            sheet.addCell(tipo);
            sheet.addCell(fator);
            sheet.addCell(inten);
            sheet.addCell(tecni);
            sheet.addCell(epc);
            sheet.addCell(epi);
            sheet.addCell(ca);
            CellView cs = new CellView();
            cs.setSize(500);
            sheet.setRowView(risco + 3, cs);

            SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
            risco = risco + 4;
            Integer teste = risco;
            for (Historico his : c.getHistoricoList()) {
                Label datas;
                if (his.getPeriodoFim() == null) {
                sheet.mergeCells(0, risco, 1, risco + his.getIdCargo().getRiscoList().size()-1);
                    datas = new Label(0, risco, form.format(his.getPeriodoInicio()) + " a " + "Presente data", normal);
                } else {
                sheet.mergeCells(0, risco, 1, risco + his.getIdCargo().getRiscoList().size()-1);
                    datas = new Label(0, risco, form.format(his.getPeriodoInicio()) + " a " + form.format(his.getPeriodoFim()), normal);
                }
                sheet.addCell(datas);
                int histo = 1;
                for (Risco ris : his.getIdCargo().getRiscoList()) {
                    int iten = 1;
                    System.out.println("*" + histo);
                    sheet.mergeCells(2, risco, 2, risco);
                    tipo = new Label(2, risco, ris.getCategoria(), normal);
                    sheet.mergeCells(3, risco, 4, risco);
                    fator = new Label(3, risco, ris.getFatorDeRisco(), normal);
                    sheet.mergeCells(5, risco, 6, risco);
                    inten = new Label(5, risco, ris.getIntencidade(), normal);
                    sheet.mergeCells(7, risco, 8, risco);
                    tecni = new Label(7, risco, ris.getClassificacao(), normal);
                    sheet.addCell(tipo);
                    sheet.addCell(fator);
                    sheet.addCell(inten);
                    sheet.addCell(tecni);
                    for (ItemSeguranca seg : his.getIdCargo().getItemSegurancaList()) {

                        System.out.println("&" + iten);
                        if (histo == iten) {
                            sheet.mergeCells(9, risco, 9, risco);
                            epc = new Label(9, risco, seg.getEpc(), normal);
                            sheet.mergeCells(10, risco, 10, risco);
                            epi = new Label(10, risco, seg.getEpi(), normal);
                            sheet.mergeCells(11, risco, 11, risco);
                            ca = new Label(11, risco, seg.getCa(), normal);
                            sheet.addCell(epc);
                            sheet.addCell(epi);
                            sheet.addCell(ca);
                        }
                        iten++;
                    }
                    risco++;
                    histo++;
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro");
        }
        return risco;
    }

    private void rodape(WritableSheet sheet, Colaborador c, Integer rod, WritableCellFormat negrito, WritableCellFormat normal) {
        try {
            sheet.mergeCells(0, rod, 10, rod);
            Label lab6 = new Label(11, rod, "(S/N)", normal);
            Label titulo = new Label(0, rod, "15.9 Atendimento aos requisitos das NR-09 do MTE pelos EPI informados", negrito);
            sheet.mergeCells(0, rod + 1, 10, rod + 1);
            Label lab1 = new Label(11, rod + 1, "S", normal);
            Label texto1 = new Label(0, rod + 1, "Foi tentada a implementação de medias de proteção coletiva,"
                    + " de caráter administrativo ou de organização do trabalho,"
                    + "optando-se pelo EPI por inviabilidade técnica, insuficiência ou interinidade,"
                    + "ou ainda em caráter complementar ou emegencial.", normal);
            sheet.mergeCells(0, rod + 2, 10, rod + 2);
            Label lab2 = new Label(11, rod + 2, "S", normal);
            Label texto2 = new Label(0, rod + 2, "Foram observadas as condições de funcionamento"
                    + "e do uso interrupto de EPI  ao longo do tempo, conforme especificação técnica"
                    + "do fabricantem ajustada as condiçoes de campo.", normal);
            sheet.mergeCells(0, rod + 3, 10, rod + 3);
            Label lab3 = new Label(11, rod + 3, "S", normal);
            Label texto3 = new Label(0, rod + 3, "Foi observado o prazo de validade, conforme certificado de Aprovação CA do MTE.", normal);
            sheet.mergeCells(0, rod + 4, 10, rod + 4);
            Label lab4 = new Label(11, rod + 4, "S", normal);
            Label texto4 = new Label(0, rod + 4, "Foi observado a periodicidade de troca definida pelos programas"
                    + "ambientais, comprovada mediante recibo assinado pelo usuário em época própria.", normal);
            sheet.mergeCells(0, rod + 5, 10, rod + 5);
            Label lab5 = new Label(11, rod + 5, "S", normal);
            Label texto5 = new Label(0, rod + 5, "Foi observada a higienização.", normal);
            sheet.addCell(titulo);
            sheet.addCell(texto1);
            sheet.addCell(texto2);
            sheet.addCell(texto3);
            sheet.addCell(texto4);
            sheet.addCell(texto5);

            sheet.addCell(lab1);
            sheet.addCell(lab2);
            sheet.addCell(lab3);
            sheet.addCell(lab4);
            sheet.addCell(lab5);
            sheet.addCell(lab6);

            // ASSINATURA
            sheet.mergeCells(0, rod + 6, 11, rod + 6);
            Label titulo2 = new Label(0, rod + 6, "16 | RESPONSAVEL PELOS REGISTROS AMBIENTAIS", negrito);
            sheet.mergeCells(0, rod + 7, 1, rod + 7);
            Label texto6 = new Label(0, rod + 7, "16.1 - Periodo", normal);
            sheet.mergeCells(2, rod + 7, 3, rod + 7);
            Label texto7 = new Label(2, rod + 7, "16.2 - NIT", normal);
            sheet.mergeCells(4, rod + 7, 7, rod + 7);
            Label texto8 = new Label(4, rod + 7, "16.3 - Registro Conselho de Classe", normal);
            sheet.mergeCells(8, rod + 7, 11, rod + 7);
            Label texto9 = new Label(8, rod + 7, "16.4 - Nome", normal);
            sheet.addCell(titulo2);
            sheet.addCell(texto6);
            sheet.addCell(texto7);
            sheet.addCell(texto8);
            sheet.addCell(texto9);
            sheet.mergeCells(0, rod + 8, 1, rod + 8);
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
            Label texto10 = null;
            for (int i = 0; i <= c.getHistoricoList().size(); i++) {

                if (i == c.getHistoricoList().size()) {
                    texto10 = new Label(0, rod + 8, form.format(c.getHistoricoList().get(0).getPeriodoInicio()) + " a " + "Presente data", normal);

                    sheet.addCell(texto10);
                }
            }

            // Label texto10 = new Label(0, rod + 8, "", normal);
            sheet.mergeCells(2, rod + 8, 3, rod + 8);
            Label texto11 = new Label(2, rod + 8, "124.80149.69-4", normal);

            sheet.mergeCells(4, rod + 8, 7, rod + 8);
            Label texto12 = new Label(4, rod + 8, "Engº Seg. Trabalho CREA/RS 112.033/D", normal);

            sheet.mergeCells(8, rod + 8, 11, rod + 8);
            Label texto13 = new Label(8, rod + 8, "ANDERSON LUIZ WEISS", normal);
            CellView cs = new CellView();
            cs.setSize(2500);
            sheet.addCell(texto11);
            sheet.addCell(texto12);
            sheet.addCell(texto13);
            sheet.setRowView(rod + 8, cs);

        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao configurar rodape \n" + e.getMessage());
        }
    }
}
