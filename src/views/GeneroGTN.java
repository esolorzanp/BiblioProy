package views;

import controls.GeneroCtrl;
import models.Genero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class GeneroGTN extends JDialog {
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

    public GeneroGTN() {
        generoCtrl = new GeneroCtrl();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        cargarJTable();
        newButton.setEnabled(true);
        addButton.setEnabled(false);
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);

        if (generoCtrl.generos.size() > 0) {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        } else {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            nextButton.setEnabled(false);
            lastButton.setEnabled(false);
        }

        textFieldGenero.setEnabled(false);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        tableGeneros.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableGeneros.rowAtPoint(e.getPoint());
                int column = tableGeneros.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    String g = String.valueOf(tableGeneros.getValueAt(row, 1));
                    textFieldGenero.setText(g);
                    textFieldGenero.setEnabled(true);
                    addButton.setEnabled(false);
                    modifyButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNew();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onModify();
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
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
        if (!generoCtrl.exist(g)) {
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

    private void onModify() {
        String g = textFieldGenero.getText();
        if (!generoCtrl.exist(g)) {
            Genero generoX = new Genero();
            generoX.setGenero(g);
            if (generoCtrl.modify(generoX)) {
                JOptionPane.showMessageDialog(null, "Género modificado");
                cargarJTable();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Genero ya existe");
        }
    }

    private void cargarJTable() {
        DefaultTableModel dtm = generoCtrl.getDefaultTableModel();
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
        GeneroGTN dialog = new GeneroGTN();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
