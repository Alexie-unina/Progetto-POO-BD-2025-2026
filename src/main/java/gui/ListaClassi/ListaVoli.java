package gui.ListaClassi;
import controller.Controller;
import gui.CreaClassi.CreaVolo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaVoli {

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JList JListaVoli;
    private JTextArea textArea;
    private JButton rimuoviButton;
    DefaultListModel<String> model = new DefaultListModel<>();

    public void refreshLista(){
        model.clear();
        model.addAll(controller.getListaVoli());
    }

    public ListaVoli(JFrame frameChiamante, Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Voli");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);
        refreshLista();
        JListaVoli.setModel(model);

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
                new CreaVolo(frameChiamante,frame,controller);
            }
        });
        JListaVoli.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaVoli.getSelectedIndex();
                String[] volo;
                try {
                    volo = controller.getVolo(i);
                    String s = "Proprietà del volo: " + "\n" +
                            "idVolo:" + volo[0] + "\n" +
                            "destinazione:" + volo[1] + "\n" +
                            "durata:" + volo[2] + "\n" +
                            "pilota:" + volo[3] + "\n" +
                            "Co-pilota:" + volo[4] + "\n" +
                            "hostess 1:" + volo[5] + "\n" +
                            "hostess 2:" + volo[6] + "\n" +
                            "aereo:" + volo[7] + "\n";

                    textArea.setText(s);
                }
                catch (Exception ex){
                    textArea.setText("");
                }
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.rimuoviVolo(JListaVoli.getSelectedIndex());
                    System.out.println("rimosso correttamente");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });
    }
}
