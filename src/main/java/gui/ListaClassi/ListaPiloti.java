package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaPilota;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaPiloti {
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JList<String> JListaPiloti;
    private JTextArea textArea;
    private JButton rimuoviButton;
    private JFrame frame;
    private Controller controller;
    private JFrame frameChiamante;
    DefaultListModel<String> model = new DefaultListModel<>();
    private List<String[]> listaPiloti = new ArrayList<>();

    /**
     * Aggiorna la lista grafica dei piloti con i dati presenti nel database.
     * Recupera i piloti tramite il controller, formatta id e nome completo di ogni pilota
     * e aggiorna il modello associato alla lista.
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @see Controller
     * @see DefaultListModel
     * @see JList
     * @see JTextArea
     * @see SQLException
     * @see JOptionPane
     * @see model.Pilota
     * @see dao.PilotaDAO
     */
    public void refreshLista(){
        model.clear();
        try {
            listaPiloti = controller.getListaPiloti();
            List<String> listaPilotiFormattata = new ArrayList<>();
            for (int i = 0; i < listaPiloti.size() ; i++){
                listaPilotiFormattata.add(listaPiloti.get(i)[5] + " " + listaPiloti.get(i)[2]);
            }
            model.addAll(listaPilotiFormattata);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"E stato riscontrato un problema con il database \n Informazioni in Console");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Costruttore della classe ListaPiloti.
     * Inizializza la finestra grafica per la visualizzazione dei piloti,
     * nasconde la finestra chiamante, carica la lista dei piloti e configura
     * i listener per navigazione, creazione, selezione e rimozione.
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param frameChiamante finestra precedente da nascondere durante la visualizzazione della lista
     * @param controller controller usato per accedere ai dati e alle operazioni sui piloti
     * @see JFrame
     * @see Controller
     * @see CreaPilota
     * @see ActionListener
     * @see ListSelectionListener
     * @see JOptionPane
     * @see model.Pilota
     * @see dao.PilotaDAO
     */
    public ListaPiloti(JFrame frameChiamante,Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Piloti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        refreshLista();
        JListaPiloti.setModel(model);
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
                new CreaPilota(frameChiamante,frame,controller);
            }
        });
        JListaPiloti.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaPiloti.getSelectedIndex();
                if(i == -1) {
                    textArea.setText("");
                    return;
                }
                String[] pilota;
                pilota = listaPiloti.get(i);
                String s = "Proprietà del pilota: " + "\n" +
                        "Login:     " + pilota[0] + "\n" +
                        "Nome:      " + pilota[2] + "\n" +
                        "Codice Fiscale: " + pilota[3] + "\n" +
                        "Numero di Cellulare:" + pilota[4] + "\n" +
                        "ID Pilota:" + pilota[5] + "\n" +
                        "salario:" + pilota[6] + "\n";
                textArea.setText(s);
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(JListaPiloti.getSelectedIndex() == -1){
                        JOptionPane.showMessageDialog(null,"Selezionare prima un pilota");
                        return;
                    }
                    controller.rimuoviPilota(listaPiloti.get(JListaPiloti.getSelectedIndex())[5]);
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });
    }
}
