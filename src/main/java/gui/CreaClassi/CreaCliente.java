package gui.CreaClassi;

import controller.Controller;
import exceptions.ChiaveException;
import exceptions.ParameterMissingException;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreaCliente {
    private JPanel mainPanel;
    private JTextField txt_login;
    private JTextField txt_nomeCompleto;
    private JTextField txt_codiceFiscale;
    private JPasswordField txt_password;
    private JTextField txt_cellulare;
    private JTextField txt_idCliente;
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
        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login,password,nome,codiceFiscale,numero,idCliente;

                login         = txt_login.getText();
                password      = txt_password.getText();
                nome          = txt_nomeCompleto.getText();
                codiceFiscale = txt_codiceFiscale.getText();
                numero        = txt_cellulare.getText();
                idCliente     = txt_idCliente.getText();

                try
                {
                    controller.creaCliente(login,password,nome,codiceFiscale,numero,idCliente);
                }
                catch (AuthenticationException | ParameterMissingException | ChiaveException ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }
                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
