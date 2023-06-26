package dao;

import db.ConexionDB;
import models.Pais;

import java.util.ArrayList;
import java.util.List;

public class PaisDAO {
    public static boolean add(Pais p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = ("INSERT INTO PAISES VALUES (NULL"
                + ',' + '\'' + p.getPais() + '\''
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Pais p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = "UPDATE PAISES SET "
                + "PAIS=" + '\'' + p.getPais() + '\''
                + " WHERE ID = " + p.getId();

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean delete(String pais) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        String sql = "DELETE FROM PAISES WHERE PAIS = '" + pais + '\'';

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean exist(String pais) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Pais p = null;
        boolean b = true;

        con.consultar("SELECT * FROM PAISES WHERE PAIS = '" + pais + '\'');
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

    public static Pais getBy(String pais) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Pais p = new Pais();

        con.consultar("SELECT * FROM PAISES " +
                "WHERE PAIS = '" + pais + '\'');
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                p.setPais(con.rs.getString("PAIS"));
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

    public static Pais getBy(int id) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Pais p = new Pais();

        con.consultar("SELECT * FROM PAISES " +
                "WHERE PAISES.ID = " + id);
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                p.setPais(con.rs.getString("PAIS"));
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

    public static List<Pais> getAll() {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Pais> personas = new ArrayList<>();

        con.consultar("SELECT * FROM PAISES ORDER BY PAIS");
        try {
            while (con.rs.next()) {
                Pais p = new Pais();
                p.setId(con.rs.getInt("ID"));
                p.setPais(con.rs.getString("PAIS"));
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
        String paisx = "Colombia";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));

        paisx = "Alemania";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));

        paisx = "España";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));

        paisx = "Estados Unidos";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));


        paisx = "Argentina";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));

        paisx = "hURUGUAI";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));

        Pais p1 = PaisDAO.exist(paisx) ? PaisDAO.getBy(paisx) : null;
        System.out.println("Pais " + paisx + ':' + p1);
        p1.setPais("Uruguay");
        System.out.println("Modifiar " + p1 + ':' + PaisDAO.modify(p1));

        paisx = "espana";
        System.out.println("Encontrar por pais a " + paisx + ':' + PaisDAO.exist(paisx));

        paisx = "Alemania";
        Pais p2 = PaisDAO.exist(paisx) ? PaisDAO.getBy(paisx) : null;
        System.out.println("Encontrar por pais " + paisx + " por id" + ':' + p2);

        paisx = "Tierra";
        System.out.println("Existe " + paisx + ':' + PaisDAO.exist(paisx));
        if (!PaisDAO.exist(paisx))
            System.out.println("Agregar " + paisx + ':' + PaisDAO.add(new Pais(paisx)));
        Pais p3 = PaisDAO.exist(paisx) ? PaisDAO.getBy(paisx) : null;
        System.out.println("Eliminar " + p3 + ':' + PaisDAO.delete(paisx));
        System.out.println("Encontrar por pais a " + paisx + ':' + PaisDAO.exist(paisx));


        List<Pais> paises = PaisDAO.getAll();
        for (Pais p : paises) {
            System.out.println(p);
        }
    }

}
