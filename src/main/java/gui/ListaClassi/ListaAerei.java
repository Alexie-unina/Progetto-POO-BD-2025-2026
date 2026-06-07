package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaAereo;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaAerei {

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JList<String> listaAerei;
    private JTextArea textArea;

    public ListaAerei(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Aerei");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        DefaultListModel<String> listaModel = new DefaultListModel<String>();
        ArrayList<String> aerei = controller.getListaAerei();
        listaModel.addAll(aerei);
        System.out.println("Aggiornata lista aerei"); //Debug
        listaAerei.setModel(listaModel);


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


        listaAerei.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = listaAerei.getSelectedIndex();
                ArrayList<String> proprietaAereo = controller.getProprietaAereo(i);

                String s =  "Proprietà dell' aereo: " + "\n" +
                            "Id Aereo:     " + proprietaAereo.getFirst() + "\n" +
                            "Modello:      " + proprietaAereo.get(1)     + "\n" +
                            "Numero Posti: " + proprietaAereo.getLast()  + "\n";
                textArea.setText(s);

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
