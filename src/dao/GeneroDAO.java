package dao;

import db.ConexionDB;
import models.Genero;

import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    public static boolean add(Genero x) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = ("INSERT INTO GENEROS VALUES (NULL"
                + ',' + '\'' + x.getGenero() + '\''
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Genero x) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = "UPDATE GENEROS SET "
                + "GENERO=" + '\'' + x.getGenero() + '\''
                + " WHERE ID = " + x.getId();

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean delete(String generox) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        String sql = "DELETE FROM GENEROS WHERE GENERO = '" + generox + '\'';

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean exist(String generoX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Genero x = null;
        boolean b = true;

        con.consultar("SELECT * FROM GENEROS WHERE GENERO = '" + generoX + '\'');
        try {
            if (!con.rs.next()) {
                b = false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            ex.printStackTrace();
        } finally {
            con.cerrarRs();
            con.cerrarStmt();
            con.cerrarConexion();
        }
        return b;
    }

    public static Genero getByWithPersona(int generox) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Genero x = new Genero();

        con.consultar("SELECT * FROM GENEROS " +
                "WHERE GENEROS = '" + generox + '\'');
        try {
            if (con.rs.next()) {
                x.setId(con.rs.getInt("ID"));
                x.setGenero(con.rs.getString("GENERO"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            ex.printStackTrace();
        } finally {
            con.cerrarRs();
            con.cerrarStmt();
            con.cerrarConexion();
        }
        return x;
    }

    public static Genero getBy(String generox) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Genero x = new Genero();

        con.consultar("SELECT * FROM GENEROS " +
                "WHERE GENERO = '" + generox + '\'');
        try {
            if (con.rs.next()) {
                x.setId(con.rs.getInt("ID"));
                x.setGenero(con.rs.getString("GENERO"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            ex.printStackTrace();
        } finally {
            con.cerrarRs();
            con.cerrarStmt();
            con.cerrarConexion();
        }
        return x;
    }

    public static Genero getBy(int idx) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Genero x = new Genero();

        con.consultar("SELECT * FROM GENEROS " +
                "WHERE ID = '" + idx + '\'');
        try {
            if (con.rs.next()) {
                x.setId(con.rs.getInt("ID"));
                x.setGenero(con.rs.getString("GENERO"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            ex.printStackTrace();
        } finally {
            con.cerrarRs();
            con.cerrarStmt();
            con.cerrarConexion();
        }
        return x;
    }

    public static List<Genero> getAll() {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Genero> personas = new ArrayList<>();

        con.consultar("SELECT * FROM GENEROS ORDER BY GENERO");
        try {
            while (con.rs.next()) {
                Genero p = new Genero();
                p.setId(con.rs.getInt("ID"));
                p.setGenero(con.rs.getString("GENERO"));
                personas.add(p);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            ex.printStackTrace();
        } finally {
            con.cerrarRs();
            con.cerrarStmt();
            con.cerrarConexion();
        }
        return personas;
    }

}
