package dao;

import db.ConexionDB;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public static boolean add(Usuario p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = ("INSERT INTO USUARIOS VALUES (NULL"
                + ',' + '\'' + p.getUsuario() + '\''
                + ',' + '\'' + p.getNombres() + '\''
                + ',' + '\'' + p.getApellidos() + '\''
                + ',' + '\'' + p.getEmail() + '\''
                + ',' + '\'' + p.getPassword() + '\''
                + ',' + '\'' + p.getPerfil() + '\''
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Usuario p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = "UPDATE USUARIOS SET "
                + "USUARIO=" + '\'' + p.getUsuario() + '\''
                + ", NOMBRES=" + '\'' + p.getNombres() + '\''
                + ", APELLIDOS=" + '\'' + p.getApellidos() + '\''
                + ", EMAIL=" + '\'' + p.getEmail() + '\''
                + ", PASSWORD=" + '\'' + p.getPassword() + '\''
                + ", TIPO=" + '\'' + p.getPerfil() + '\''
                + " WHERE ID = " + p.getId();

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean delete(String usuarioX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        String sql = "DELETE FROM USUARIOS WHERE USUARIO = '" + usuarioX + '\'';

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean existWithWhereUsuario(String usuarioX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Usuario p = null;
        boolean b = true;

        con.consultar("SELECT * FROM USUARIOS WHERE USUARIO = '" + usuarioX + '\'');
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

    public static boolean existWithWhereEmail(String emailX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Usuario p = null;
        boolean b = true;

        con.consultar("SELECT * FROM USUARIOS WHERE EMAIL = '" + emailX + '\'');
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
        Usuario p = null;
        boolean b = true;

        con.consultar("SELECT * FROM USUARIOS WHERE ID = " + idX);
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

    public static Usuario getBy(String usuarioX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Usuario p = new Usuario();

        con.consultar("SELECT * FROM USUARIOS " +
                "WHERE USUARIO = '" + usuarioX + '\'');
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                p.setUsuario(con.rs.getString("USUARIO"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setEmail(con.rs.getString("EMAIL"));
                p.setPassword(con.rs.getString("PASSWORD"));
                String sTipo = con.rs.getString("TIPO");
                p.setPerfil(sTipo.charAt(0));
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

    public static Usuario getBy(int idX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Usuario p = new Usuario();

        con.consultar("SELECT * FROM USUARIOS " +
                "WHERE USUARIOS.ID = " + idX
        );
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                p.setUsuario(con.rs.getString("USUARIO"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setEmail(con.rs.getString("EMAIL"));
                p.setPassword(con.rs.getString("PASSWORD"));
                String sTipo = con.rs.getString("TIPO");
                p.setPerfil(sTipo.charAt(0));
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

    public static List<Usuario> getAll() {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Usuario> personas = new ArrayList<>();

        con.consultar("SELECT * FROM USUARIOS ORDER BY USUARIO");
        try {
            while (con.rs.next()) {
                Usuario p = new Usuario();
                p.setId(con.rs.getInt("ID"));
                p.setUsuario(con.rs.getString("USUARIO"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setEmail(con.rs.getString("EMAIL"));
                p.setPassword(con.rs.getString("PASSWORD"));
                String sTipo = con.rs.getString("TIPO");
                p.setPerfil(sTipo.charAt(0));
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

    public static List<Usuario> getAllWithWhereUsuario(String usuarioX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Usuario> personas = new ArrayList<>();

        con.consultar("SELECT * FROM USUARIOS " +
                "WHERE USUARIO = '%" + usuarioX + "%' " +
                "ORDER BY USUARIO"
        );
        try {
            while (con.rs.next()) {
                Usuario p = new Usuario();
                p.setId(con.rs.getInt("ID"));
                p.setUsuario(con.rs.getString("USUARIO"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setEmail(con.rs.getString("EMAIL"));
                p.setPassword(con.rs.getString("PASSWORD"));
                String sTipo = con.rs.getString("TIPO");
                p.setPerfil(sTipo.charAt(0));
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
