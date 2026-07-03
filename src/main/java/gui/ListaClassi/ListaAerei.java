package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaAereo;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<String[]> listaAerei = new ArrayList<>();

    private void refreshLista () {
        model.clear();
        try {
            listaAerei = controller.getListaAerei();
            List<String> listaAereiFormattata = new ArrayList<>();
            for (int i = 0; i < listaAerei.size() ; i++){
                listaAereiFormattata.add(listaAerei.get(i)[0] + " " + listaAerei.get(i)[1]);
            }
            model.addAll(listaAereiFormattata);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"E stato riscontrato un problema con il database \n Informazioni in Console");
            System.out.println(e.getMessage());
        }
    }

    public ListaAerei(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Aerei");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
        refreshLista();
        System.out.println("Aggiornata lista aerei"); //Debug
        JListaAerei.setModel(model);
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
                new CreaAereo(frameChiamante,frame,controller);
            }
        });

        JListaAerei.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaAerei.getSelectedIndex();
                String[] aereo;
                if(i == -1) {
                    textArea.setText("");
                    return;
                }
                aereo = listaAerei.get(i);
                String s =  "Proprietà dell'aereo: " +"\n" +
                        "idAereo:" + aereo[0] + "\n" +
                        "modello:" + aereo[1] + "\n" +
                        "numero posti:" + aereo[2] + "\n";

                textArea.setText(s);
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(JListaAerei.getSelectedIndex() == -1){
                        JOptionPane.showMessageDialog(null,"Selezionare prima un aereo");
                        return;
                    }
                    controller.rimuoviAereo(listaAerei.get(JListaAerei.getSelectedIndex())[0]);
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });

    }


}
