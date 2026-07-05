package gui.CreaClassi;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;



    public class CreaAereo {
    private JPanel mainPanel;
    private JTextField txt_idAereo;
    private JTextField txt_modello;
    private JTextField txt_nPosti;
    private JButton creaButton;
    private JButton indietroButton;
    private JFrame frame,mainFrame,frameChiamante;
    private Controller controller;

    /**
    * Costruisce e visualizza la finestra grafica per la creazione di un nuovo aereo.
    * <p>
    * Il costruttore inizializza il frame dedicato alla creazione dell'aereo,
    * chiude la finestra chiamante e configura i listener dei pulsanti.
    * Il pulsante "Indietro" riporta l'utente alla finestra principale, mentre
    * il pulsante di creazione legge i dati inseriti nei campi di testo e delega
    * al {@link Controller} la creazione dell'aereo.
    * </p>
    *
    * @author Alessandro Pizzi
    * @author Emy Servillo
    *
    * @param mainFrame frame principale dell'applicazione da rendere nuovamente visibile al termine dell'operazione
    * @param frameChiamante frame da cui è stata aperta la schermata di creazione e che viene chiuso all'apertura
    * @param controller controller applicativo utilizzato per creare il nuovo aereo
    *
    * @see Controller
    * @see JFrame
    * @see JButton
    * @see JTextField
    */
    public CreaAereo(JFrame mainFrame,JFrame frameChiamante, Controller controller){
        System.out.println("Costruttore creaaereo chiamato!"); //Debug
        this.mainFrame = mainFrame;
        this.frameChiamante = frameChiamante;
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
                String nPosti,modello,idAereo;

                idAereo = txt_idAereo.getText().strip();
                modello = txt_modello.getText().strip();
                nPosti  = txt_nPosti.getText().strip();

                try
                {
                    controller.creaAereo(idAereo, modello, nPosti);
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
