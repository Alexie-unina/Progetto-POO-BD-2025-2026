package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaAereo;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaAerei {

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JList<String> JListaAerei;
    private JTextArea textArea;
    private JButton rimuoviButton;
    DefaultListModel<String> model = new DefaultListModel<String>();

    private void refreshLista () {
        model.clear();
        model.addAll(controller.getListaAerei());
    }

    public ListaAerei(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Aerei");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);


        refreshLista();
        System.out.println("Aggiornata lista aerei"); //Debug
        JListaAerei.setModel(model);


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
                new CreaAereo(frameChiamante,frame,controller);
            }
        });

        JListaAerei.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaAerei.getSelectedIndex();
                String[] aereo;
                try {
                    aereo = controller.getAereo(i);
                    String s =  "Proprietà dell'aereo: " +"\n" +
                            "idAereo:" + aereo[0] + "\n" +
                            "modello:" + aereo[1] + "\n" +
                            "numero posti:" + aereo[2] + "\n";

                    textArea.setText(s);

                } catch (Exception ex) {
                    textArea.setText("");
                }

            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.rimuoviAereo(JListaAerei.getSelectedIndex());
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });

    }


}
