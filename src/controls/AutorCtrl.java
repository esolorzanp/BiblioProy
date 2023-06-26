package controls;

import dao.AutorDAO;
import models.Autor;
import models.Persona;

import java.util.Iterator;
import java.util.List;

public class AutorCtrl {
    List<Autor> autores;

    public AutorCtrl() {
        autores = AutorDAO.getAll();
    }

    public boolean add(Autor autor) {
        if (AutorDAO.add(autor)) {
            Autor x = AutorDAO.getBy(autor.getIdPersona());
            autores.add(x);
        }
        return true;
    }

    public boolean modify(Autor autor) {
        if (AutorDAO.modify(autor)) {
            Autor x = AutorDAO.getBy(autor.getIdPersona());
            int n = getIndexOfBy(x.getIdPersona());
            autores.set(n, x);
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(int autorx) {
        if (AutorDAO.delete(autorx)) {
            Autor p = AutorDAO.getBy(autorx);
            int n = getIndexOfBy(p.getIdPersona());
            if (n != -1)
                autores.remove(n);
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(int idPersonax) {
        int n = -1;
        Iterator<Autor> iterator = autores.iterator();
        while (iterator.hasNext()) {
            Autor x = iterator.next();
            if (x.getIdPersona() == idPersonax) {
                n = autores.indexOf(x);
                break;
            }
        }
        return n;
    }

    public Autor getBy(int autorx) {
        Iterator<Autor> iterator = autores.iterator();
        while (iterator.hasNext()) {
            Autor x = iterator.next();
            if (x.getIdPersona() == autorx) {
                return x;
            }
        }
        return null;
    }

    public boolean exist(int idPersonax) {
        return AutorDAO.exist(idPersonax);
    }

}
