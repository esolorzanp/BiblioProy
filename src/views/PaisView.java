package views;

import controls.PaisCtrl;
import models.Pais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;

public class PaisView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable paisesTable;
    private JButton newButton;
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lastButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField searchTextField;
    private JTextField paisTextField;
    private JTextField codeTextField;
    private PaisCtrl paisCtrl;
    private Pais paisSelected;
    private int rowToSelect;

    public PaisView() {
        paisCtrl = new PaisCtrl();
        paisSelected = null;
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
        paisTextField.setEnabled(false);
        codeTextField.setEnabled(false);

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

        paisesTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = paisesTable.rowAtPoint(e.getPoint());
                int column = paisesTable.columnAtPoint(e.getPoint());
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

        paisesTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
                int keyPressed = e.getKeyCode();
                if (Arrays.binarySearch(keys, keyPressed) >= 0) {
                    int row = paisesTable.getSelectedRow();
                    int column = paisesTable.getSelectedColumn();
                    updateDataFieldsFormFromJTable(row, column);
                }
            }
        });

        setTitle("Gestión de paises");
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
            String paisStr = String.valueOf(paisesTable.getValueAt(row, 0));
            String codeStr = String.valueOf(paisesTable.getValueAt(row, 1));
            paisSelected = paisCtrl.getBy(paisStr);
            paisTextField.setText(paisStr);
            paisTextField.setEnabled(true);
            codeTextField.setText(codeStr);
            codeTextField.setEnabled(true);
            addButton.setEnabled(false);
            modifyButton.setEnabled(true);
            deleteButton.setEnabled(true);
            setStateToButtonsNavigate();
        }
    }

    private void onNew() {
        paisTextField.setText("");
        codeTextField.setText("");
        addButton.setEnabled(true);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        paisTextField.setEnabled(true);
        codeTextField.setEnabled(true);
    }

    private void onAdd() {
        String paisStr = paisTextField.getText();
        String codeStr = codeTextField.getText();
        if (fieldsFormValidated()) {
            if (!paisCtrl.existWithWherePais(paisStr)) {
                Pais paisX = new Pais();
                paisX.setPais(paisStr);
                paisX.setCode(codeStr);
                if (paisCtrl.add(paisX)) {
                    JOptionPane.showMessageDialog(null, "Pais agregado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pais ya existe");
            }
        }
    }

    private void onModify() {
        String paisStr = paisTextField.getText();
        String codeStr = codeTextField.getText();
        if (fieldsFormValidated()) {
            Pais paisX = paisSelected;
            int idX = paisX.getId();
            if (paisCtrl.existWithWhereId(idX)) {
                paisX.setPais(paisStr);
                paisX.setCode(codeStr);
                if (paisCtrl.modify(paisX)) {
                    JOptionPane.showMessageDialog(null, "Pais modificado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pais no puede ser modificado porque ya existe");
            }
        }
    }

    private void onDelete() {
        String paisStr = paisTextField.getText();
        if (paisSelected != null) {
            if (paisCtrl.existWithWherePais(paisStr)) {
                if (paisCtrl.delete(paisStr)) {
                    JOptionPane.showMessageDialog(null, "Pais eliminado");
                    cargarJTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pais no existe");
            }
        }
    }

    private void onSearch() {
        String s = searchTextField.getText();
        if (!s.isEmpty()) {
            paisCtrl.getAllWithWherePais(s);
            cargarJTable();
        }
    }

    private void onFirst() {
        int size = paisesTable.getRowCount();
        if (size > 0) {
            rowToSelect = 0;
            paisesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onLast() {
        int size = paisesTable.getRowCount();
        if (size > 0) {
            rowToSelect = size - 1;
            paisesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onPrevious() {
        int size = paisesTable.getRowCount();
        if (size > 0 && rowToSelect > 0) {
            rowToSelect--;
            paisesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void onNext() {
        int size = paisesTable.getRowCount();
        if (size > 0 && rowToSelect < (size - 1)) {
            rowToSelect++;
            paisesTable.setRowSelectionInterval(rowToSelect, rowToSelect);
            updateDataFieldsFormFromJTable(rowToSelect, rowToSelect);
        }
    }

    private void setStateToButtonsNavigate() {
        int size = paisesTable.getRowCount();

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
        String paisStr = paisTextField.getText();
        if (paisStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo pais no puede estar vacío");
            return false;
        }
        Pais paisX = paisCtrl.getBy(paisStr);
        if (paisX != null
                && paisSelected != null
                && paisStr.equals(paisX.getPais())
                && paisSelected.getId() != paisX.getId()) {
            JOptionPane.showMessageDialog(null, "Registro no se puede modificar. Ya existe otro registro con el mismo pais");
            return false;
        }
        String codeStr = codeTextField.getText();
        if (codeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo code no puede estar vacío");
            return false;
        }
        return true;
    }

    private void cargarJTable() {
        String[] c = {"Pais", "Code"};
        DefaultTableModel dtm = paisCtrl.getDefaultTableModel(c);
        paisesTable.setModel(dtm);
    }


    public static void main(String[] args) {
        PaisView dialog = new PaisView();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
