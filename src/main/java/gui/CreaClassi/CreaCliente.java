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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


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

                login         = txt_login.getText().strip();
                password      = txt_password.getText().strip();
                nome          = txt_nomeCompleto.getText().strip();
                codiceFiscale = txt_codiceFiscale.getText().strip();
                numero        = txt_cellulare.getText().strip();
                idCliente     = txt_idCliente.getText().strip();

                try
                {
                    controller.creaCliente(login,password,nome,codiceFiscale,numero,idCliente);
                    mainFrame.setVisible(true);
                    frame.dispose();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }

            }
        });
    }
}
