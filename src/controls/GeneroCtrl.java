package controls;

import controls.comparate.GeneroGeneroComparator;
import dao.GeneroDAO;
import models.Genero;

import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GeneroCtrl {
    public List<Genero> generos;

    public GeneroCtrl() {
        getAll();
    }

    public boolean add(Genero generox) {
        if (GeneroDAO.add(generox)) {
            Genero x = GeneroDAO.getBy(generox.getGenero());
            generos.add(x);
            Collections.sort(generos, new GeneroGeneroComparator());
        }
        return true;
    }

    public boolean modify(Genero genero) {
        if (GeneroDAO.modify(genero)) {
            Genero x = GeneroDAO.getBy(genero.getGenero());
            int n = getIndexOfBy(x.getGenero());
            generos.set(n, x);
            Collections.sort(generos, new GeneroGeneroComparator());
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String generox) {
        Genero p = GeneroDAO.getBy(generox);
        int n = getIndexOfBy(p.getGenero());
        if (GeneroDAO.delete(generox)) {
            if (n != -1) {
                generos.remove(n);
                Collections.sort(generos, new GeneroGeneroComparator());
            }
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

    public boolean exist(String idPersonax) {
        return GeneroDAO.exist(idPersonax);
    }

    public DefaultTableModel getDefaultTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Genero ge = new Genero();
        dtm.setColumnIdentifiers(ge.getTitles());
        if (generos.size() > 0) {
            for (Genero g : generos) {
                dtm.addRow(g.getData());
            }
        }
        return dtm;
    }

    public void getAll() {
        generos = GeneroDAO.getAll();
    }

    public void getAllWithWhereGenero(String generoX) {
        generos = GeneroDAO.getAllWithWhereGenero(generoX);
    }
}
