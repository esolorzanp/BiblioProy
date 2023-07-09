package controls.comparate;

import models.Pais;

import java.util.Comparator;

public class PaisPaisComparator implements Comparator<Pais> {

    @Override
    public int compare(Pais o1, Pais o2) {
        return o1.getPais().toLowerCase().compareTo(o2.getPais().toLowerCase());
    }
}
