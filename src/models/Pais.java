package models;

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

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Pais",
                "Code"
        };
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(getId()),
                getPais(),
                getCode()
        };
    }
}
