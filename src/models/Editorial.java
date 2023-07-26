package models;

import java.util.HashMap;
import java.util.Map;

public class Editorial {
    private int id;
    private String editorial;
    private int idPais;

    public Editorial(int id, String editorial, int idPais) {
        this.id = id;
        this.editorial = editorial;
        this.idPais = idPais;
    }

    public Editorial(String editorial, int idPais) {
        this.id = -1;
        this.editorial = editorial;
        this.idPais = idPais;
    }

    public Editorial(String editorial) {
        this.id = -1;
        this.editorial = editorial;
        this.idPais = -1;
    }

    public Editorial() {
        this.id = -1;
        this.editorial = "";
        this.idPais = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        return "Editorial = {" +
                "id=" + id +
                ", editorial='" + editorial + '\'' +
                ", idPais=" + idPais +
                '}';
    }

    private Map<String, String> getTitlesColumnsMap() {
        Map<String, String> m = new HashMap<>();
        m.put("Id", String.valueOf(getId()));
        m.put("Editorial", getEditorial());
        m.put("Id Pais", String.valueOf(getIdPais()));
        m.put("Pais", "");
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
