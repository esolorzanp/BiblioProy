package models;

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
                '}' + '\n';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Editorial",
                "Id Pais"
        };
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(getId()),
                getEditorial(),
                String.valueOf(getIdPais())
        };
    }
}
