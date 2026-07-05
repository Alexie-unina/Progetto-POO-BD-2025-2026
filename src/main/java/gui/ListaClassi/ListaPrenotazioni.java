package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaPrenotazione;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaPrenotazioni {

    private JFrame frame,frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JList JListaPrenotazioni;
    private JTextArea textArea;
    private JButton rimuoviButton;
    DefaultListModel<String> model = new DefaultListModel<>();
    ArrayList<String[]> listaPrenotazioni = new ArrayList<>();

    /**
     * Aggiorna la lista grafica delle prenotazioni con i dati presenti nel database.
     * Recupera le prenotazioni tramite il controller, formatta le informazioni principali
     * di ciascuna prenotazione e aggiorna il modello della lista.
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @see Controller
     * @see DefaultListModel
     * @see JList
     * @see JTextArea
     * @see SQLException
     * @see JOptionPane
     * @see model.Prenotazione
     * @see model.Cliente
     * @see model.Volo
     * @see model.Pilota
     * @see dao.PrenotazioneDAO
     */
    public void refreshLista(){
        model.clear();
        try {
            listaPrenotazioni = controller.getListaPrenotazioni();
            List<String> listaPrenotazioniFormattata = new ArrayList<>();
            for (int i = 0; i < listaPrenotazioni.size() ; i++){
                listaPrenotazioniFormattata.add(listaPrenotazioni.get(i)[0] + " " //IdPrenotazione
                        + listaPrenotazioni.get(i)[2] + " " //Nome Cliente
                        + listaPrenotazioni.get(i)[4] + " " //Destinazione
                        + listaPrenotazioni.get(i)[8]);     //Classe
            }
            model.addAll(listaPrenotazioniFormattata);
            frame.pack();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"E stato riscontrato un problema con il database \n Informazioni in Console");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Costruttore della classe ListaPrenotazioni.
     * Inizializza la finestra grafica per la visualizzazione delle prenotazioni,
     * carica i dati iniziali e configura i listener per tornare indietro,
     * creare una nuova prenotazione, visualizzare i dettagli e rimuovere una prenotazione.
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param frameChiamante finestra precedente da nascondere durante la visualizzazione della lista
     * @param controller controller usato per accedere ai dati e alle operazioni sulle prenotazioni
     * @see JFrame
     * @see Controller
     * @see CreaPrenotazione
     * @see ActionListener
     * @see ListSelectionListener
     * @see JOptionPane
     * @see model.Prenotazione
     * @see model.Cliente
     * @see model.Volo
     * @see model.Pilota
     * @see dao.PrenotazioneDAO
     */
    public ListaPrenotazioni(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Prenotazioni");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
        refreshLista();
        JListaPrenotazioni.setModel(model);
        frame.pack();


        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        creaNuovoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pulsante premuto!"); //Debug
                new CreaPrenotazione(frameChiamante,frame,controller);
            }
        });
        JListaPrenotazioni.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaPrenotazioni.getSelectedIndex();
                String[] prenotazione;
                if (i==-1){
                    textArea.setText("");
                    return;
                }
                prenotazione = listaPrenotazioni.get(i);
                String s = "Proprietà della prenotazione: " + "\n" +
                    "idPrenotazione:" + prenotazione[0] + "\n" +
                    "cliente: id:" + prenotazione[1] + " Nome:" + prenotazione[2] + "\n" +
                    "volo: id: " + prenotazione[3] + " Destinazione: " + prenotazione[4]+ "\n" +
                    "Pilota id: " + prenotazione[5] + " Nome: " + prenotazione[6] + "\n" +
                    "posto:" + prenotazione[7] + "\n" +
                    "classe:" + prenotazione[8] + "\n";

                textArea.setText(s);
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(JListaPrenotazioni.getSelectedIndex() == -1){
                        JOptionPane.showMessageDialog(null,"Selezionare prima una prenotazione");
                        return;
                    }
                    controller.rimuoviPrenotazione(listaPrenotazioni.get(JListaPrenotazioni.getSelectedIndex())[0]);
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();

            }
        });
    }
}
