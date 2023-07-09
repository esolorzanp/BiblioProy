package controls;

import controls.comparate.PaisPaisComparator;
import dao.PaisDAO;
import models.Pais;

import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PaisCtrl {
    List<Pais> paises;

    public PaisCtrl() {
        paises = PaisDAO.getAll();
    }

    public boolean add(Pais paisX) {
        if (PaisDAO.add(paisX)) {
            Pais p = PaisDAO.getBy(paisX.getPais());
            paises.add(p);
            Collections.sort(paises, new PaisPaisComparator());
        }
        return true;
    }

    public boolean modify(Pais paisX) {
        if (PaisDAO.modify(paisX)) {
            Pais p = PaisDAO.getBy(paisX.getPais());
            int n = getIndexOfBy(p.getPais());
            paises.set(n, p);
            Collections.sort(paises, new PaisPaisComparator());
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String paisX) {
        Pais p = PaisDAO.getBy(paisX);
        int n = getIndexOfBy(p.getPais());
        if (PaisDAO.delete(paisX)) {
            if (n != -1) {
                paises.remove(n);
                Collections.sort(paises, new PaisPaisComparator());
            }
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String paisX) {
        int n = -1;
        Iterator<Pais> iterator = paises.iterator();
        while (iterator.hasNext()) {
            Pais p = iterator.next();
            if (p.getPais().equals(paisX)) {
                n = paises.indexOf(p);
                break;
            }
        }
        return n;
    }

    public Pais getBy(String paisX) {
        Pais p = null;
        Iterator<Pais> iterator = paises.iterator();
        while (iterator.hasNext()) {
            Pais x = iterator.next();
            if (x.getPais().equals(paisX)) {
                p = x;
                break;
            }
        }
        return p;
    }

    public boolean existWithWherePais(String paisX) {
        return PaisDAO.existWithWherePais(paisX);
    }

    public boolean existWithWhereId(int idX) {
        return PaisDAO.existWithWhereId(idX);
    }

    public DefaultTableModel getDefaultTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Pais paisX = new Pais();
        dtm.setColumnIdentifiers(paisX.getTitles());
        if (paises.size() > 0) {
            for (Pais pais : paises) {
                dtm.addRow(pais.getData());
            }
        }
        return dtm;
    }

    public void getAll() {
        paises = PaisDAO.getAll();
    }

    public void getAllWithWherePais(String paisX) {
        paises = PaisDAO.getAllWithWherePais(paisX);
    }

    public static void main(String[] args) {
        PaisCtrl pct = new PaisCtrl();

        String sp1 = "Paraguay";
        Pais p1 = new Pais(sp1);
        System.out.println("Existe " + sp1 + ':' + pct.existWithWherePais(sp1));
        if (!pct.existWithWherePais(sp1))
            System.out.println("Agregar " + p1 + ':' + pct.add(p1));

        int n = pct.getIndexOfBy(sp1);
        System.out.println("Indice de " + sp1 + ':' + n);
        if (pct.existWithWherePais(p1.getPais())) {
            Pais p2 = pct.getBy(p1.getPais());
            p2.setPais("Paraguay");
            System.out.println("Modificar " + p2 + ':' + pct.modify(p2));
        }

        String sp2 = "Paraguayy";
        System.out.println("Eliminar " + sp2 + ':' + pct.delete(sp2));

        Iterator<Pais> i = pct.paises.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
