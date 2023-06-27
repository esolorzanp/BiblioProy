package controls;

import dao.GeneroDAO;
import models.Genero;

import java.util.Iterator;
import java.util.List;

public class GeneroCtrl {
    List<Genero> generos;

    public GeneroCtrl() {
        generos = GeneroDAO.getAll();
    }

    public boolean add(Genero generox) {
        if (GeneroDAO.add(generox)) {
            Genero x = GeneroDAO.getBy(generox.getGenero());
            generos.add(x);
        }
        return true;
    }

    public boolean modify(Genero genero) {
        if (GeneroDAO.modify(genero)) {
            Genero x = GeneroDAO.getBy(genero.getGenero());
            int n = getIndexOfBy(x.getGenero());
            generos.set(n, x);
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String generox) {
        if (GeneroDAO.delete(generox)) {
            Genero p = GeneroDAO.getBy(generox);
            int n = getIndexOfBy(p.getGenero());
            if (n != -1)
                generos.remove(n);
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String generox) {
        int n = -1;
        Iterator<Genero> iterator = generos.iterator();
        while (iterator.hasNext()) {
            Genero x = iterator.next();
            if (x.getGenero().equals(generox)) {
                n = generos.indexOf(x);
                break;
            }
        }
        return n;
    }

    public Genero getBy(String generox) {
        Iterator<Genero> iterator = generos.iterator();
        while (iterator.hasNext()) {
            Genero x = iterator.next();
            if (x.getGenero() == generox) {
                return x;
            }
        }
        return null;
    }

    public boolean exist(int idPersonax) {
        return GeneroDAO.exist(idPersonax);
    }


}
