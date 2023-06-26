package controls;


import dao.EditorialDAO;
import models.Editorial;

import java.util.Iterator;
import java.util.List;

public class EditorialCtrl {
    List<Editorial> editoriales;

    public EditorialCtrl() {
        editoriales = EditorialDAO.getAll();
    }

    public boolean add(Editorial editorial) {
        if (EditorialDAO.add(editorial)) {
            Editorial p = EditorialDAO.getBy(editorial.getEditorial());
            editoriales.add(p);
        }
        return true;
    }

    public boolean modify(Editorial editorial) {
        if (EditorialDAO.modify(editorial)) {
            Editorial p = EditorialDAO.getBy(editorial.getEditorial());
            int n = getIndexOfBy(p.getEditorial());
            editoriales.set(n, p);
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String editorialx) {
        if (EditorialDAO.delete(editorialx)) {
            Editorial p = EditorialDAO.getBy(editorialx);
            int n = getIndexOfBy(p.getEditorial());
            if (n != -1)
                editoriales.remove(n);
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String editorialx) {
        int n = -1;
        Iterator<Editorial> iterator = editoriales.iterator();
        while (iterator.hasNext()) {
            Editorial p = iterator.next();
            if (p.getEditorial().equals(editorialx)) {
                n = editoriales.indexOf(p);
                break;
            }
        }
        return n;
    }

    public Editorial getBy(String paisx) {
        Editorial p = null;
        Iterator<Editorial> iterator = editoriales.iterator();
        while (iterator.hasNext()) {
            Editorial x = iterator.next();
            if (x.getEditorial().equals(paisx)) {
                p = x;
                break;
            }
        }
        return p;
    }

    public boolean exist(String paisx) {
        return EditorialDAO.exist(paisx);
    }

}
