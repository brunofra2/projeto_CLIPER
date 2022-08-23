package bac.com.br.hibernate.utils;

import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.relatorios.Analise_critica;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import sun.applet.resources.MsgAppletViewer;

/**
 *
 * @author Eleton Breke
 * @version 1.0
 * @since 2013-12-18
 * @email eletonbreke@yahoo.com.br
 *
 */
public class Utils {

    public static void envioftp(String servidorftp, String diretorio, File uploadarquivo,
            String username, String senha) {
        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(servidorftp);

            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                System.out.println("conexao recusada");
                return;
            }

            ftp.login(username, senha);
            System.err.println("Workdir>>" + ftp.printWorkingDirectory());
            ftp.changeWorkingDirectory(diretorio);
            System.err.println("Workdir>>" + ftp.printWorkingDirectory());

            FileInputStream input = new FileInputStream(uploadarquivo);
            System.err.println(ftp.storeFile(uploadarquivo.toString(), input));
            /*
             try {
                if(ftp.storeFile(, input)){
                    System.err.println("armazenado");
                }else{
                    System.err.println("nao armazenado");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(null, "erro ao armazenar");
            }
             */

            FTPFile[] files = ftp.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        System.out.println("File >>" + files[1].getName() + "size >>" + files[i].getSize());
                    }
                }
            }
            ftp.logout();
            System.out.println("saiu da conta");
            ftp.disconnect();
            System.out.println("desconectou");
        } catch (Exception e) {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException f) {
                    // do nothing  
                }
            }

            e.printStackTrace();
        }
    }

    public static Connection getConexao() throws SQLException {
        java.sql.Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/aejn";
            conexao = DriverManager.getConnection(url, "root", "bruno");
            conexao.close();
            System.err.println("conectou direto");
        } catch (Exception e) {
            if (e.getMessage().contains("aejn")) {
                String url1 = "jdbc:mysql://localhost:3306/";
                conexao = DriverManager.getConnection(url1, "root", "bruno");
                conexao.createStatement().execute("CREATE USER 'adminaejn'@'%' IDENTIFIED BY 'aejnconection';");
                conexao.createStatement().execute("GRANT ALL PRIVILEGES ON aejn.* TO 'adminaejn'@'%';");
                conexao.createStatement().execute("FLUSH PRIVILEGES;");
                conexao.close();
                System.err.println("criou usuario");

                String url = "jdbc:mysql://localhost:3306/";
                conexao = DriverManager.getConnection(url, "adminaejn", "aejnconection");
                conexao.createStatement().execute("create database aejn");
                conexao.close();
                System.err.println("criou database");

                String url2 = "jdbc:mysql://localhost:3306/aejn";
                conexao = DriverManager.getConnection(url2, "adminaejn", "aejnconection");
                conexao.createStatement().execute("CREATE TABLE `usuario` (\n"
                        + "	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,\n"
                        + "	`codigo` VARCHAR(50) NOT NULL,\n"
                        + "	`senha` VARCHAR(50) NOT NULL,\n"
                        + "	`tipo` VARCHAR(50) NOT NULL,\n"
                        + "	PRIMARY KEY (`id`)\n"
                        + ")\n"
                        + "COLLATE='latin1_swedish_ci'\n"
                        + "ENGINE=InnoDB;");
                conexao.close();
                System.err.println("criou tabela");

                String url3 = "jdbc:mysql://localhost:3306/aejn";
                conexao = DriverManager.getConnection(url3, "adminaejn", "aejnconection");
                conexao.createStatement().execute("INSERT INTO `aejn`.`usuario` (`codigo`, `senha`, `tipo`) VALUES ('adminaejn', 'aejnconection', 'ADMINISTRADOR')");
                conexao.close();
                System.err.println("inseriu usario");
            } else {
                e.printStackTrace();
                Msg.alerta(null, "Erro ao conectar\n Erro: " + e.getMessage());
            }
        }
        return conexao;
    }

    public static void tanscomponente(JFrame frm) {
        frm.setUndecorated(true);
//        AWTUtilities.setWindowOpaque(frm, false);
    }

    public static void tanscomponenteDialog(JDialog frm) {
        frm.setUndecorated(true);
//        AWTUtilities.setWindowOpaque(frm, false);
    }

    public static void copyFile(File source, File destination) throws IOException {

        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

    public static byte[] converteFileToByteArray(String arquivo) throws FileNotFoundException, IOException {
        File f = new File(arquivo);
        FileInputStream fs = new FileInputStream(f);
        byte[] byt = new byte[(int) f.length()];
        for (int i = 0; i < f.length(); i++) {
            byt[i] = (byte) fs.read();
        }
        return byt;
    }

    public static void converteByteArrayToFile(byte[] bytes) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(new File("Definir pasta destino"));
        fos.write(bytes);
    }

    public static String getTempo() {
        Date data = new Date();
        int hora = data.getHours();
        int minit = data.getMinutes();
        int segund = data.getSeconds();
        String nome = hora + "" + minit + "" + segund;
        return nome;
    }

    public static String longToMB(long size) {
        double kb = size / 1024;
        double mb = kb / 1024;
        String format = new DecimalFormat("#0.##").format(mb) + " Mb";
        return format;
    }

    public static String substituiCaracter(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    public static String substituiCaracterCNPJ(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos - 1);
    }

    public static String convertDouble(double valor) {
        return new DecimalFormat("#0.00").format(valor);
    }

    public static String convertDouble(double valor, String pattern) {
        return new DecimalFormat(pattern).format(valor);
    }

    public static void maximizar(JDialog janela) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        screen = new Dimension((int) screen.getWidth(), (int) screen.getHeight() - 30);
        janela.setSize(screen);
        janela.setLocationRelativeTo(null);
    }

    public static void maximizar(JFrame janela) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        screen = new Dimension((int) screen.getWidth(), (int) screen.getHeight() - 30);
        janela.setSize(screen);
        janela.setLocationRelativeTo(null);
    }

    public static void configTabela(JTable tabela, int[] largura) {
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < largura.length; i++) {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(largura[i]);

            if (largura[i] <= 0) {
                tabela.getColumnModel().getColumn(i).setMinWidth(0);
                tabela.getColumnModel().getColumn(i).setMaxWidth(0);
            }
        }
    }

    public static Date convertData(String date) {
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException ex) {
            //Msg.erro(null, "Erro ao converter data!!!\n\n" + ex.getMessage());
        }
        return data;
    }

    public static Date convertDataSalvar(String date) {
        Date data = null;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException ex) {
            //Msg.erro(null, "Erro ao converter data!!!\n\n" + ex.getMessage());
        }
        return data;
    }

    public static Date convertDataTime(String date) {
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();  //Msg.erro(null, "Erro ao converter data!!!\n\n" + ex.getMessage());
        }
        return data;
    }

    public static String convertDateTime(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }

    public static String convertData(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static String convertData(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String getExtFile(String file) {
        String arr[] = file.split("[\\.]");

        if (arr.length < 2) {
            return null;
        }
        return arr[arr.length - 1].trim().toLowerCase();
    }

    public static void deleteFile(String srcFile) {
        File file = new File(srcFile);

        if (file.isFile()) {
            file.delete();
        }
    }

    public static void deleteTree(String srcFile) {
        File file = new File(srcFile);

        if (file.isFile()) {
            file.delete();
        } else {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteTree(files[i].getAbsolutePath());
            }
        }
    }

    public static boolean isEmail(String email) {
        String regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{1,3})+$";
        return email.matches(regexp);
    }

    public static void setButtonCursor(JButton button) {
        button.setCursor(Cursor.getPredefinedCursor(12));
    }

    public static void setShortTable(JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        TableRowSorter<TableModel> sorter;
        sorter = new TableRowSorter<TableModel>(modelo);
        tabela.setRowSorter(sorter);
    }

    public static void setTableCursor(JTable tabela) {
        tabela.setCursor(Cursor.getPredefinedCursor(12));
    }

    public static boolean isCPF(String cpf) {
        boolean retorno = true;

        if (cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            try {
                cpf = cpf.replace(".", "");
                cpf = cpf.replace("-", "");

                String cpfV = "";
                for (int i = 0; i <= 9; i++) {
                    cpfV = "";
                    for (int j = 1; j <= 11; j++) {
                        cpfV += i;
                    }
                    if (cpfV.equals(cpf)) {
                        return false;
                    }
                }

                int digito[] = new int[11];
                int dvInformado = Integer.parseInt(cpf.substring(9, 11));

                for (int i = 0; i <= 8; i++) {
                    digito[i] = Integer.parseInt(cpf.substring(i, i + 1));
                }

                int posicao = 10;
                int soma = 0;

                for (int i = 0; i <= 8; i++) {
                    soma = (soma + digito[i] * posicao);
                    posicao = (posicao - 1);
                }

                digito[9] = (soma % 11);

                if (digito[9] < 2) {
                    digito[9] = 0;
                } else {
                    digito[9] = (11 - digito[9]);
                }

                posicao = 11;
                soma = 0;

                for (int i = 0; i <= 9; i++) {
                    soma = (soma + digito[i] * posicao);
                    posicao = (posicao - 1);
                }

                digito[10] = (soma % 11);

                if (digito[10] < 2) {
                    digito[10] = 0;
                } else {
                    digito[10] = (11 - digito[10]);
                }

                int dv = (digito[9] * 10 + digito[10]);

                if (dv != dvInformado) {
                    retorno = false;
                }
            } catch (Exception e) {
                retorno = false;
            }
        } else {
            retorno = false;
        }
        return retorno;
    }

    public static boolean isCNPJ(String cnpj) {
        boolean retorno = true;

        if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
            return false;
        }

        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");

        int a[] = new int[14];
        int x = 0, b = 0;
        int c[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < 12; i++) {
            a[i] = Integer.parseInt(cnpj.substring(i, i + 1));
            b += (a[i] * c[i + 1]);
        }

        if ((x = b % 11) < 2) {
            a[12] = 0;
        } else {
            a[12] = (11 - x);
        }

        b = 0;
        for (int y = 0; y < 13; y++) {
            b += (a[y] * c[y]);
        }

        if ((float) (x = b % 11) < 2) {
            a[13] = 0;
        } else {
            a[13] = (11 - x);
        }

        int pos12 = Integer.parseInt(cnpj.substring(12, 13));
        int pos13 = Integer.parseInt(cnpj.substring(13, 14));

        if ((pos12 != a[12]) || (pos13 != a[13])) {
            retorno = false;
        }

        return retorno;
    }
    private GraphicsDevice device;

    public void setFullScreen() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = environment.getDefaultScreenDevice();

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);

        device.setFullScreenWindow(frame);

//       if (displayMode != null && device.isDisplayChangeSupported()) {
//          setDisplayMode(displayMode);
//          // fix for mac os x
//          frame.setSize(displayMode.getWidth(), displayMode.getHeight());
//       }
        frame.createBufferStrategy(2);
//       sleep(500);
    }

    public static boolean isCodigoBarra(String barCode) {
        int digit;
        int calculated;
        String ean;
        String checkSum = "131313131313";
        int sum = 0;

        if (barCode.length() == 8 || barCode.length() == 13) {
            digit = Integer.parseInt("" + barCode.charAt(barCode.length() - 1));
            ean = barCode.substring(0, barCode.length() - 1);
            for (int i = 0; i <= ean.length() - 1; i++) {
                sum += (Integer.parseInt("" + ean.charAt(i))) * (Integer.parseInt("" + checkSum.charAt(i)));
            }
            calculated = 10 - (sum % 10);
            return (digit == calculated);
        } else {
            return false;
        }
    }

    public static void maximumAndMinimumColumnSizeTable(JTable e, int column, int max, int min) {
        e.getColumnModel().getColumn(column).setMinWidth(min);
        e.getColumnModel().getColumn(column).setMaxWidth(max);
    }

    public static String getNome() {
        Date data = new Date();
        int year = data.getYear();
        int mount = data.getMonth();
        int day = data.getDay();
        int hours = data.getHours();
        int minutes = data.getMinutes();
        int secundes = data.getSeconds();
        return "" + year + "" + mount + "" + day + "" + hours + "" + minutes + "" + secundes;
    }

    public static String getErro(Throwable exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        return (sw.toString());
    }
    
    // realizar concatenação dos documentos em determinada lista para texto compactado em informação relevante
    // usado para impressão de atas e tambem nas descrições de cargo
    public static String concatenar(List<String> s, List<Get_setor> a) {

        String resultado = "";
       
        if ("NA".equals(s.get(0)) || s.get(0).equals("null")) {
            resultado = " NA";
        } else {
                    int quantidade = 0;
                    int c,d,e;
                for(int i = 0;i < s.size();i++){
                    if(i> 0){
                        c = Integer.parseInt(a.get(i).getNumero().trim());
                        d = Integer.parseInt(a.get(i-1).getNumero().trim());
                        e = c-d;
                        if(e != 1){
                                if(!a.get(i).getTitulo().equals(a.get(i-1).getTitulo())){
                                    if(resultado.contains(s.get(i-1))){
                                        resultado +="\n"+s.get(i);
                                    }else{
                                        resultado += " e "+s.get(i-1)+"\n"+s.get(i);
                                    }
                                }else{
                                    if(resultado.contains(s.get(i-1))){
                                        resultado +="\n"+s.get(i);
                                    }else{
                                        resultado += " ha "+s.get(i-1)+"\n"+s.get(i);
                                    }
                                }
                                System.out.println(c+"-"+d+"="+e);
                        }else{
                            if(a.get(i).getTitulo().equals(a.get(i-1).getTitulo()) && s.size() == i+1){
                                resultado +=" ha "+s.get(i);
                            }
                        }
                        }else{
                            resultado += "\n"+s.get(i);                        
                        }
                    
                e = 0;
                }
        }
        return resultado;
    }

    public static String Sistema_operacional() {
        String s = System.getProperty("os.name");
        String a;
        if (s.contains("Windows")) {
            a = System.getProperty("user.dir");
        } else {
            a = System.getProperty("user.home");
        }
        return a;
    }

    /**
     *
     * public static void concatenar(List<BigInteger> lista2,List<String>
     * lista){ List<BigInteger> ids = lista2; List<String> concat = lista; int
     * anterior = Integer.parseInt(ids.get(0).toString()); List<String>
     * resultado = new ArrayList<>(); for(int i = 0; i<concat.size();i++){ if(i
     * == 0){ resultado.add(concat.get(i)); System.out.println("sa"+resultado);
     * System.out.println("0"); }else{ System.out.println("1"); int convert_int
     * = Integer.parseInt(ids.get(i).toString()); if(convert_int-1 == anterior
     * ){ anterior = Integer.parseInt(ids.get(i).toString()); }else{
     * resultado.add(concat.get(i)); } } if(i == concat.size()-1){
     * resultado.add(concat.get(i)); } }
     * System.out.println("resultado"+resultado); } @return
     */

    public static Connection open() {
        java.sql.Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.100.37:3306/2019";
            
            //String url = "jdbc:mysql://192.168.100.37:3306/bd_teste";
            conexao = DriverManager.getConnection(url, "root", "conecttemp");

            //String url = "jdbc:mysql://localhost:3306/aejn";
            //conexao = DriverManager.getConnection(url, "root", "bruno");
            //String url = "jdbc:mysql://192.168.100.204:3306/aejn";
            //conexao = DriverManager.getConnection(url, "Bruno", "senha@1");
        } catch (Exception e) {
            e.printStackTrace();
            Msg.alerta(null, "Erro no relatorio\nErro: " + e.getMessage());
        }
        return conexao;
    }
    
    
    

  
   
  
  public void relatorio_estatistico(String ano){
      DocumentoDao doc = new DocumentoDao();
      int i = 0;
      List<String> fevereiro = new ArrayList<>();
      List<String> marco = new ArrayList<>();
      List<String> abril = new ArrayList<>();
      List<String> maio = new ArrayList<>();
      List<Setor> lista = new SetorDao().impressao();
      
      for (Setor setor : lista) {
          System.out.println(setor.getNomeSetor());
        for(int j = 2; j <=5; j++){
         //   System.out.println(j);
          i = doc.quantidade_documento(setor.getId(), "0"+j, ano);
          
          
         if(j == 2){
             if(i == 0){ 
                fevereiro.add(String.valueOf(i+"-v"));
             }else{
                fevereiro.add(String.valueOf(i+"-f"));
             }
         //fevereiro.add(String.valueOf(i));
         }
         if(j == 3){
             if(i == 0){ 
                marco.add(String.valueOf(i+"-v"));
             }else{
                marco.add(String.valueOf(i+"-f"));
             }
            // marco.add(String.valueOf(i));
         }
         if(j == 4){
             if(i == 0){ 
                abril.add(String.valueOf(i+"-v"));
             }else{
                abril.add(String.valueOf(i+"-f"));
             }
            // abril.add(String.valueOf(i));
         }
         if(j == 5){
             if(i == 0){ 
                maio.add(String.valueOf(i+"-v"));
             }else{
                maio.add(String.valueOf(i+"-f"));
             }
            // maio.add(String.valueOf(i));
         }
        }
      }
            
            int cont = fevereiro.size();
            int what=0;
            while(what < cont){
                    System.out.println(fevereiro.get(what)+"/"+marco.get(what)+"/"+abril.get(what)+"/"+maio.get(what));
                what++;
            }
            System.out.println("mes de fevereiro \n"+fevereiro);
            System.out.println("mes de marco \n"+marco);
            System.out.println("mes de abril \n"+abril);
            System.out.println("mes de maio \n"+maio);
      
            new Analise_critica(fevereiro,marco,abril,maio,Utils.Sistema_operacional());
  }
}
