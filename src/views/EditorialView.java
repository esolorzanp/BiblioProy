package views;

import controls.EditorialCtrl;
import controls.PaisCtrl;
import models.Editorial;
import models.Pais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;

public class EditorialView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable editorialesTable;
    private JButton newButton;
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lastButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTextField editorialTextField;
    private JComboBox paisesComboBox;
    private JButton searchButton;
    private JTextField searchTextField;
    private EditorialCtrl editorialCtrl;
    private Editorial editorialSelected;
    private int rowToSelect;

    public EditorialView() {
        editorialCtrl = new EditorialCtrl();
        editorialSelected = null;
        rowToSelect = -1;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        cargarJTable();
        cargarPaisesComboBox();
        newButton.setEnabled(true);
        addButton.setEnabled(false);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        setStateToButtonsNavigate();
        editorialTextField.setEnabled(false);
        paisesComboBox.setEnabled(false);

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

        editorialesTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = editorialesTable.rowAtPoint(e.getPoint());
                int column = editorialesTable.columnAtPoint(e.getPoint());
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

        editorialesTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
                int keyPressed = e.getKeyCode();
                if (Arrays.binarySearch(keys, keyPressed) >= 0) {
                    int row = editorialesTable.getSelectedRow();
                    int column = editorialesTable.getSelectedColumn();
                    updateDataFieldsFormFromJTable(row, column);
                }
            }
        });

        setTitle("Gestión de editoriales");
        setSize(600, 400);
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
            String editorialStr = String.valueOf(editorialesTable.getValueAt(row, 0));
            String paisStr = (String) editorialesTable.getValueAt(row, 1);
            editorialSelected = editorialCtrl.getBy(editorialStr);
            editorialTextField.setText(editorialStr);
            editorialTextField.setEnabled(true);
            if (!paisStr.isEmpty()) {
                paisesComboBox.setSelectedItem(paisStr);
            } else {
                paisesComboBox.setSelectedIndex(-1);
            }
            paisesComboBox.setEnabled(true);
            addButton.setEnabled(false);
            modifyButton.setEnabled(true);
            deleteButton.setEnabled(true);
            setStateToButtonsNavigate();
        }
    }

    private void onNew() {
        editorialTextField.setText("");
        paisesComboBox.setSelectedIndex(-1);
        addButton.setEnabled(true);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        editorialTextField.setEnabled(true);
        paisesComboBox.setEnabled(true);
    }

    private void onAdd() {
        String editorialStr = editorialTextField.getText();
        String paisStr = (String) paisesComboBox.getSelectedItem();
        Pais paisX = new PaisCtrl().getBy(paisStr);
        int idPaisX = (paisX == null ? -1 : paisX.getId());
        if (fieldsFormValidated()) {
            if (!editorialCtrl.existWithWhereEditorial(editorialStr)) {
                Editorial editorialX = new Editorial();
                editorialX.setEditorial(editorialStr);
                editorialX.setIdPais(idPaisX);
                if (editorialCtrl.add(editorialX)) {
                    JOptionPane.showMessageDialog(null, "Editorial agregada");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Editorial ya existe");
            }
        }
    }

    private void onModify() {
        String editorialStr = editorialTextField.getText();
        String paisStr = (String) paisesComboBox.getSelectedItem();
        Pais paisX = new PaisCtrl().getBy(paisStr);
        int idPaisX = (paisX == null ? -1 : paisX.getId());
        if (fieldsFormValidated()) {
            Editorial editorialX = editorialSelected;
            int idX = editorialX.getId();
            if (editorialCtrl.existWithWhereId(idX)) {
                editorialX.setEditorial(editorialStr);
                editorialX.setIdPais(idPaisX);
                if (editorialCtrl.modify(editorialX)) {
                    JOptionPane.showMessageDialog(null, "Editorial modificado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Editorial no puede ser modificado porque ya existe");
            }
        }
    }

    private void onDelete() {
        String editorialStr = editorialTextField.getText();
        if (editorialSelected != null) {
            if (editorialCtrl.existWithWhereEditorial(editorialStr)) {
                if (editorialCtrl.delete(editorialStr)) {
                    JOptionPane.showMessageDialog(null, "Editorial eliminado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Editorial no existe");
            }
        }
    }

    private void onSearch() {
        String s = searchTextField.getText();
        if (!s.isEmpty()) {
            editorialCtrl.getAllWithWhereEditorial(s);
            cargarJTable();
        }
    }

    private void onFirst() {
        int size = editorialesTable.getRowCount();
        if (size > 0) {
            rowToSelect = 0;
            editorialesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onLast() {
        int size = editorialesTable.getRowCount();
        if (size > 0) {
            rowToSelect = size - 1;
            editorialesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onPrevious() {
        int size = editorialesTable.getRowCount();
        if (size > 0 && rowToSelect > 0) {
            rowToSelect--;
            editorialesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onNext() {
        int size = editorialesTable.getRowCount();
        if (size > 0 && rowToSelect < (size - 1)) {
            rowToSelect++;
            editorialesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void setStateToButtonsNavigate() {
        int size = editorialesTable.getRowCount();

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
        String editorialStr = editorialTextField.getText();
        if (editorialStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo editorial no puede estar vacío");
            return false;
        }
        Editorial editorialX = editorialCtrl.getBy(editorialStr);
        if (editorialX != null
                && editorialSelected != null
                && editorialStr.equals(editorialX.getEditorial())
                && editorialSelected.getId() != editorialX.getId()) {
            JOptionPane.showMessageDialog(null, "Registro no se puede modificar. Ya existe otro registro con la misma editorial");
            return false;
        }
        return true;
    }

    private void cargarJTable() {
        String[] c = {"Editorial", "Pais"};
        DefaultTableModel dtm = editorialCtrl.getDefaultTableModel(c);
        editorialesTable.setModel(dtm);
    }

    private void cargarPaisesComboBox() {
        PaisCtrl paisCtrl = new PaisCtrl();
        paisesComboBox.setModel(paisCtrl.getDefaultComboBoxModel());
    }


    public static void main(String[] args) {
        EditorialView dialog = new EditorialView();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
