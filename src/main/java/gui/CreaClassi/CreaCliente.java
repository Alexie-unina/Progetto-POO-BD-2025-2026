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

    /**
     * Costruisce e visualizza la finestra grafica per la creazione di un nuovo cliente.
     * <p>
     * Il costruttore inizializza il frame dedicato alla creazione del cliente,
     * chiude la finestra chiamante e configura i listener dei pulsanti.
     * Il pulsante "Indietro" riporta l'utente alla finestra principale, mentre
     * il pulsante di creazione legge i dati inseriti nei campi di testo e delega
     * al {@link Controller} la creazione del cliente.
     * </p>
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @param mainFrame frame principale dell'applicazione da rendere nuovamente visibile al termine dell'operazione
     * @param frameChiamante frame da cui è stata aperta la schermata di creazione e che viene chiuso all'apertura
     * @param controller controller applicativo utilizzato per creare il nuovo cliente
     *
     * @see Controller
     * @see JFrame
     * @see JButton
     * @see JTextField
     */
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
