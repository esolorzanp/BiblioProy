package models;

import java.util.HashMap;
import java.util.Map;

public class Libro {
    private int id;
    private String titulo;
    private int idAutor;
    private int idEditorial;
    private int idGenero;
    private int anoPublicacion;
    private char prestadoEn;
    private int diasPrestamo;

    public Libro(int id, String titulo, int idAutor, int idEditorial, int idGenero, int anoPublicacion, char prestadoEn, int diasPrestamo) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.idEditorial = idEditorial;
        this.idGenero = idGenero;
        this.anoPublicacion = anoPublicacion;
        this.prestadoEn = prestadoEn;
        this.diasPrestamo = diasPrestamo;
    }

    public Libro(String titulo, int idAutor, int idEditorial, int idGenero, int anoPublicacion) {
        this.id = -1;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.idEditorial = idEditorial;
        this.idGenero = idGenero;
        this.anoPublicacion = anoPublicacion;
        this.prestadoEn = 'S';
        this.diasPrestamo = 1;
    }

    public Libro() {
        this.id = -1;
        this.titulo = "";
        this.idAutor = -1;
        this.idEditorial = -1;
        this.idGenero = -1;
        this.anoPublicacion = 0;
        this.prestadoEn = ' ';
        this.diasPrestamo = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public char getPrestadoEn() {
        return prestadoEn;
    }

    public void setPrestadoEn(char prestadoEn) {
        this.prestadoEn = prestadoEn;
    }

    public int getDiasPrestamo() {
        return diasPrestamo;
    }

    public void setDiasPrestamo(int diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
    }

    @Override
    public String toString() {
        return "Libro = {" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idAutor=" + idAutor +
                ", idEditorial=" + idEditorial +
                ", idGenero=" + idGenero +
                ", anoPublicacion=" + anoPublicacion +
                ", prestadoEn=" + prestadoEn +
                ", diasPrestamo=" + diasPrestamo +
                '}' + '\n';
    }

    private Map<String, String> getTitlesColumnsMap() {
        Map<String, String> m = new HashMap<>();
        m.put("Id", String.valueOf(getId()));
        m.put("Título", getTitulo());
        m.put("Id Autor", String.valueOf(getIdAutor()));
        m.put("Id Editorial", String.valueOf(getIdEditorial()));
        m.put("Id Genero", String.valueOf(getIdGenero()));
        m.put("Año publicación", String.valueOf(getAnoPublicacion()));
        m.put("Prestado en", String.valueOf(getPrestadoEn()));
        m.put("Días préstamo", String.valueOf(getDiasPrestamo()));
        return m;
    }

    public String[] getData(String[] columns) {
        Map<String, String> m = getTitlesColumnsMap();
        String[] d = new String[columns.length];

        for (int i = 0; i < columns.length; i++) {
            d[i] = m.get(columns[i]);
        }
        return d;
    }
}
