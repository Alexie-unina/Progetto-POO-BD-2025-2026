package gui.ListaClassi;
import controller.Controller;
import gui.CreaClassi.CreaVolo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    List<String[]> listaVoli;
    public void refreshLista(){
        model.clear();
        try {
            listaVoli = controller.getListaVoli();
            List<String> listaPilotiFormattata = new ArrayList<>();
            for (int i = 0; i < listaVoli.size() ; i++){
                listaPilotiFormattata.add(listaVoli.get(i)[0] + " " + listaVoli.get(i)[1]);
            }
            model.addAll(listaPilotiFormattata);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public ListaVoli(JFrame frameChiamante, Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Voli");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
        refreshLista();
        JListaVoli.setModel(model);
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
                new CreaVolo(frameChiamante,frame,controller);
            }
        });
        JListaVoli.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaVoli.getSelectedIndex();
                if(i == -1){
                    textArea.setText("");
                    return;
                }

                String[] volo;
                volo = listaVoli.get(i);
                String s = "Proprietà del volo: " + "\n" +
                "idVolo:" + volo[0] + "\n" +
                "destinazione:" + volo[1] + "\n" +
                "durata:" + volo[2] + "\n" +
                "pilota:" + volo[3] + "\n" +
                "Co-pilota:" + volo[4] + "\n" +
                "hostess 1:" + volo[5] + "\n" +
                "hostess 2:" + volo[6] + "\n" +
                "aereo:" + volo[7] + "\n" +
                "clienti: ";
                try {
                    String clienti = controller.mostraClientiVolo(volo[0]);
                    s = s + clienti;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                textArea.setText(s);
                frame.pack();
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.rimuoviVolo(listaVoli.get(JListaVoli.getSelectedIndex())[0]);
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
