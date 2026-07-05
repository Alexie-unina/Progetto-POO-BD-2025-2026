package gui.CreaClassi;

import controller.Controller;
import exceptions.ChiaveException;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaPilota {
    private JLabel cp;
    private JPanel mainPanel;
    private JTextField txt_login;
    private JPasswordField txt_password;
    private JButton creaButton;
    private JButton indietroButton;
    private JTextField txt_nomeCompleto;
    private JTextField txt_codiceFiscale;
    private JTextField txt_numeroDiCellulare;
    private JTextField txt_idPilota;
    private JTextField txt_Salario;
    private JFrame frame;

    /**
     * Costruisce e visualizza la finestra grafica per la creazione di un nuovo pilota.
     * <p>
     * Il costruttore inizializza il frame dedicato alla creazione del pilota,
     * chiude la finestra chiamante e configura i listener dei pulsanti.
     * Il pulsante "Indietro" riporta l'utente alla finestra principale, mentre
     * il pulsante di creazione legge i dati inseriti nei campi di testo e delega
     * al {@link Controller} la creazione del pilota.
     * </p>
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @param mainFrame frame principale dell'applicazione da rendere nuovamente visibile al termine dell'operazione
     * @param frameChiamante frame da cui è stata aperta la schermata di creazione e che viene chiuso all'apertura
     * @param controller controller applicativo utilizzato per creare il nuovo pilota
     *
     * @see Controller
     * @see JFrame
     * @see JButton
     * @see JTextField
     */
    public CreaPilota(JFrame mainFrame, JFrame frameChiamante, Controller controller){
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
                String login;
                String password;
                String nome;
                String codiceFiscale;
                String numeroDiCellulare;
                String idPilota;
                String salario;

                login = txt_login.getText().strip();
                password = txt_password.getText().strip();
                nome = txt_nomeCompleto.getText().strip();
                codiceFiscale = txt_codiceFiscale.getText().strip();
                numeroDiCellulare = txt_numeroDiCellulare.getText().strip();
                idPilota = txt_idPilota.getText().strip();
                salario = txt_Salario.getText().strip();

                try
                {
                    controller.creaPilota(login, password, nome, codiceFiscale, numeroDiCellulare, idPilota, salario);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }

                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
