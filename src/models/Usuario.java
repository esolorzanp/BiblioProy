package models;

public class Usuario {
    private int id;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private char perfil;

    public Usuario(int id, String usuario, String nombres, String apellidos, String email, String password, char perfil) {
        this.id = id;
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.perfil = perfil;
    }

    public Usuario(String usuario) {
        this.id = -1;
        this.usuario = usuario;
        this.nombres = "";
        this.apellidos = "";
        this.email = "";
        this.password = "";
        this.perfil = 'U';
    }

    public Usuario() {
        this.id = -1;
        this.usuario = "";
        this.nombres = "";
        this.apellidos = "";
        this.email = "";
        this.password = "";
        this.perfil = 'U';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getPerfil() {
        return perfil;
    }

    public void setPerfil(char perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "id =" + id +
                ", usuario ='" + usuario + '\'' +
                ", nombres ='" + nombres + '\'' +
                ", apellidos ='" + apellidos + '\'' +
                ", email ='" + email + '\'' +
                ", password ='" + password + '\'' +
                ", Perfil =" + perfil +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Id",
                "Usuario",
                "Nombres",
                "Apellidos",
                "Email",
//                "Password",
                "Perfil"
        };
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(id),
                usuario,
                nombres,
                apellidos,
                email,
//                password,
                String.valueOf(perfil)
        };
    }

}
