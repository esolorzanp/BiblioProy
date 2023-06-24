package models;

public class Autor {
    private int id;
    private int idAutor;

    public Autor(int id, int idAutor) {
        this.id = id;
        this.idAutor = idAutor;
    }

    public Autor(int idAutor) {
        this.id = -1;
        this.idAutor = idAutor;
    }

    public Autor() {
        this.id = -1;
        this.idAutor = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return "Autor = {" +
                "id=" + id +
                ", idAutor=" + idAutor +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Id Autor"
        };
    }

    public String[] getDats() {
        return new String[]{
                String.valueOf(getId()),
                String.valueOf(getIdAutor())
        };
    }
}
