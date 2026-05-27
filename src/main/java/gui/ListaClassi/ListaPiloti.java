package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaPilota;
import model.Pilota;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaPiloti {
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JList<String> listaPiloti;
    private JTextArea textArea;
    private JFrame frame;
    private Controller controller;
    private JFrame frameChiamante;

    public ListaPiloti(JFrame frameChiamante,Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Piloti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        ArrayList<Pilota> piloti = controller.getPiloti();
        DefaultListModel<String> modelPiloti = new DefaultListModel<String>();

        for(int i=0;i < piloti.size();i++){
            modelPiloti.add(i,piloti.get(i).getIdPilota() + " " + piloti.get(i).getNomeCompleto());
        }

        listaPiloti.setModel(modelPiloti);


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
        listaPiloti.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = listaPiloti.getSelectedIndex();
                String s =  "Proprietà del pilota: " +                                  "\n" +
                        "Login:     " + piloti.get(i).getLogin() +                      "\n" +
                        "Nome:      " + piloti.get(i).getNomeCompleto() +               "\n" +
                        "Codice Fiscale: " + piloti.get(i).getCodiceFiscale()  +        "\n" +
                        "Numero di Cellulare:" + piloti.get(i).getNumeroDiCellulare() + "\n" +
                        "ID Pilota:" + piloti.get(i).getIdPilota() +                    "\n" +
                        "Salario:" + piloti.get(i).getSalario() +                       "\n";

                textArea.setText(s);

            }
        });
    }
}

