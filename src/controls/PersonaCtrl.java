package controls;


import controls.comparate.ApellidosNombresPersonaComparator;
import dao.PaisDAO;
import dao.PersonaDAO;
import models.Editorial;
import models.Pais;
import models.Persona;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PersonaCtrl {
    List<Persona> personas;

    public PersonaCtrl() {
        this.getAll();
    }

    public boolean add(Persona persona) {
        if (PersonaDAO.add(persona)) {
            Persona p = PersonaDAO.getBy(persona.getIdentificacion());
            personas.add(p);
            personas.sort(new ApellidosNombresPersonaComparator());
        }
        return true;
    }

    public boolean modify(Persona persona) {
        if (PersonaDAO.modify(persona)) {
            Persona p = PersonaDAO.getBy(persona.getIdentificacion());
            int n = getIndexOfBy(p.getIdentificacion());
            personas.set(n, p);
            personas.sort(new ApellidosNombresPersonaComparator());
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String identificacionX) {
        Persona p = PersonaDAO.getBy(identificacionX);
        int n = getIndexOfBy(p.getIdentificacion());
        if (PersonaDAO.delete(identificacionX)) {
            if (n != -1) {
                personas.remove(n);
                personas.sort(new ApellidosNombresPersonaComparator());
            }
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String identificacionX) {
        int n = -1;
        for (Persona p : personas) {
            if (p.getIdentificacion().equals(identificacionX)) {
                n = personas.indexOf(p);
                break;
            }
        }
        return n;
    }

    public Persona getBy(String identificacionX) {
        Persona p = null;
        for (Persona x : personas) {
            if (x.getIdentificacion().equals(identificacionX)) {
                p = x;
                break;
            }
        }
        return p;
    }

    public boolean existWithWhereEditorial(String identificacionX) {
        return PersonaDAO.existWithWhereIdentificacion(identificacionX);
    }

    public boolean existWithWhereId(int idX) {
        return PersonaDAO.existWithWhereId(idX);
    }

    public DefaultTableModel getDefaultTableModel(String[] columns) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(columns);
        if (personas.size() > 0) {
            for (Persona e : personas) {
                String[] d = e.getData(columns);
                int idPaisX = e.getIdPais();
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
        personas = PersonaDAO.getAll();
    }

    public void getAllWithWhereEditorial(String identificacionX, String apellidosX, String nombresX) {
        personas = PersonaDAO.getAllWithWhere(identificacionX,apellidosX,nombresX);
    }

}
