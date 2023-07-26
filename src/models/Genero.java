package models;

import java.util.HashMap;
import java.util.Map;

public class Genero {
    private int id;
    private String genero;

    public Genero(int id, String genero) {
        this.id = id;
        this.genero = genero;
    }

    public Genero(String genero) {
        this.id = -1;
        this.genero = genero;
    }

    public Genero() {
        this.id = -1;
        this.genero = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Genero = {" +
                "id=" + id +
                ", género='" + genero + '\'' +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Género"
        };
    }

    private Map<String, String> getTitlesColumnsMap() {
        Map<String, String> m = new HashMap<>();
        m.put("Id", String.valueOf(getId()));
        m.put("Género", getGenero());
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
