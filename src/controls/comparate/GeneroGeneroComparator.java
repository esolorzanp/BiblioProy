package controls.comparate;

import models.Genero;

import java.util.Comparator;

public class GeneroGeneroComparator implements Comparator<Genero> {

    @Override
    public int compare(Genero o1, Genero o2) {
        return o1.getGenero().toLowerCase().compareTo(o2.getGenero().toLowerCase());
    }
}
