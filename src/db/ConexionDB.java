package db;

import java.sql.*;

public class ConexionDB {
    public Connection conn;
    public Statement stmt;
    public ResultSet rs;
    public String serverDB;
    public String portDB;
    public String dataBaseDB;
    public String userDB;
    public String passDB;

    public ConexionDB() {
        conn = null;
        stmt = null;
        rs = null;
        serverDB = "";
        portDB = "";
        dataBaseDB = "";
        userDB = "";
        passDB = "";
    }

    public ConexionDB(String serverDB, String portDB, String dataBaseDB, String userDB, String passDB) {
        this.serverDB = serverDB;
        this.portDB = portDB;
        this.dataBaseDB = dataBaseDB;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public ConexionDB(String serverDB, String dataBaseDB, String userDB, String passDB) {
        this.serverDB = serverDB;
        this.portDB = "";
        this.dataBaseDB = dataBaseDB;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public boolean cargarConexion() {
        if (!serverDB.isEmpty() && !userDB.isEmpty() && !passDB.isEmpty())
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(getUrl());
                mostrarInfo("Conexión base de datos exitosa");
            } catch (Exception ex) {
                mostrarError("Problemas con la conexión a la base de datos");
                mostrarMensajeError(ex);
                return false;
            }
        else
            return false;

        return true;
    }

    public String getUrl() {
        return "jdbc:mysql://" + serverDB + (!portDB.isEmpty() ? ":" + portDB : "")
                + "/" + dataBaseDB
                + "?user=" + userDB +
                "&password=" + passDB;
    }

//    public boolean ejecutar(String q) {
//        try {
//            stmt = conn.createStatement();
//            rs = stmt.execute(q);
//            mostrarInfo("Ejecución con éxito");
//        } catch (SQLException e) {
//            mostrarError("Problemas al ejecutar el query");
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    public boolean consultar(String q) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(q);
            mostrarInfo("Ejecución query con éxito");
        } catch (Exception ex) {
            mostrarError("Problemas al ejecutar el query: {" + q + "]");
            mostrarMensajeError(ex);
            return false;
        }
        return true;
    }

    public boolean actualizar(String q) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(q);
            mostrarInfo("Actualización query con éxito");
        } catch (Exception ex) {
            mostrarError("Problema al ejecutar el query: {" + q + "]");
            mostrarMensajeError(ex);
            return false;
        }
        return true;
    }

    public boolean cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                mostrarInfo("Cierre conexion con exito");
            } catch (Exception ex) {
                mostrarError("Se presentó un problema en el cierre de la conexión");
                mostrarMensajeError(ex);
                return false;
            }
        }
        return true;
    }

    public boolean cerrarStmt() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
                mostrarInfo("Cierre stmt con exito");
            }
        } catch (Exception ex) {
            mostrarError("Problema al cerrar stmt");
            mostrarMensajeError(ex);
            return false;
        }
        return true;
    }

    public boolean cerrarRs() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
                mostrarInfo("Cierre rs con exito");
            }
        } catch (Exception ex) {
            mostrarError("Problema al cerrar rs");
            mostrarMensajeError(ex);
            return false;
        }
        return true;
    }

    public void cargarDatosConexion() {
        serverDB = "localhost";
        portDB = "";
        dataBaseDB = "BiblioProy";
        userDB = "root";
        passDB = "root";
    }

    public void mostrarMensajeError(Exception ex) {
        System.out.println("Mensaje = " + ex.getMessage());
        System.out.println("Causa = " + ex.getCause());
        ex.printStackTrace();
    }

    public void mostrarInfo(String inf) {
        System.out.println("[INFO]  " + inf);
    }

    public void mostrarError(String err) {
        System.out.println("[ERROR] " + err);
    }

    public static void main(String[] args) {
//        ConexionDB con = new ConexionDB("localhost", "baseProyDB", "root", "root");
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        con.consultar("SELECT * FROM PERSONAS");

        try {
            while (con.rs.next()) {
                System.out.println(con.rs.getInt("ID") + "," +
                        con.rs.getString("NOMBRES") + ",");
            }

        } catch (Exception ex) {
            System.out.println("Mensaje = " + ex.getMessage());
            System.out.println("Causa = " + ex.getCause());
            ex.printStackTrace();
        }

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();
    }
}
