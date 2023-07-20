package controls.comparate;

import models.Usuario;

import java.util.Comparator;

public class UsuarioUsuarioComparator implements Comparator<Usuario> {

    @Override
    public int compare(Usuario o1, Usuario o2) {
        return o1.getUsuario().toLowerCase().compareTo(o2.getUsuario().toLowerCase());
    }
}
