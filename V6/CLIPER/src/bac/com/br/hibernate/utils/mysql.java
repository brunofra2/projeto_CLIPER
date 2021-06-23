/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.utils;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author bruno+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
public class mysql {
     private Connection openedCon;
    private ResultSet RSet;
    private PreparedStatement stmt;
    String host = "192.168.100.37";
    String user = "adminaejn";
    String pass = "aejnconection";
    String port = "3306";
    String base = "aejn";

    //METODO ABRE CONEXÃO
    public boolean open() throws SQLException {
        boolean cont = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + base;
            this.openedCon = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            cont = false;
            throw new SQLException("ERRO DE CONEXÃO,\n relate o ocorrido ao administrador do sistema.\n" + e.getMessage());
        }
        return cont;
    }

    //METODO FECHA CONEXAO
    public void close() {
        try {
            this.openedCon.close();
            this.stmt.close();
            this.RSet.close();
        } catch (Exception e) {
        }
    }

    public void prepare(String SQL) throws SQLException {
        this.stmt = this.openedCon.prepareStatement(SQL);
    }

    public void execute() throws SQLException {
        this.stmt.execute();
    }

    public void setString(int param, String var) throws SQLException {
        this.stmt.setString(param, var);
    }

    public void setInt(int param, int var) throws SQLException {
        this.stmt.setInt(param, var);
    }

    public void setShort(int param, short var) throws SQLException {
        this.stmt.setShort(param, var);
    }

    public void setByte(int param, byte var) throws SQLException {
        this.stmt.setByte(param, var);
    }

    public void setBytes(int param, byte[] var) throws SQLException {
        this.stmt.setBytes(param, var);
    }

    public void setLong(int param, long var) throws SQLException {
        this.stmt.setLong(param, var);
    }

    public void setDouble(int param, double var) throws SQLException {
        this.stmt.setDouble(param, var);
    }

    public void setFloat(int param, float var) throws SQLException {
        this.stmt.setFloat(param, var);
    }

    public void setBoolean(int param, boolean var) throws SQLException {
        this.stmt.setBoolean(param, var);
    }

    public void setDate(int param, Date var) throws SQLException {
        int day = var.getDate();
        int month = var.getMonth();
        int year = var.getYear();
        this.stmt.setDate(param, new java.sql.Date(year, month, day));
    }

    public void setObject(int param, Object var) throws SQLException {
        this.stmt.setObject(param, var);
    }

    public void setBinaryStream(int param, InputStream stream, int len) throws SQLException {
        this.stmt.setBinaryStream(param, stream, len);
    }

    public void setAsciiStream(int param, InputStream stream, int len) throws SQLException {
        this.stmt.setAsciiStream(param, stream, len);
    }

    public void setNull(int param, int sqlType) {
        this.setNull(param, sqlType);
    }

    public void clearParameters() throws SQLException {
        this.stmt.clearParameters();
    }

    public void executeQuery() throws SQLException {
        this.RSet = this.stmt.executeQuery();
    }

    public ResultSet getRS() {
        return this.RSet;
    }

    public Connection getConnection() {
        return this.openedCon;
    }
}
