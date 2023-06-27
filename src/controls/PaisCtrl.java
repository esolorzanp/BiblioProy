package controls;

import dao.PaisDAO;
import models.Pais;

import java.util.Iterator;
import java.util.List;

public class PaisCtrl {
    List<Pais> paises;

    public PaisCtrl() {
        paises = PaisDAO.getAll();
    }

    public boolean add(Pais pais) {
        if (PaisDAO.add(pais)) {
            Pais p = PaisDAO.getBy(pais.getPais());
            paises.add(p);
        }
        return true;
    }

    public boolean modify(Pais pais) {
        if (PaisDAO.modify(pais)) {
            Pais p = PaisDAO.getBy(pais.getPais());
            int n = getIndexOfBy(p.getPais());
            paises.set(n, p);
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String paisx) {
        if (PaisDAO.delete(paisx)) {
            Pais p = PaisDAO.getBy(paisx);
            int n = getIndexOfBy(p.getPais());
            if (n != -1)
                paises.remove(n);
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String paisx) {
        int n = -1;
        Iterator<Pais> iterator = paises.iterator();
        while (iterator.hasNext()) {
            Pais p = iterator.next();
            if (p.getPais().equals(paisx)) {
                n = paises.indexOf(p);
                break;
            }
        }
        return n;
    }

    public Pais getBy(String paisx) {
        Pais p = null;
        Iterator<Pais> iterator = paises.iterator();
        while (iterator.hasNext()) {
            Pais x = iterator.next();
            if (x.getPais().equals(paisx)) {
                p = x;
                break;
            }
        }
        return p;
    }

    public boolean exist(String paisx) {
        return PaisDAO.exist(paisx);
    }

    public static void main(String[] args) {
        PaisCtrl pct = new PaisCtrl();

        String sp1 = "Paraguay";
        Pais p1 = new Pais(sp1);
        System.out.println("Existe " + sp1 + ':' + pct.exist(sp1));
        if (!pct.exist(sp1))
            System.out.println("Agregar " + p1 + ':' + pct.add(p1));

        int n = pct.getIndexOfBy(sp1);
        System.out.println("Indice de " + sp1 + ':' + n);
        if (pct.exist(p1.getPais())) {
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
