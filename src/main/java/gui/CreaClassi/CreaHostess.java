package gui.CreaClassi;

import controller.Controller;
import exceptions.ChiaveException;
import exceptions.ParameterMissingException;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaHostess {
    private JLabel ch;
    private JPanel mainPanel;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JButton creaButton;
    private JButton indietroButton;
    private JTextField txtNomeCompleto;
    private JTextField txtCodiceFiscale;
    private JTextField txtCellulare;
    private JTextField txtIdHostess;
    private JTextField txtSalario;
    private JFrame frame;


    public CreaHostess(JFrame mainFrame, JFrame frameChiamante, Controller controller){
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
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

                String login,password,nome,codiceFiscale,idHostess,salario,cellulare;

                login           =   txtLogin.getText().strip();
                password        =   txtPassword.getText().strip();
                nome            =   txtNomeCompleto.getText().strip();
                codiceFiscale   =   txtCodiceFiscale.getText().strip();
                idHostess       =   txtIdHostess.getText().strip();
                cellulare       =   txtCellulare.getText().strip();
                salario         =   txtSalario.getText().strip();

                try
                {
                    controller.creaHostess(login,password,nome,codiceFiscale,cellulare,idHostess,salario);
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
