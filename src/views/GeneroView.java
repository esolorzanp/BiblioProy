package views;

import controls.GeneroCtrl;
import models.Genero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;

public class GeneroView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldGenero;
    private JButton newButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lastButton;
    private JButton searchButton;
    private JTextField textFieldSearch;
    private JTable tableGeneros;
    private GeneroCtrl generoCtrl;
    private Genero generoSelected;
    private int rowToSelect;

    public GeneroView() {
        generoCtrl = new GeneroCtrl();
        generoSelected = null;
        rowToSelect = -1;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        cargarJTable();
        newButton.setEnabled(true);
        addButton.setEnabled(false);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        setStateToButtonsNavigate();
        textFieldGenero.setEnabled(false);

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

        tableGeneros.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableGeneros.rowAtPoint(e.getPoint());
                int column = tableGeneros.columnAtPoint(e.getPoint());
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

        tableGeneros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
                int keyPressed = e.getKeyCode();
                if (Arrays.binarySearch(keys, keyPressed) >= 0) {
                    int row = tableGeneros.getSelectedRow();
                    int column = tableGeneros.getSelectedColumn();
                    updateDataFieldsFormFromJTable(row, column);
                }
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDataFieldsFormFromJTable(int row, int column) {
        if (row >= 0 && column >= 0) {
            rowToSelect = row;
            String g = String.valueOf(tableGeneros.getValueAt(row, 0));
            generoSelected = generoCtrl.getBy(g);
            textFieldGenero.setText(g);
            textFieldGenero.setEnabled(true);
            addButton.setEnabled(false);
            modifyButton.setEnabled(true);
            deleteButton.setEnabled(true);
            setStateToButtonsNavigate();
        }
    }

    private void onNew() {
        textFieldGenero.setText("");
        addButton.setEnabled(true);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        textFieldGenero.setEnabled(true);
    }

    private void onAdd() {
        String g = textFieldGenero.getText();
        if (fieldsFormValidated()) {
            if (!generoCtrl.existWithWhereGenero(g)) {
                Genero generoX = new Genero();
                generoX.setGenero(g);
                if (generoCtrl.add(generoX)) {
                    JOptionPane.showMessageDialog(null, "Género agregado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Genero ya existe");
            }
        }
    }

    private void onModify() {
        String generoStr = textFieldGenero.getText();
        if (fieldsFormValidated()) {
            Genero generoX = generoSelected;
            int idX = generoX.getId();
            if (generoCtrl.existWithWhereId(idX)) {
                generoX.setGenero(generoStr);
                if (generoCtrl.modify(generoX)) {
                    JOptionPane.showMessageDialog(null, "Género modificado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Género no puede ser modificado porque ya existe");
            }
        }
    }

    private void onDelete() {
        String g = textFieldGenero.getText();
        if (fieldsFormValidated()) {
            if (generoCtrl.existWithWhereGenero(g)) {
                if (generoCtrl.delete(g)) {
                    JOptionPane.showMessageDialog(null, "Género eliminado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Género no existe");
            }
        }
    }

    private void onSearch() {
        String s = textFieldSearch.getText();
        if (!s.isEmpty()) {
            generoCtrl.getAllWithWhereGenero(s);
            cargarJTable();
        }
    }

    private void onFirst() {
        int size = tableGeneros.getRowCount();
        if (size > 0) {
            rowToSelect = 0;
            tableGeneros.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onLast() {
        int size = tableGeneros.getRowCount();
        if (size > 0) {
            rowToSelect = size - 1;
            tableGeneros.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onPrevious() {
        int size = tableGeneros.getRowCount();
        if (size > 0 && rowToSelect > 0) {
            rowToSelect--;
            tableGeneros.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onNext() {
        int size = tableGeneros.getRowCount();
        if (size > 0 && rowToSelect < (size - 1)) {
            rowToSelect++;
            tableGeneros.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void setStateToButtonsNavigate() {
        int size = tableGeneros.getRowCount();

        if (rowToSelect <= 0) {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        } else if (rowToSelect > 0 && rowToSelect < size - 1) {
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
        boolean b = true;
        String generoStr = textFieldGenero.getText();
        if (generoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Género no puede estar vacío");
            b = false;
        }
        Genero generoX = generoCtrl.getBy(generoStr);
        System.out.println("generoX=" + generoX);
        System.out.println("generoSelected" + generoSelected);
        if (generoX != null
                && generoSelected != null
                && generoStr.equals(generoX.getGenero())
                && generoSelected.getId() != generoX.getId()) {
            JOptionPane.showMessageDialog(null, "Registro no se puede modificar. Ya existe otro registro con el mismo genero");
            return false;
        }
        return b;
    }

    private void cargarJTable() {
        String[] c = {"Género"};
        DefaultTableModel dtm = generoCtrl.getDefaultTableModel(c);
        tableGeneros.setModel(dtm);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        GeneroView dialog = new GeneroView();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
