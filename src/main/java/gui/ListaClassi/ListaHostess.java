package gui.ListaClassi;

import controller.Controller;
import gui.CreaClassi.CreaHostess;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private List<String[]> listaHostess = new ArrayList<>();

    /**
     * Aggiorna la lista grafica degli Hostess con i dati presenti nel database.
     * Recupera gli Hostess tramite il controller, formatta id e Nome Completo di ogni hostess
     * e li mostra nel modello della lista.
     *
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @see Controller
     * @see DefaultListModel
     * @see JList
     * @see JTextArea
     * @see SQLException
     * @see JOptionPane
     * @see model.Hostess
     * @see dao.HostessDAO
     */
    private void refreshLista () {
        model.clear();
        try {
            listaHostess = controller.getListaHostess();
            List<String> listaHostessFormattata = new ArrayList<>();
            for (int i = 0; i < listaHostess.size() ; i++){
                listaHostessFormattata.add(listaHostess.get(i)[0] + " " + listaHostess.get(i)[2]);
            }
            model.addAll(listaHostessFormattata);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"E stato riscontrato un problema con il database \n Informazioni in Console");
            System.out.println(e.getMessage());
        }
    }

    public ListaHostess(JFrame frameChiamante, Controller controller){
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        frame = new JFrame("Lista Aerei");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameChiamante.setVisible(false);
        frame.setVisible(true);

        refreshLista();
        JListaHostess.setModel(model);
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
                new CreaHostess(frameChiamante,frame,controller);
            }
        });
        JListaHostess.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = JListaHostess.getSelectedIndex();
                if(i == -1){
                    textArea.setText("");
                    return;
                }
                String[] hostess;
                hostess = listaHostess.get(i);
                String s = "Proprietà dell'hostess: " + "\n" +
                        "Login:     " + hostess[0] + "\n" +
                        "Nome:      " + hostess[2] + "\n" +
                        "Codice Fiscale: " + hostess[3] + "\n" +
                        "Numero di Cellulare:" + hostess[4] + "\n" +
                        "ID Hostess:" + hostess[5] + "\n" +
                        "Salario:" + hostess[6] + "\n";

                    textArea.setText(s);

            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(JListaHostess.getSelectedIndex() == -1){
                        JOptionPane.showMessageDialog(null,"Selezionare prima un hostess");
                        return;
                    }
                    controller.rimuoviHostess(listaHostess.get(JListaHostess.getSelectedIndex())[5]);
                    System.out.println("rimosso correttamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                refreshLista();
            }
        });
    }
}
