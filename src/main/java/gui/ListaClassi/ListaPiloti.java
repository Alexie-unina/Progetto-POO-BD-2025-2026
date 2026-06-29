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
    private JList<String> JlistaPiloti;
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

        ArrayList<String> piloti = controller.getListaPiloti();
        DefaultListModel<String> modelPiloti = new DefaultListModel<String>();
        modelPiloti.addAll(piloti);
        JlistaPiloti.setModel(modelPiloti);


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
        JlistaPiloti.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JlistaPiloti.getSelectedIndex();
                String[] pilota = controller.getPilota(i);
                String s =  "Proprietà del pilota: " +"\n" +
                        "Login:     " + pilota[0] +                     "\n" +
                        "Nome:      " + pilota[1] +                     "\n" +
                        "Codice Fiscale: " + pilota[2]  +               "\n" +
                        "Numero di Cellulare:" + pilota[3] +            "\n" +
                        "ID Pilota:" + pilota[4] +                      "\n" +
                        "Salario:" + pilota[5] +                        "\n";

                textArea.setText(s);

            }
        });
    }
}

