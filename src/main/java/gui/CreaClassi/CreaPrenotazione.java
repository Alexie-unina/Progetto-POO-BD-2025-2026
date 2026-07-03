package gui.CreaClassi;

import controller.Controller;
import exceptions.ChiaveException;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

public class CreaPrenotazione {
    private JLabel cp;
    private JPanel mainPanel;
    private JTextField TXT_idPrenotazione;
    private JComboBox comboClienti;
    private JComboBox comboVoli;
    private JRadioButton economyRadioButton;
    private JRadioButton economyPlusRadioButton;
    private JRadioButton businessRadioButton;
    private JRadioButton primaRadioButton;
    private JButton creaButton;
    private JButton indietroButton;
    private JTextField TXT_posto;
    private JFrame frame;
    List<String[]> listaClienti;
    List<String[]> listaVoli;

    public CreaPrenotazione(JFrame mainFrame, JFrame frameChiamante, Controller controller){
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultComboBoxModel<String> clientiModel = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> voliModel = new DefaultComboBoxModel();

        try{
            listaClienti = controller.getListaClienti();
            listaVoli = controller.getListaVoli();

            for (String [] cliente : listaClienti){
                clientiModel.addElement(cliente[0] + " " + cliente[2]);
            }
            for (String[] volo : listaVoli){
                voliModel.addElement(volo[0] + " " + volo[1]);
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        comboClienti.setModel(clientiModel);
        comboVoli.setModel(voliModel);

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
        creaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idPrenotazione;
                String posto;
                String classe = "economy";

                idPrenotazione = TXT_idPrenotazione.getText().strip();
                posto = TXT_posto.getText().strip();
                if(economyRadioButton.isSelected())
                    classe = "economy";
                if(economyPlusRadioButton.isSelected())
                    classe = "economy plus";
                if(businessRadioButton.isSelected())
                    classe = "business";
                if(primaRadioButton.isSelected())
                    classe = "prima classe";
                try
                {
                    controller.creaPrenotazione(idPrenotazione, listaClienti.get(comboClienti.getSelectedIndex())[5], listaVoli.get(comboClienti.getSelectedIndex())[0], posto, classe);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }

                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
