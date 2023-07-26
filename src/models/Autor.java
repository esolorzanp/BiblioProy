package models;

import java.util.HashMap;
import java.util.Map;

public class Autor {
    private int id;
    private int idPersona;

    public Autor(int id, int idPersona) {
        this.id = id;
        this.idPersona = idPersona;
    }

    public Autor(int idPersona) {
        this.id = -1;
        this.idPersona = idPersona;
    }

    public Autor() {
        this.id = -1;
        this.idPersona = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Autor = {" +
                "id=" + id +
                ", idPersona=" + idPersona +
                '}' + '\n';
    }

//    public String[] getTitles() {
//        return new String[]{
//                "Id",
//                "Id persona"
//        };
//    }

    private Map<String, String> getTitlesColumnsMap() {
        Map<String, String> m = new HashMap<>();
        m.put("Id", String.valueOf(getId()));
        m.put("Id Persona", String.valueOf(getIdPersona()));
        return m;
    }

    public String[] getDats(String[] columns) {
        Map<String, String> m = getTitlesColumnsMap();
        String[] d = new String[columns.length];

        for (int i = 0; i < columns.length; i++) {
            d[i] = m.get(columns[i]);
        }
        return d;
    }
}
