package dao;

import db.ConexionDB;
import models.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    public static boolean add(Autor x) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = ("INSERT INTO AUTORES VALUES (NULL"
                + ',' + '\'' + x.getIdPersona() + '\''
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Autor x) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = "UPDATE AUTORES SET "
                + "IDPERSONA=" + '\'' + x.getIdPersona() + '\''
                + " WHERE ID = " + x.getId();

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean delete(int idPersona) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        String sql = "DELETE FROM AUTORES WHERE IDPERSONA = '" + idPersona + '\'';

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean exist(int idPersona) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Autor x = null;
        boolean b = true;

        con.consultar("SELECT * FROM AUTORES WHERE IDPERSONA = '" + idPersona + '\'');
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

    public static Autor getByWithPersona(int idPersona) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Autor x = new Autor();

        con.consultar("SELECT * FROM AUTORES " +
                " INNER JOIN PERSONAS ON PERSONAS.ID = IDAUTOR " +
                " LEFT JOIN PAISES ON PAISES.ID = PERSONAS.IDPAIS " +
                "WHERE IDPERSONA = '" + idPersona + '\'');
        try {
            if (con.rs.next()) {
                x.setId(con.rs.getInt("ID"));
                x.setIdPersona(con.rs.getInt("AUTOR"));
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

    public static Autor getBy(int id) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Autor x = new Autor();

        con.consultar("SELECT * FROM AUTORES " +
                " INNER JOIN PERSONAS ON PERSONAS.ID = IDAUTOR " +
                " LEFT JOIN PAISES ON PAISES.ID = PERSONAS.IDPAIS " +
                "WHERE ID = '" + id + '\'');
        try {
            if (con.rs.next()) {
                x.setId(con.rs.getInt("ID"));
                x.setIdPersona(con.rs.getInt("IDPERSONA"));
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

    public static List<Autor> getAll() {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Autor> personas = new ArrayList<>();

        con.consultar("SELECT * FROM AUTORES " +
                " INNER JOIN PERSONAS ON PERSONAS.ID = IDAUTOR " +
                " LEFT JOIN PAISES ON PAISES.ID = PERSONAS.IDPAIS ");
        try {
            while (con.rs.next()) {
                Autor p = new Autor();
                p.setId(con.rs.getInt("ID"));
                p.setIdPersona(con.rs.getInt("IDPERSONA"));
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
