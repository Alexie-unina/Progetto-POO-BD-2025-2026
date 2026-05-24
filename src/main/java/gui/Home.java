package gui;

import controller.Controller;
import gui.ListaClassi.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel mainPanel;
    private JButton btnListaPilota;
    private JButton btnListaHostess;
    private JButton btnListaCliente;
    private JButton btnListaAereo;
    private JButton btnListaVolo;
    private JButton btnListaPrenotazioni;
    private JButton btnEsci;
    private JLabel mm;
    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        frameHome = new JFrame("Home");
        frameHome.setContentPane(new Home().mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);


    }

    public Home() {
        controller = new Controller();
        // Add action listeners or other initialization code here

        btnEsci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exit();
            }
        });

        btnListaPilota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaPiloti(frameHome,controller);
            }
        });
        btnListaHostess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaHostess(frameHome,controller);
            }
        });
        btnListaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaClienti(frameHome,controller);
            }
        });
        btnListaAereo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaAerei(frameHome,controller);
            }
        });
        btnListaVolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaVoli(frameHome,controller);
            }
        });
        btnListaPrenotazioni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaPrenotazioni(frameHome,controller);
            }
        });
    }


}
