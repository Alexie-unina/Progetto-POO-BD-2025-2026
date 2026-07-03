package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaCliente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaClienti {

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JButton indietroButton;
    private JButton creaNuovoButton;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JList JListaClienti;
    private JButton rimuoviButton;
    private List<String[]> listaClienti;
    private DefaultListModel<String> model = new DefaultListModel<String>();

    private void refreshLista () {
        model.clear();
        try {
            listaClienti = controller.getListaClienti();
            List<String> listaClientiFormattata = new ArrayList<>();
            for (int i = 0; i < listaClienti.size() ; i++){
                listaClientiFormattata.add(listaClienti.get(i)[5] + " " + listaClienti.get(i)[2]);
            }
            model.addAll(listaClientiFormattata);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public ListaClienti(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Clienti");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frameChiamante.setVisible(false);
        frame.setVisible(true);
        refreshLista();
        JListaClienti.setModel(model);
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
        JListaClienti.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaClienti.getSelectedIndex();
                if(i == -1){
                    textArea.setText("");
                    return;
                }
                String[] cliente;
                cliente = listaClienti.get(i);
                String s = "Proprietà del cliente: " + "\n" +
                           "Login:     " + cliente[0] + "\n" +
                           "Nome:      " + cliente[2] + "\n" +
                           "Codice Fiscale: " + cliente[3] + "\n" +
                           "Numero di Cellulare:" + cliente[4] + "\n" +
                           "ID Cliente:" + cliente[5] + "\n";
                textArea.setText(s);
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = JListaClienti.getSelectedIndex();
                    if(i == -1){
                        JOptionPane.showMessageDialog(null,"Selezionare un cliente");
                    }
                    controller.rimuoviCliente(listaClienti.get(i)[5]);
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });
    }
}
