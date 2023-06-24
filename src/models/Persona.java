package models;

import java.sql.Date;

public class Persona {
    private int id;
    private char tipo;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private int idPais;
    private String email;
    private char sexo;
    private int idRepresentanteLegal;
    private String razonSocial;

    public Persona(int id, char tipo, String identificacion, String nombres, String apellidos, Date fechaNacimiento, int idPais, String email, char sexo, int idRepresentanteLegal, String razonSocial) {
        this.id = id;
        this.tipo = tipo;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.idPais = idPais;
        this.email = email;
        this.sexo = sexo;
        this.idRepresentanteLegal = idRepresentanteLegal;
        this.razonSocial = razonSocial;
    }

    public Persona(String identificacion, String nombres, String apellidos, Date fechaNacimiento, int idPais, String email, char sexo) {
        this.id = -1;
        this.tipo = 'N';
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.idPais = idPais;
        this.email = email;
        this.sexo = sexo;
        this.idRepresentanteLegal = -1;
        this.razonSocial = "";
    }

    public Persona(String identificacion, String nombres, String apellidos) {
        this.id = -1;
        this.tipo = 'N';
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = null;
        this.idPais = -1;
        this.email = "";
        this.sexo = ' ';
        this.idRepresentanteLegal = -1;
        this.razonSocial = "";
    }

    public Persona(String identificacion, int idPais, String email, int idRepresentanteLegal, String razonSocial) {
        this.id = -1;
        this.tipo = 'J';
        this.identificacion = identificacion;
        this.nombres = "";
        this.apellidos = "";
        this.fechaNacimiento = null;
        this.idPais = idPais;
        this.email = email;
        this.sexo = ' ';
        this.idRepresentanteLegal = idRepresentanteLegal;
        this.razonSocial = razonSocial;
    }

    public Persona(String identificacion, int idRepresentanteLegal, String razonSocial) {
        this.id = -1;
        this.tipo = 'J';
        this.identificacion = identificacion;
        this.nombres = "";
        this.apellidos = "";
        this.fechaNacimiento = null;
        this.idPais = -1;
        this.email = "";
        this.sexo = ' ';
        this.idRepresentanteLegal = idRepresentanteLegal;
        this.razonSocial = razonSocial;
    }

    public Persona() {
        this.id = -1;
        this.tipo = ' ';
        this.identificacion = "";
        this.nombres = "";
        this.apellidos = "";
        this.fechaNacimiento = null;
        this.idPais = -1;
        this.email = "";
        this.sexo = ' ';
        this.idRepresentanteLegal = -1;
        this.razonSocial = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    public void setIdRepresentanteLegal(int idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Override
    public String toString() {
        return "Persona = {" +
                "id=" + id +
                ", tipo=" + tipo +
                ", identificacion='" + identificacion + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", idPais=" + idPais +
                ", email='" + email + '\'' +
                ", sexo=" + sexo +
                ", idRepresentanteLegal=" + idRepresentanteLegal +
                ", razonSocial='" + razonSocial + '\'' +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Tipo",
                "Identificacion",
                "Nombres",
                "Apellidos",
                "Fecha nacimiento",
                "Id pais",
                "e-mail",
                "Sexo",
                "Id Representante Legal",
                "Razon social"
        };
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(getId()),
                String.valueOf(getTipo()),
                String.valueOf(getIdentificacion()),
                getNombres(),
                getApellidos(),
                getFechaNacimiento().toString(),
                String.valueOf(getIdPais()),
                getEmail(),
                String.valueOf(getSexo()),
                String.valueOf(getIdRepresentanteLegal()),
                getRazonSocial()

        };
    }
}
