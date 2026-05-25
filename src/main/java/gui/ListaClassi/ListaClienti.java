package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaCliente;
import model.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaClienti {

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JScrollPane scrollPane;
    private JList listaClienti;
    private ArrayList<Cliente> clienti;
//    private String[] clientiStrings;
    private DefaultListModel<String> model;
    public ListaClienti(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Clienti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        clienti = controller.getClienti();
//        clientiStrings = new String[clienti.size()];
        model = new DefaultListModel<String>();

        for(int i = 0; i < clienti.size(); i++){
//            clientiStrings[i] = clienti.get(i).getIdCliente() + " " + clienti.get(i).getNomeCompleto();
            model.add(i,clienti.get(i).getIdCliente() + " " + clienti.get(i).getNomeCompleto());
        }
        listaClienti = new JList(model);


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
                new CreaCliente(frameChiamante,frame,controller);
            }
        });
    }
}
