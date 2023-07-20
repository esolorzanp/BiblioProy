package views;

import controls.UsuarioCtrl;
import models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;

public class UsuarioView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable usuariosTable;
    private JButton searchButton;
    private JTextField searchTextField;
    private JTextField nombresTextField;
    private JTextField apellidosTextField;
    private JTextField usuarioTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmarPasswordField;
    private JComboBox perfilComboBox;
    private JButton newButton;
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lastButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private UsuarioCtrl usuarioCtrl;
    private Usuario usuarioSelected;
    private int rowToSelect;

    public UsuarioView() {
        usuarioCtrl = new UsuarioCtrl();
        usuarioSelected = null;
        rowToSelect = -1;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        cargarJTable();
        cargarPerfilComboBox();
        newButton.setEnabled(true);
        addButton.setEnabled(false);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        setStateToButtonsNavigate();
        usuarioTextField.setEnabled(false);
        nombresTextField.setEnabled(false);
        apellidosTextField.setEnabled(false);
        emailTextField.setEnabled(false);
        perfilComboBox.setEnabled(false);
        passwordField.setEnabled(false);
        confirmarPasswordField.setEnabled(false);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        usuariosTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = usuariosTable.rowAtPoint(e.getPoint());
                int column = usuariosTable.columnAtPoint(e.getPoint());
                updateDataFieldsFormFromJTable(row, column);
            }
        });

        newButton.addActionListener(e -> onNew());
        addButton.addActionListener(e -> onAdd());
        modifyButton.addActionListener(e -> onModify());
        deleteButton.addActionListener(e -> onDelete());
        searchButton.addActionListener(e -> onSearch());
        firstButton.addActionListener(e -> onFirst());
        previousButton.addActionListener(e -> onPrevious());
        nextButton.addActionListener(e -> onNext());
        lastButton.addActionListener(e -> onLast());

        usuariosTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
                int keyPressed = e.getKeyCode();
                if (Arrays.binarySearch(keys, keyPressed) >= 0) {
                    int row = usuariosTable.getSelectedRow();
                    int column = usuariosTable.getSelectedColumn();
                    updateDataFieldsFormFromJTable(row, column);
                }
            }
        });

        setTitle("Gestión de usuarios");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void updateDataFieldsFormFromJTable(int row, int column) {
        if (row >= 0 && column >= 0) {
            rowToSelect = row;
            String usuarioStr = String.valueOf(usuariosTable.getValueAt(row, 1));
            Usuario usuario = usuarioCtrl.getBy(usuarioStr);
            String nombresStr = usuario.getNombres();
            String apellidosStr = usuario.getApellidos();
            String emailStr = usuario.getEmail();
            String passwordStr = "";//usuario.getPassword();
            String confirmarPasswordStr = "";
            DefaultComboBoxModel d = (DefaultComboBoxModel) perfilComboBox.getModel();
            char perfilChr = usuario.getPerfil();
            String perfilStr = usuarioCtrl.perfilesMap.get(perfilChr);
            int indexPerfil = d.getIndexOf(perfilStr);
            usuarioSelected = usuarioCtrl.getBy(usuarioStr);
            usuarioTextField.setText(usuarioStr);
            nombresTextField.setText(nombresStr);
            apellidosTextField.setText(apellidosStr);
            emailTextField.setText(emailStr);
            passwordField.setText(passwordStr);
            confirmarPasswordField.setText(confirmarPasswordStr);
            perfilComboBox.setSelectedIndex(indexPerfil);
            usuarioTextField.setEnabled(true);
            nombresTextField.setEnabled(true);
            apellidosTextField.setEnabled(true);
            emailTextField.setEnabled(true);
            perfilComboBox.setEnabled(true);
            passwordField.setEnabled(true);
            confirmarPasswordField.setEnabled(true);
            addButton.setEnabled(false);
            modifyButton.setEnabled(true);
            deleteButton.setEnabled(true);
            setStateToButtonsNavigate();
        }
    }

    private void onNew() {
        usuarioTextField.setText("");
        nombresTextField.setText("");
        apellidosTextField.setText("");
        emailTextField.setText("");
        perfilComboBox.setSelectedIndex(-1);
        passwordField.setText("");
        confirmarPasswordField.setText("");
        addButton.setEnabled(true);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);
        usuarioTextField.setEnabled(true);
        nombresTextField.setEnabled(true);
        apellidosTextField.setEnabled(true);
        emailTextField.setEnabled(true);
        perfilComboBox.setEnabled(true);
        passwordField.setEnabled(true);
        confirmarPasswordField.setEnabled(true);
    }

    private void onAdd() {
        String usuarioStr = usuarioTextField.getText();
        String nombresStr = nombresTextField.getText();
        String apellidosStr = apellidosTextField.getText();
        String emailStr = emailTextField.getText();
        String perfilStr = (String) perfilComboBox.getSelectedItem();
        String passwordStr = passwordField.getText();
        String confirmarPasswordStr = confirmarPasswordField.getText();
        String tipoStr = (String) perfilComboBox.getSelectedItem();
        if (fieldsFormValidated()) {
            char tipoChr = tipoStr.charAt(0);
            if (!usuarioCtrl.existWithWhereUsuario(usuarioStr)) {
                Usuario usuarioX = new Usuario();
                usuarioX.setUsuario(usuarioStr);
                usuarioX.setNombres(nombresStr);
                usuarioX.setApellidos(apellidosStr);
                usuarioX.setEmail(emailStr);
                usuarioX.setPerfil(tipoChr);
                usuarioX.setPassword(passwordStr);
                if (usuarioCtrl.add(usuarioX)) {
                    JOptionPane.showMessageDialog(null, "Usuario agregada");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario ya existe");
            }
        }
    }

    private void onModify() {
        String usuarioStr = usuarioTextField.getText();
        String nombresStr = nombresTextField.getText();
        String apellidosStr = apellidosTextField.getText();
        String emailStr = emailTextField.getText();
        String perfilStr = (String) perfilComboBox.getSelectedItem();
        String passwordStr = passwordField.getText();
        String confirmarPasswordStr = confirmarPasswordField.getText();
        String tipoStr = (String) perfilComboBox.getSelectedItem();
        char tipoChr = tipoStr.charAt(0);
        if (fieldsFormValidated()) {
            Usuario usuarioX = usuarioSelected;
            int idX = usuarioX.getId();
            if (usuarioCtrl.existWithWhereId(idX)) {
                usuarioX.setUsuario(usuarioStr);
                usuarioX.setNombres(nombresStr);
                usuarioX.setApellidos(apellidosStr);
                usuarioX.setEmail(emailStr);
                usuarioX.setPerfil(tipoChr);
                usuarioX.setPassword(passwordStr);
                if (usuarioCtrl.modify(usuarioX)) {
                    JOptionPane.showMessageDialog(null, "Usuario modificado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no puede ser modificado porque ya existe");
            }
        }
    }

    private void onDelete() {
        String usuarioStr = usuarioTextField.getText();
        if (usuarioSelected != null) {
            if (usuarioCtrl.existWithWhereUsuario(usuarioStr)) {
                if (usuarioCtrl.delete(usuarioStr)) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no existe");
            }
        }
    }

    private void onSearch() {
        String s = searchTextField.getText();
        if (!s.isEmpty()) {
            usuarioCtrl.getAllWithWhereUsuario(s);
            cargarJTable();
        }
    }

    private void onFirst() {
        int size = usuariosTable.getRowCount();
        if (size > 0) {
            rowToSelect = 0;
            usuariosTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onLast() {
        int size = usuariosTable.getRowCount();
        if (size > 0) {
            rowToSelect = size - 1;
            usuariosTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onPrevious() {
        int size = usuariosTable.getRowCount();
        if (size > 0 && rowToSelect > 0) {
            rowToSelect--;
            usuariosTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onNext() {
        int size = usuariosTable.getRowCount();
        if (size > 0 && rowToSelect < (size - 1)) {
            rowToSelect++;
            usuariosTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void setStateToButtonsNavigate() {
        int size = usuariosTable.getRowCount();

        if (rowToSelect <= 0) {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        } else if (rowToSelect < size - 1) {
            firstButton.setEnabled(true);
            previousButton.setEnabled(true);
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        } else if (rowToSelect == size - 1) {
            firstButton.setEnabled(true);
            previousButton.setEnabled(true);
            nextButton.setEnabled(false);
            lastButton.setEnabled(false);
        }
    }

    private boolean fieldsFormValidated() {
        String usuarioStr = usuarioTextField.getText();
        String nombresStr = nombresTextField.getText();
        String apellidosStr = apellidosTextField.getText();
        String emailStr = emailTextField.getText();
        int indexPerfil = perfilComboBox.getSelectedIndex();
        String perfilStr = (String) perfilComboBox.getSelectedItem();
        String passwordStr = passwordField.getText();
        String confirmarPasswordStr = confirmarPasswordField.getText();
        if (usuarioStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo usuario no puede estar vacío");
            return false;
        }
        if (nombresStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo nombre no puede estar vacío");
            return false;
        }
        if (apellidosStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo apellidos no puede estar vacío");
            return false;
        }
        if (emailStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo email no puede estar vacío");
            return false;
        }
        if (indexPerfil == -1) {
            JOptionPane.showMessageDialog(null, "Seleciones un perfil de usuario");
            return false;
        }
        if (perfilStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo perfil no puede estar vacío");
            return false;
        }
        if (passwordStr.isEmpty() && !confirmarPasswordStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo password no puede estar vacío");
            return false;
        }
        if (confirmarPasswordStr.isEmpty() && !passwordStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo confirmar password no puede estar vacío");
            return false;
        }
        if (!passwordStr.equals(confirmarPasswordStr)) {
            JOptionPane.showMessageDialog(null, "Password y confirmación no son iguales");
            return false;
        }
        Usuario usuarioX = usuarioCtrl.getBy(usuarioStr);
        if (usuarioX != null
                && usuarioSelected != null
                && usuarioStr.equals(usuarioX.getUsuario())
                && usuarioSelected.getId() != usuarioX.getId()) {
            JOptionPane.showMessageDialog(null, "Registro no se puede modificar. Ya existe otro registro con el mismo usuario");
            return false;
        }
        if (usuarioX != null
                && usuarioSelected != null
                && emailStr.equals(usuarioX.getEmail())
                && usuarioSelected.getId() != usuarioX.getId()) {
            JOptionPane.showMessageDialog(null, "Registro no se puede modificar. Ya existe otro registro con el mismo email");
            return false;
        }
        return true;
    }

    private void cargarJTable() {
        DefaultTableModel dtm = usuarioCtrl.getDefaultTableModel();
        usuariosTable.setModel(dtm);
    }

    private void cargarPerfilComboBox() {
        perfilComboBox.setModel(usuarioCtrl.getDefaultComboBoxModel());
    }

    public static void main(String[] args) {
        UsuarioView dialog = new UsuarioView();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
