package controls.comparate;

import models.Persona;

import java.util.Comparator;

public class ApellidosNombresPersonaComparator implements Comparator<Persona> {

    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getApellidos().toLowerCase().compareTo(o2.getApellidos().toLowerCase());
    }
}
