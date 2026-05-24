package gui.CreaClassi;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaAereo {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
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
    }



}
