package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaHostess;
import model.Hostess;


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

    public ListaHostess(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Aerei");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        ArrayList<Hostess> hostess = controller.getHostess();
        DefaultListModel<Hostess> modelPiloti = new DefaultListModel<String>();
      //  model.addAll(hostess);
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
        JlistaHostess.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaHostess.getSelectedIndex();
                String s =  "Proprietà dell'hostess: " +                                  "\n" +
                        "Login:     " + hostess.get(i).getLogin() +                      "\n" +
                        "Nome:      " + hostess.get(i).getNomeCompleto() +               "\n" +
                        "Codice Fiscale: " + hostess.get(i).getCodiceFiscale()  +        "\n" +
                        "Numero di Cellulare:" + hostess.get(i).getNumeroDiCellulare() + "\n" +
                        "ID Pilota:" + hostess.get(i).getIdPilota() +                    "\n" +
                        "Salario:" + hostess.get(i).getSalario() +                       "\n";

                textArea.setText(s);

            }
        });
    }
}
