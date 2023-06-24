package models;

public class Pais {
    private int id;
    private String pais;

    public Pais(int id, String pais) {
        this.id = id;
        this.pais = pais;
    }

    public Pais() {
        this.id = -1;
        this.pais = "";
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

    @Override
    public String toString() {
        return "Pais = {" +
                "id=" + id +
                ", pais='" + pais + '\'' +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Pais"
        };
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(getId()),
                getPais()
        };
    }
}
