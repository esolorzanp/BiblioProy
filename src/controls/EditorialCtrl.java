package controls;


import controls.comparate.EditorialEditorialComparator;
import dao.EditorialDAO;
import dao.PaisDAO;
import models.Editorial;
import models.Pais;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EditorialCtrl {
    List<Editorial> editoriales;

    public EditorialCtrl() {
        this.getAll();
    }

    public boolean add(Editorial editorial) {
        if (EditorialDAO.add(editorial)) {
            Editorial p = EditorialDAO.getBy(editorial.getEditorial());
            editoriales.add(p);
            editoriales.sort(new EditorialEditorialComparator());
        }
        return true;
    }

    public boolean modify(Editorial editorial) {
        if (EditorialDAO.modify(editorial)) {
            Editorial p = EditorialDAO.getBy(editorial.getEditorial());
            int n = getIndexOfBy(p.getEditorial());
            editoriales.set(n, p);
            editoriales.sort(new EditorialEditorialComparator());
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String editorialX) {
        Editorial p = EditorialDAO.getBy(editorialX);
        int n = getIndexOfBy(p.getEditorial());
        if (EditorialDAO.delete(editorialX)) {
            if (n != -1) {
                editoriales.remove(n);
                editoriales.sort(new EditorialEditorialComparator());
            }
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String editorialX) {
        int n = -1;
        for (Editorial p : editoriales) {
            if (p.getEditorial().equals(editorialX)) {
                n = editoriales.indexOf(p);
                break;
            }
        }
        return n;
    }

    public Editorial getBy(String paisX) {
        Editorial p = null;
        for (Editorial x : editoriales) {
            if (x.getEditorial().equals(paisX)) {
                p = x;
                break;
            }
        }
        return p;
    }

    public boolean existWithWhereEditorial(String editorialX) {
        return EditorialDAO.existWithWhereEditorial(editorialX);
    }

    public boolean existWithWhereId(int idX) {
        return EditorialDAO.existWithWhereId(idX);
    }

    public DefaultTableModel getDefaultTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Editorial editorialX = new Editorial();
        dtm.setColumnIdentifiers(editorialX.getTitles());
        if (editoriales.size() > 0) {
            for (Editorial e : editoriales) {
                String[] d = e.getData();
                int idPaisX = Integer.parseInt(String.valueOf(e.getData()[2]));
                if (idPaisX != -1 && PaisDAO.existWithWhereId(idPaisX)) {
                    Pais p = PaisDAO.getBy(idPaisX);
                    d[3] = p.getPais();
                }
                dtm.addRow(d);
            }
        }
        return dtm;
    }

    public void getAll() {
        editoriales = EditorialDAO.getAll();
    }

    public void getAllWithWhereEditorial(String editorialX) {
        editoriales = EditorialDAO.getAllWithWhereEditorial(editorialX);
    }
}
