package gui.CreaClassi;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaCliente {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField textField5;
    private JButton creaButton;
    private JButton indietroButton;
    private JFrame frame;

    public CreaCliente(JFrame mainFrame, JFrame frameChiamante, Controller controller) {

        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Cliente");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);

        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
