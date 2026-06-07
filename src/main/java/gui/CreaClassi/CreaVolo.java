package gui.CreaClassi;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaVolo {
    private JLabel cv;
    private JPanel mainPanel;
    private JTextField textField1;
    private JComboBox comboPiloti;
    private JComboBox comboCoPiloti;
    private JComboBox comboHostess1;
    private JComboBox comboHostess2;
    private JButton creaButton;
    private JButton indietroButton;
    private JFrame frame;

    public CreaVolo(JFrame mainFrame, JFrame frameChiamante, Controller controller){
        frameChiamante.dispose();
        frame = new JFrame("Crea Nuovo Aereo");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
        DefaultComboBoxModel<String> listaPilotiModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> listaHostessModel = new DefaultComboBoxModel<>();
        listaPilotiModel.addAll(controller.getListaPiloti());
        listaHostessModel.addAll(controller.getHostessLists());
        comboPiloti.setModel(listaPilotiModel);
        comboCoPiloti.setModel(listaPilotiModel);
        comboHostess1.setModel(listaHostessModel);
        comboHostess2.setModel(listaHostessModel);

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
                frame.dispose();
            }
        });
    }
}
