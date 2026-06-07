package gui.CreaClassi;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaPrenotazione {
    private JLabel cp;
    private JPanel mainPanel;
    private JTextField textField1;
    private JComboBox comboClienti;
    private JComboBox comboVoli;
    private JRadioButton economyRadioButton;
    private JRadioButton economyPlusRadioButton;
    private JRadioButton businessRadioButton;
    private JRadioButton primaRadioButton;
    private JButton creaButton;
    private JButton indietroButton;
    private JFrame frame;


    public CreaPrenotazione(JFrame mainFrame, JFrame frameChiamante, Controller controller){
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
        DefaultComboBoxModel<String> clientiModel = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> voliModel = new DefaultComboBoxModel();
        clientiModel.addAll(controller.getListaClienti());
        voliModel.addAll(controller.getListaVoli());
        comboClienti.setModel(clientiModel);
        comboVoli.setModel(voliModel);

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
