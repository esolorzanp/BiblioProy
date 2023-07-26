package models;

import java.util.HashMap;
import java.util.Map;

public class Pais {
    private int id;
    private String pais;
    private String code;

    public Pais(int id, String pais, String code) {
        this.id = id;
        this.pais = pais;
        this.code = code;
    }

    public Pais(int id, String pais) {
        this.id = id;
        this.pais = pais;
        this.code = "";
    }

    public Pais() {
        this.id = -1;
        this.pais = "";
        this.code = "";
    }

    public Pais(String pais) {
        this.id = -1;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Pais = {" +
                "id=" + id +
                ", pais='" + pais + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    private Map<String, String> getTitlesColumnsMap() {
        Map<String, String> m = new HashMap<>();
        m.put("Id", String.valueOf(getId()));
        m.put("Pais", getPais());
        m.put("Code", getCode());
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
