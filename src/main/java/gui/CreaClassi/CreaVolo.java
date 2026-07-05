package gui.CreaClassi;

import controller.Controller;
import exceptions.ChiaveException;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreaVolo {
    private JLabel cv;
    private JPanel mainPanel;
    private JTextField txt_idVolo;
    private JComboBox comboPiloti1;
    private JComboBox comboCoPiloti;
    private JComboBox comboHostess1;
    private JComboBox comboHostess2;
    private JButton creaButton;
    private JButton indietroButton;
    private JTextField txt_destinazione;
    private JTextField txt_durata;
    private JLabel LabelAereo;
    private JComboBox comboAerei;
    private JFrame frame;

    List<String[]> listaPiloti = new ArrayList<>();
    List<String[]> listaHostess = new ArrayList<>();
    List<String[]> listaAerei = new ArrayList<>();

    /**
     * Costruisce e visualizza la finestra grafica per la creazione di un nuovo volo.
     * <p>
     * Il costruttore inizializza il frame dedicato alla creazione del volo,
     * chiude la finestra chiamante e configura i listener dei pulsanti.
     * Il pulsante "Indietro" riporta l'utente alla finestra principale, mentre
     * il pulsante di creazione legge i dati inseriti nei campi di testo e delega
     * al {@link Controller} la creazione del volo.
     * </p>
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @param mainFrame frame principale dell'applicazione da rendere nuovamente visibile al termine dell'operazione
     * @param frameChiamante frame da cui è stata aperta la schermata di creazione e che viene chiuso all'apertura
     * @param controller controller applicativo utilizzato per creare il nuovo volo
     *
     * @see Controller
     * @see JFrame
     * @see JButton
     * @see JTextField
     */
    public CreaVolo(JFrame mainFrame, JFrame frameChiamante, Controller controller){
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultComboBoxModel<String> listaPilotiModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaCopilotiModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaHostess1Model = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaHostess2Model = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaAereiModel = new DefaultComboBoxModel<>();



        try {
            listaPiloti = controller.getListaPiloti();
            listaHostess = controller.getListaHostess();
            listaAerei = controller.getListaAerei();

            for(String[] pilota : listaPiloti){
                listaPilotiModel.addElement(pilota[0] + " " + pilota[2]);
                listaCopilotiModel.addElement(pilota[0] + " " + pilota[2]);
            }
            for(String[] hostess : listaHostess){
                listaHostess1Model.addElement(hostess[0] + " " + hostess[2]);
                listaHostess2Model.addElement(hostess[0] + " " + hostess[2]);
            }
            for(String[] aereo : listaAerei){
                listaAereiModel.addElement(aereo[0] + " " + aereo[1]);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(1);
        }
        comboPiloti1.setModel(listaPilotiModel);
        comboCoPiloti.setModel(listaCopilotiModel);
        comboHostess1.setModel(listaHostess1Model);
        comboHostess2.setModel(listaHostess2Model);
        comboAerei.setModel(listaAereiModel);

        frame.pack();

        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idVolo;
                String destinazione;
                String durata;
                String idPilota;
                String idCoPilota;
                String idHostess1;
                String idHostess2;
                String idAereo;

                idVolo = txt_idVolo.getText().strip();
                destinazione = txt_destinazione.getText().strip();
                durata = txt_durata.getText().strip();
                idPilota   = listaPiloti.get(comboPiloti1.getSelectedIndex())[5];
                idCoPilota = listaPiloti.get(comboCoPiloti.getSelectedIndex())[5];
                idHostess1 = listaHostess.get(comboHostess1.getSelectedIndex())[5];
                idHostess2 = listaHostess.get(comboHostess2.getSelectedIndex())[5];
                idAereo = listaAerei.get(comboAerei.getSelectedIndex())[0];

                try
                {
                    controller.creaVolo(idVolo, destinazione, durata, idPilota, idCoPilota, idHostess1, idHostess2, idAereo);
                }
                 catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }

                mainFrame.setVisible(true);
                frame.dispose();
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
