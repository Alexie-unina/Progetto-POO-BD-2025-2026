package gui.CreaClassi;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;

public class CreaAereo {
    private JPanel mainPanel;
    private JTextField txt_idAereo;
    private JTextField txt_modello;
    private JTextField txt_nPosti;
    private JButton creaButton;
    private JButton indietroButton;
    private JFrame frame,mainFrame,frameChiamante;
    private Controller controller;
    public CreaAereo(JFrame mainFrame,JFrame frameChiamante, Controller controller){
        System.out.println("Costruttore creaaereo chiamato!"); //Debug
        this.mainFrame = mainFrame;
        this.frameChiamante = frameChiamante;
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);


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
                String nPosti,modello,idAereo;

                idAereo = txt_idAereo.getText().strip();
                modello = txt_modello.getText().strip();
                nPosti  = txt_nPosti.getText().strip();

                try
                {
                    controller.creaAereo(idAereo, modello, nPosti);
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }

                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }


}
