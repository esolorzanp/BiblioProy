package models;

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
                ", genero='" + genero + '\'' +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Genero"
        };
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(getId()),
                getGenero()
        };
    }
}
