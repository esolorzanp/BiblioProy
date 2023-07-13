package dao;

import db.ConexionDB;
import models.Editorial;

import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {
    public static boolean add(Editorial editorialX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b;

        String sql = ("INSERT INTO EDITORIALES VALUES (NULL"
                + ',' + '\'' + editorialX.getEditorial() + '\''
                + ',' + (editorialX.getIdPais() != -1 ? editorialX.getIdPais() : "NULL")
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Editorial editorialX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b;

        String sql = "UPDATE EDITORIALES SET "
                + "EDITORIAL=" + '\'' + editorialX.getEditorial() + '\''
                + ',' + "IDPAIS=" + (editorialX.getIdPais() != -1 ? editorialX.getIdPais() : "NULL")
                + " WHERE ID = " + editorialX.getId();

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean delete(String editorial) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b;

        String sql = "DELETE FROM EDITORIALES WHERE EDITORIAL = '" + editorial + '\'';

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean existWithWhereEditorial(String editorialX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        con.consultar("SELECT * FROM EDITORIALES WHERE EDITORIAL = '" + editorialX + '\'');
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

    public static boolean existWithWhereId(int idX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        con.consultar("SELECT * FROM EDITORIALES WHERE ID = '" + idX + '\'');
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

    public static Editorial getBy(String editorialX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Editorial p = new Editorial();

        con.consultar("SELECT * FROM EDITORIALES " +
                " LEFT JOIN PAISES ON PAISES.ID = IDPAIS " +
                "WHERE EDITORIAL = '" + editorialX + '\'');
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                p.setEditorial(con.rs.getString("EDITORIAL"));
                p.setIdPais(con.rs.getInt("IDPAIS") != 0 ? con.rs.getInt("IDPAIS") : -1);
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
        return p;
    }

    public static Editorial getBy(int idX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Editorial p = new Editorial();

        con.consultar("SELECT * FROM EDITORIALES " +
                " LEFT JOIN PAISES ON PAISES.ID = IDPAIS " +
                "WHERE EDITORIALES.ID = " + idX);
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                p.setEditorial(con.rs.getString("EDITORIAL"));
                p.setIdPais(con.rs.getInt("IDPAIS") != 0 ? con.rs.getInt("IDPAIS") : -1);
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
        return p;
    }

    public static List<Editorial> getAll() {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Editorial> personas = new ArrayList<>();

        con.consultar("SELECT * FROM EDITORIALES " +
                "LEFT JOIN PAISES ON PAISES.ID = IDPAIS " +
                "ORDER BY EDITORIAL");
        try {
            while (con.rs.next()) {
                Editorial p = new Editorial();
                p.setId(con.rs.getInt("ID"));
                p.setEditorial(con.rs.getString("EDITORIAL"));
                p.setIdPais(con.rs.getInt("IDPAIS") != 0 ? con.rs.getInt("IDPAIS") : -1);
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

    public static List<Editorial> getAllWithWhereEditorial(String editorialX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Editorial> personas = new ArrayList<>();

        String sql = "SELECT * FROM EDITORIALES " +
                "LEFT JOIN PAISES ON PAISES.ID = IDPAIS " +
                "WHERE EDITORIAL LIKE '%" + editorialX + "%' " +
                "ORDER BY EDITORIAL";
        con.consultar(sql);
        System.out.println(sql);
        try {
            while (con.rs.next()) {
                Editorial p = new Editorial();
                p.setId(con.rs.getInt("ID"));
                p.setEditorial(con.rs.getString("EDITORIAL"));
                p.setIdPais(con.rs.getInt("IDPAIS") != 0 ? con.rs.getInt("IDPAIS") : -1);
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

    public static void main(String[] args) {
    }
}
