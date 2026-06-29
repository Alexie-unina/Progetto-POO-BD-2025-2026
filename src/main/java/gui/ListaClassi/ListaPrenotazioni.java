package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaPrenotazione;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaPrenotazioni {

    private JFrame frame,frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JList JListaPrenotazioni;
    private JTextArea textArea;
    private JButton rimuoviButton;

    public ListaPrenotazioni(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Prenotazioni");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addAll(controller.getListaPrenotazioni());
        JListaPrenotazioni.setModel(model);


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
                String[] prenotazione = controller.getPrenotazione(i);
                String s =  "Proprietà della prenotazione: " +"\n" +
                        "idPrenotazione:" + prenotazione[0] + "\n" +
                        "cliente:" + prenotazione[1] + "\n" +
                        "volo:" + prenotazione[2] + "\n" +
                        "posto:" + prenotazione[3] + "\n" +
                        "classe:" + prenotazione[4] + "\n";

                textArea.setText(s);

            }
        });

    }
}
