package gui.CreaClassi;

import controller.Controller;
import exceptions.ChiaveException;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaVolo {
    private JLabel cv;
    private JPanel mainPanel;
    private JTextField txt_idVolo;
    private JComboBox comboPiloti1;
    private JComboBox comboCoPiloti;
    private JComboBox comboHostess1;
    private JComboBox comboHostess2;
    private JButton creaButton;
    private JButton indietroButton;
    private JTextField txt_destinazione;
    private JTextField txt_durata;
    private JLabel LabelAereo;
    private JComboBox comboAerei;
    private JFrame frame;

    public CreaVolo(JFrame mainFrame, JFrame frameChiamante, Controller controller){
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultComboBoxModel<String> listaPilotiModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaCopilotiModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaHostess1Model = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaHostess2Model = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaAereiModel = new DefaultComboBoxModel<>();
        listaPilotiModel.addAll(controller.getListaPiloti());
        listaCopilotiModel.addAll(controller.getListaPiloti());
        listaHostess1Model.addAll(controller.getListaHostess());
        listaHostess2Model.addAll(controller.getListaHostess());
        listaAereiModel.addAll(controller.getListaAerei());
        comboPiloti1.setModel(listaPilotiModel);
        comboCoPiloti.setModel(listaCopilotiModel);
        comboHostess1.setModel(listaHostess1Model);
        comboHostess2.setModel(listaHostess2Model);
        comboAerei.setModel(listaAereiModel);

        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idVolo;
                String destinazione;
                String durata;
                int idPilota;
                int idCoPilota;
                int idHostess1;
                int idHostess2;
                int idAereo;

                idVolo = txt_idVolo.getText().strip();
                destinazione = txt_destinazione.getText().strip();
                durata = txt_durata.getText().strip();
                idPilota = comboPiloti1.getSelectedIndex();
                idCoPilota = comboCoPiloti.getSelectedIndex();
                idHostess1 = comboHostess1.getSelectedIndex();
                idHostess2 = comboHostess2.getSelectedIndex();
                idAereo = comboAerei.getSelectedIndex();

                try
                {
                    controller.creaVolo(idVolo, destinazione, durata, idPilota, idCoPilota, idHostess1, idHostess2, idAereo);
                }
                 catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }

                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
