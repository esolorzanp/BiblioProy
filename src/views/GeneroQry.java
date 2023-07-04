package views;

import controls.GeneroCtrl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class GeneroQry extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tableGeneros;
    private JButton searchButton;
    private JTextField textFieldGeneroX;

    private DefaultTableModel dtm;
    private GeneroCtrl generoCtrl;

    public GeneroQry() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        generoCtrl = new GeneroCtrl();
        cargarDtm();

        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        tableGeneros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println("Entro por KeyEvent");
            }
        });
        tableGeneros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println("Entro por KeyPressed");
            }
        });
    }

    private void cargarDtm() {
        DefaultTableModel dtm = generoCtrl.getDefaultTableModel();
        tableGeneros.setModel(dtm);
    }

    public int getSelected() {
        System.out.println("Entra");
        int r = -1;
//        generoCtrl = new GeneroCtrl();
//        cargarDtm();
//
//        setSize(600, 300);
//        setLocationRelativeTo(null);
//        setVisible(true);
        return r;
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
        GeneroQry dialog = new GeneroQry();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
        System.out.println("Salio");
        System.out.println(dialog.getSelected());
    }
}
