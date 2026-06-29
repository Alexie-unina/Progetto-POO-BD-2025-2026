package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaHostess;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaHostess {

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JList JListaHostess;
    private JTextArea textArea;
    private JButton rimuoviButton;
    DefaultListModel<String> model = new DefaultListModel<>();

    private void refreshLista () {
        model.clear();
        model.addAll(controller.getListaHostess());
    }

    public ListaHostess(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Aerei");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        refreshLista();
        JListaHostess.setModel(model);

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
                new CreaHostess(frameChiamante,frame,controller);
            }
        });
        JListaHostess.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaHostess.getSelectedIndex();
                String[] hostess;
                try {
                    hostess = controller.getHostess(i);
                    String s = "Proprietà dell'hostess: " + "\n" +
                            "Login:     " + hostess[0] + "\n" +
                            "Nome:      " + hostess[1] + "\n" +
                            "Codice Fiscale: " + hostess[2] + "\n" +
                            "Numero di Cellulare:" + hostess[3] + "\n" +
                            "ID Hostess:" + hostess[4] + "\n" +
                            "Salario:" + hostess[5] + "\n";

                    textArea.setText(s);
                }
                catch(Exception ex){
                    textArea.setText("");
                }
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.rimuoviHostess(JListaHostess.getSelectedIndex());
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });
    }
}
