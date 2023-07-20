package controls;


import controls.comparate.UsuarioUsuarioComparator;
import dao.UsuarioDAO;
import models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioCtrl {
    public List<Usuario> usuarios;
    public Map<Character, String> perfilesMap;

    public UsuarioCtrl() {
        this.getAll();
        this.cargaPerfiles();
    }

    public boolean add(Usuario usuarioX) {
        if (UsuarioDAO.add(usuarioX)) {
            Usuario p = UsuarioDAO.getBy(usuarioX.getUsuario());
            usuarios.add(p);
            usuarios.sort(new UsuarioUsuarioComparator());
        }
        return true;
    }

    public boolean modify(Usuario usuarioX) {
        if (UsuarioDAO.modify(usuarioX)) {
            Usuario p = UsuarioDAO.getBy(usuarioX.getUsuario());
            int n = getIndexOfBy(p.getUsuario());
            usuarios.set(n, p);
            usuarios.sort(new UsuarioUsuarioComparator());
        } else {
            return false;
        }
        return true;
    }

    public boolean delete(String usuarioX) {
        Usuario p = UsuarioDAO.getBy(usuarioX);
        int n = getIndexOfBy(p.getUsuario());
        if (UsuarioDAO.delete(usuarioX)) {
            if (n != -1) {
                usuarios.remove(n);
                usuarios.sort(new UsuarioUsuarioComparator());
            }
        } else {
            return false;
        }
        return true;
    }

    public int getIndexOfBy(String usuarioX) {
        int n = -1;
        for (Usuario p : usuarios) {
            if (p.getUsuario().equals(usuarioX)) {
                n = usuarios.indexOf(p);
                break;
            }
        }
        return n;
    }

    public Usuario getBy(String usuarioX) {
        Usuario p = null;
        for (Usuario x : usuarios) {
            if (x.getUsuario().equals(usuarioX)) {
                p = x;
                break;
            }
        }
        return p;
    }

    public boolean existWithWhereUsuario(String usuarioX) {
        return UsuarioDAO.existWithWhereUsuario(usuarioX);
    }

    public boolean existWithWhereEmail(String emailX) {
        return UsuarioDAO.existWithWhereEmail(emailX);
    }

    public boolean existWithWhereId(int idX) {
        return UsuarioDAO.existWithWhereId(idX);
    }

    public DefaultTableModel getDefaultTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Usuario usuarioX = new Usuario();
        dtm.setColumnIdentifiers(usuarioX.getTitles());
        if (usuarios.size() > 0) {
            for (Usuario usuario : usuarios) {
                String[] u = usuario.getData();
                String s = u[5];
                char c = s.charAt(0);
                u[5] = perfilesMap.get(c);
                dtm.addRow(u);
            }
        }
        return dtm;
    }

    private void cargaPerfiles() {
        String[] perfilesStr = {"Administrador", "Docente", "Estudiante"};
        this.perfilesMap = new HashMap<>();
        for (String p : perfilesStr) {
            char c = p.charAt(0);
            perfilesMap.put(c, p);
        }
    }

    public DefaultComboBoxModel getDefaultComboBoxModel() {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Map.Entry<Character, String> p : perfilesMap.entrySet()) {
            dcbm.addElement(p.getValue());
        }
        return dcbm;
    }

    public String[] getUsuarioToArray() {
        return usuarios.stream()
                .map(Usuario::getUsuario)
                .toArray(String[]::new);
    }

    public int getIndexOfPaisesArray(String usuarioStrX) {
        int n = -1;
        String[] sArr = getUsuarioToArray();
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i].equals(usuarioStrX)) {
                return i;
            }
        }
        return n;
    }

    public void getAll() {
        usuarios = UsuarioDAO.getAll();
    }

    public void getAllWithWhereUsuario(String usuarioX) {
        usuarios = UsuarioDAO.getAllWithWhereUsuario(usuarioX);
    }
}
