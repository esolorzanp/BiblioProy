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

    public boolean add(Genero generoX) {
        if (GeneroDAO.add(generoX)) {
            Genero x = GeneroDAO.getBy(generoX.getGenero());
            generos.add(x);
            Collections.sort(generos, new GeneroGeneroComparator());
        }
        return true;
    }

    public boolean modify(Genero generoX) {
        if (GeneroDAO.modify(generoX)) {
            Genero x = GeneroDAO.getBy(generoX.getGenero());
            int n = getIndexOfBy(x.getGenero());
            generos.set(n, x);
            Collections.sort(generos, new GeneroGeneroComparator());
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String generoX) {
        Genero p = GeneroDAO.getBy(generoX);
        int n = getIndexOfBy(p.getGenero());
        if (GeneroDAO.delete(generoX)) {
            if (n != -1) {
                generos.remove(n);
                Collections.sort(generos, new GeneroGeneroComparator());
            }
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String generoX) {
        int n = -1;
        for (Genero x : generos) {
            if (x.getGenero().equals(generoX)) {
                n = generos.indexOf(x);
                break;
            }
        }
        return n;
    }

    public Genero getBy(String generoX) {
        for (Genero x : generos) {
            if (x.getGenero().equals(generoX)) {
                return x;
            }
        }
        return null;
    }

    public boolean existWithWhereGenero(String generoX) {
        return GeneroDAO.existWithWhereGenero(generoX);
    }

    public boolean existWithWhereId(int idX) {
        return GeneroDAO.existWithWhereId(idX);
    }

    public DefaultTableModel getDefaultTableModel(String[] columns) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(columns);
        if (generos.size() > 0) {
            for (Genero g : generos) {
                dtm.addRow(g.getData(columns));
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
