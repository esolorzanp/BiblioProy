package bussines;

import db.ConexionDB;
import models.Editorial;

import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {
    public static boolean add(Editorial p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = ("INSERT INTO EDITORIALES VALUES (NULL"
                + ',' + '\'' + p.getEditorial() + '\''
                + ',' + (p.getIdPais() != -1 ? p.getIdPais() : "NULL")
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Editorial p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = "UPDATE EDITORIALES SET "
                + "EDITORIAL=" + '\'' + p.getEditorial() + '\''
                + ',' + "IDPAIS=" + (p.getIdPais() != -1 ? p.getIdPais() : "NULL")
                + " WHERE ID = " + p.getId();

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
        boolean b = true;

        String sql = "DELETE FROM EDITORIALES WHERE EDITORIAL = '" + editorial + '\'';

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean exist(String editorial) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Editorial p = null;
        boolean b = true;

        con.consultar("SELECT * FROM EDITORIALES WHERE EDITORIAL = '" + editorial + '\'');
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

    public static Editorial getBy(String editorial) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Editorial p = new Editorial();

        con.consultar("SELECT * FROM EDITORIALES " +
                " LEFT JOIN PAISES ON PAISES.ID = IDPAIS " +
                "WHERE EDITORIAL = '" + editorial + '\'');
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

    public static Editorial getBy(int id) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Editorial p = new Editorial();

        con.consultar("SELECT * FROM EDITORIALES " +
                " LEFT JOIN PAISES ON PAISES.ID = IDPAIS " +
                "WHERE EDITORIALES.ID = " + id);
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

    public static void main(String[] args) {
        Editorial e1 = new Editorial("Voluntad");
        Editorial e2 = new Editorial("Omega");
        Editorial e3 = new Editorial("Oveja Negra");


        System.out.println(e1);
        System.out.println("Existe " + e1 + ':' + EditorialDAO.exist(e1.getEditorial()));

        if (!EditorialDAO.exist(e1.getEditorial()))
            System.out.println("Agregar " + e1 + ':' + EditorialDAO.add(e1));

        System.out.println("Existe " + e2 + ':' + EditorialDAO.exist(e2.getEditorial()));

        if (!EditorialDAO.exist(e2.getEditorial()))
            System.out.println("Agregar " + e2 + ':' + EditorialDAO.add(e2));

        System.out.println("Existe " + e3 + ':' + EditorialDAO.exist(e3.getEditorial()));

        if (!EditorialDAO.exist(e3.getEditorial()))
            System.out.println("Agregar " + e3 + ':' + EditorialDAO.add(e3));

        String edit = "Oveja azul";
        Editorial e4 = new Editorial(edit);
        EditorialDAO.add(e4);
        if (EditorialDAO.exist(edit)) {
            e4 = EditorialDAO.getBy(edit);
            System.out.println(e4);
            e4.setEditorial("Lobo negro");
            System.out.println("Modificar " + e4 + ':' + EditorialDAO.modify(e4));
        }

        Editorial e5 = EditorialDAO.getBy(5);
        System.out.println("Eliminar " + e5 + ':' + EditorialDAO.delete(e5.getEditorial()));

        List<Editorial> es = EditorialDAO.getAll();
        for (Editorial e : es) {
            System.out.println(e);
        }
    }

}
