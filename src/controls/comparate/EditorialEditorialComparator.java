package controls.comparate;

import models.Editorial;

import java.util.Comparator;

public class EditorialEditorialComparator implements Comparator<Editorial> {

    @Override
    public int compare(Editorial o1, Editorial o2) {
        return o1.getEditorial().toLowerCase().compareTo(o2.getEditorial().toLowerCase());
    }
}
