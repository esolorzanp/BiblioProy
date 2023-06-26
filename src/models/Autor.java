package models;

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

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Id persona"
        };
    }

    public String[] getDats() {
        return new String[]{
                String.valueOf(getId()),
                String.valueOf(getIdPersona())
        };
    }
}
