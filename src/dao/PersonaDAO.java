package dao;

import db.ConexionDB;
import models.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    public static boolean add(Persona p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = ("INSERT INTO PERSONAS VALUES (NULL"
                + ',' + '\'' + p.getTipo() + '\''
                + ',' + '\'' + p.getIdentificacion() + '\''
                + ',' + '\'' + p.getNombres() + '\''
                + ',' + '\'' + p.getApellidos() + '\''
                + ',' + (p.getFechaNacimiento() != null ? ('\'' + p.getFechaNacimiento().toString() + '\'') : "NULL")
                + ',' + '\'' + p.getIdPais() + '\''
                + ',' + '\'' + p.getEmail() + '\''
                + ',' + '\'' + p.getSexo() + '\''
                + ',' + '\'' + p.getIdRepresentanteLegal() + '\''
                + ',' + '\'' + p.getRazonSocial() + '\''
                + ')');

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean modify(Persona p) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = false;

        String sql = "UPDATE PERSONAS SET "
                + "TIPO=" + '\'' + p.getTipo() + '\''
                + ',' + "IDENTIFICACION=" + '\'' + p.getIdentificacion() + '\''
                + ',' + "NOMBRES=" + '\'' + p.getNombres() + '\''
                + ',' + "APELLIDOS = " + '\'' + p.getApellidos() + '\''
                + ',' + "FECHANACIMIENTO = " + '\'' + p.getFechaNacimiento() + '\''
                + ',' + "IDPAIS = " + '\'' + p.getIdPais() + '\''
                + ',' + "EMAIL = " + '\'' + p.getEmail() + '\''
                + ',' + "SEXO = " + '\'' + p.getSexo() + '\''
                + ',' + "IDREPRESENTANTELEGAL = " + '\'' + p.getIdRepresentanteLegal() + '\''
                + ',' + "RAZONSOCIAL = " + '\'' + p.getRazonSocial() + '\''
                + " WHERE ID = " + p.getId();

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean delete(String identificacion) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        boolean b = true;

        String sql = "DELETE FROM PERSONAS WHERE IDENTIFICACION = " + identificacion;

        b = con.actualizar(sql);

        con.cerrarRs();
        con.cerrarStmt();
        con.cerrarConexion();

        return b;
    }

    public static boolean existWithWhereIdentificacion(String identificacion) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Persona p = null;
        boolean b = true;

        con.consultar("SELECT * FROM PERSONAS " +
                "WHERE IDENTIFICACION = '" + identificacion + '\'');
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
        Persona p = null;
        boolean b = true;

        con.consultar("SELECT * FROM PERSONAS " +
                "WHERE ID = " + idX);
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

    public static Persona getBy(String identificacion) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Persona p = new Persona();

        con.consultar("SELECT * FROM PERSONAS " +
                "WHERE IDENTIFICACION = '" + identificacion + '\'');
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                String sTipo = con.rs.getString("TIPO");
                p.setTipo(!sTipo.isEmpty() ? sTipo.charAt(0) : ' ');
                p.setIdentificacion(con.rs.getString("IDENTIFICACION"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setFechaNacimiento(con.rs.getDate("FECHANACIMIENTO"));
                p.setIdPais(con.rs.getInt("IDPAIS"));
                p.setEmail(con.rs.getString("EMAIL"));
                String ssexo = con.rs.getString("SEXO");
                p.setSexo(!ssexo.isEmpty() ? ssexo.charAt(0) : ' ');
                p.setIdRepresentanteLegal(con.rs.getInt("IDREPRESENTANTELEGAL"));
                p.setRazonSocial(con.rs.getString("RAZONSOCIAL"));
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

    public static Persona getBy(String nombres, String apellidos) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        Persona p = new Persona();

        con.consultar("SELECT * FROM PERSONAS WHERE NOMBRES = '" + nombres + "' AND APELLIDOS = '" + apellidos + '\'');
        try {
            if (con.rs.next()) {
                p.setId(con.rs.getInt("ID"));
                String sTipo = con.rs.getString("TIPO");
                p.setTipo(!sTipo.isEmpty() ? sTipo.charAt(0) : ' ');
                p.setIdentificacion(con.rs.getString("IDENTIFICACION"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setFechaNacimiento(con.rs.getDate("FECHANACIMIENTO"));
                p.setIdPais(con.rs.getInt("IDPAIS"));
                p.setEmail(con.rs.getString("EMAIL"));
                String ssexo = con.rs.getString("SEXO");
                p.setSexo(!ssexo.isEmpty() ? ssexo.charAt(0) : ' ');
                p.setIdRepresentanteLegal(con.rs.getInt("IDREPRESENTANTELEGAL"));
                p.setRazonSocial(con.rs.getString("RAZONSOCIAL"));
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

    public static List<Persona> getAll() {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Persona> personas = new ArrayList<>();

        con.consultar("SELECT * FROM PERSONAS " +
                "ORDER BY APELLIDOS, NOMBRES");
        try {
            while (con.rs.next()) {
                Persona p = new Persona();
                p.setId(con.rs.getInt("ID"));
                String sTipo = con.rs.getString("TIPO");
                p.setTipo(!sTipo.isEmpty() ? sTipo.charAt(0) : ' ');
                p.setIdentificacion(con.rs.getString("IDENTIFICACION"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setFechaNacimiento(con.rs.getDate("FECHANACIMIENTO"));
                p.setIdPais(con.rs.getInt("IDPAIS"));
                p.setEmail(con.rs.getString("EMAIL"));
                String ssexo = con.rs.getString("SEXO");
                p.setSexo(!ssexo.isEmpty() ? ssexo.charAt(0) : ' ');
                p.setIdRepresentanteLegal(con.rs.getInt("IDREPRESENTANTELEGAL"));
                p.setRazonSocial(con.rs.getString("RAZONSOCIAL"));
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

    public static List<Persona> getAllWithWhere(String identificacionX, String nombresX, String apellidosX) {
        ConexionDB con = new ConexionDB();
        con.cargarDatosConexion();
        con.cargarConexion();
        List<Persona> personas = new ArrayList<>();

        con.consultar("SELECT * FROM PERSONAS " +
                "WHERE " +
                (!identificacionX.isEmpty() ? "IDENTIFICACION LIKE '%" + identificacionX + "%'" : ' ') +
                (!identificacionX.isEmpty() && (!nombresX.isEmpty() || !apellidosX.isEmpty()) ? "OR " : ' ') +
                (!nombresX.isEmpty() ? "NOMBRES LIKE '%" + nombresX + "%'" : ' ') +
                (!nombresX.isEmpty() && !apellidosX.isEmpty() ? "OR " : ' ') +
                (!apellidosX.isEmpty() ? "APELLIDOS LIKE '%" + apellidosX + "%'" : ' ') +
                "ORDER BY APELLIDOS, NOMBRES");
        try {
            while (con.rs.next()) {
                Persona p = new Persona();
                p.setId(con.rs.getInt("ID"));
                String sTipo = con.rs.getString("TIPO");
                p.setTipo(!sTipo.isEmpty() ? sTipo.charAt(0) : ' ');
                p.setIdentificacion(con.rs.getString("IDENTIFICACION"));
                p.setNombres(con.rs.getString("NOMBRES"));
                p.setApellidos(con.rs.getString("APELLIDOS"));
                p.setFechaNacimiento(con.rs.getDate("FECHANACIMIENTO"));
                p.setIdPais(con.rs.getInt("IDPAIS"));
                p.setEmail(con.rs.getString("EMAIL"));
                String ssexo = con.rs.getString("SEXO");
                p.setSexo(!ssexo.isEmpty() ? ssexo.charAt(0) : ' ');
                p.setIdRepresentanteLegal(con.rs.getInt("IDREPRESENTANTELEGAL"));
                p.setRazonSocial(con.rs.getString("RAZONSOCIAL"));
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
        List<Persona> ps = new ArrayList<>();
        ps = PersonaDAO.getAll();
        for (Persona p : ps) {
            System.out.print(p);
        }

        Persona pe = new Persona("123", "Edwin", "Solorzano Pardo");
        System.out.println("Existe " + pe + ':' + PersonaDAO.existWithWhereIdentificacion(pe.getIdentificacion()));
        System.out.println("Agregar " + pe + ':' + (!PersonaDAO.existWithWhereIdentificacion(pe.getIdentificacion()) ? PersonaDAO.add(pe) : false));

//        String identx = PersonaDao.getBy(pe.getIdentificacion()).getIdentificacion();
//        System.out.println("Eliminar "+ pe + ':' + PersonaDao.delete(identx));

        ps = PersonaDAO.getAll();
        for (Persona per : ps) {
            System.out.print(per);
        }
    }
}
