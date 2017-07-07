package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jorge Silva Borda
 */
public class Conexion {

    private final String servidor;
    private final String baseDato;
    private final String usuario;
    private final String password;
    private Connection conn;

    public Conexion(String servidor, String baseDato, String usuario, String password) {
        this.servidor = servidor;
        this.baseDato = baseDato;
        this.usuario = usuario;
        this.password = password;
    }

    public boolean testConexion() {
        return this.conn != null;
    }

    public boolean abrirConexion() {
        try {
            Class.forName("com.jdbc.mysql.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysq://" + servidor + "/" + baseDato, usuario, password);
            return this.conn != null;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se pudo abrir la conexión.");
            System.out.println(ex);
            return false;
        }
    }

    public void cerrarConexion() {
        try {
            if (!this.conn.isClosed()) {
                this.conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexión.");
            System.out.println(ex);
        }
    }

    public ResultSet ejecutarQuery(String query) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (Exception ex) {
            System.out.println("No se pudo ejecutar la query.");
            System.out.println(ex);
            return null;
        }
    }

    public void ejecutar(String query) {
        try {
            Statement st = this.conn.createStatement();
            st.execute(query);
        } catch (SQLException ex) {
            System.out.println("No se pudo ejecutar la sentencia.");
            System.out.println(ex);
        }
    }
}
