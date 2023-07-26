package views;

import javax.swing.*;
import java.awt.event.*;

public class PersonaView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable personaTable;
    private JButton newButton;
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lastButton;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTextField emailTextField;
    private JTextField identificacionTextField;
    private JTextField nombresTextField;
    private JTextField apellidosTextField;
    private JComboBox tipoComboBox;
    private JComboBox paisComboBox;
    private JFormattedTextField fechaNacimientoFormattedTextField;
    private JTextField sexoTextField;
    private JTextField razonSocialTextField;
    private JComboBox representanteLegalComboBox;

    public PersonaView() {
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
        PersonaView dialog = new PersonaView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
