/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.BackupDao;
import bac.com.br.hibernate.Dao.TreinamentoDao;
import bac.com.br.hibernate.Dao.UsuarioDao;
import bac.com.br.hibernate.entidade.Usuario;
import bac.com.br.hibernate.entidade.Backup;
import bac.com.br.hibernate.utils.Msg;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 *
 * @author bruno
 */
public class Realiza_backup extends JDialog {
    private final Tela_login pai;
    protected String usuario;
    private JFileChooser arquivo = null;
    String caminho;
    public Realiza_backup(Tela_login parent, boolean modal) {
        this.pai = parent;
        this.Realizar_backup();
    }
    public void Realizar_backup() {
        try {
            arquivo = new JFileChooser("C:\\backup");
            arquivo.setSelectedFile(new File("backup-" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".sql"));
            caminho = arquivo.getSelectedFile().getAbsolutePath();
            ProcessBuilder pb = new ProcessBuilder("mysqldump", "--default-character-set=latin1", "--user=Bruno", "--password=senha@1", "--host=192.168.100.204", "aejn", "--result-file=" + caminho);
            pb.start();
            Process p = pb.start();
            p.waitFor();
            this.salvar();
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao realizar backup\n" + e.getMessage());
        }
    }

    public void salvar() {
        Backup p = new Backup();
        p.setCaminho(caminho);
        List<Date> d = new TreinamentoDao().curdate();
        p.setDataBackup(d.get(0));
        Usuario u = new UsuarioDao().seleciona(pai.usuario);
        p.setUsuarioId(u);
        try {
            new BackupDao().inserir(p);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao inserir backup \n"+e.getMessage());
        }
    }
}
