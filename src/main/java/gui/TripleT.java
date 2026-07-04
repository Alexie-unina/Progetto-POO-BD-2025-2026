package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TripleT {
    private JPanel mainPanel;
    private JButton indietroButton;
    private JLabel tripleT;
    private JFrame frame;
    private BufferedImage ttt;
    public TripleT(JFrame frameChiamante){
        frame = new JFrame("TripleT");
        frameChiamante.setVisible(false);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            ttt = ImageIO.read(new File("src/main/java/tripleT.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tripleT.setIcon(new ImageIcon(ttt));
        frame.pack();


        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }


}
