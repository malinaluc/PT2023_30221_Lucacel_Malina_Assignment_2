package org.example.views;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class MainFrameView {

    @Getter
    private final JPanel mainPanel;
    @Getter
    private final JPanel listaCoziPanel;
    @Getter
    private final JPanel coadaClientiPanel;
    @Getter
    private final JLabel timerLabel;
    @Getter
    private final JLabel secundeLabel;
    @Getter
    private final JPanel timerPanel;

    public MainFrameView()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        listaCoziPanel = new JPanel();
        listaCoziPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        listaCoziPanel.setBackground(new Color(-2252579));
        listaCoziPanel.setPreferredSize(new Dimension(1300, 350));
        mainPanel.add(listaCoziPanel);
        timerPanel = new JPanel();
        timerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        timerPanel.setBackground(new Color(-7398179));
        listaCoziPanel.add(timerPanel);
        timerLabel = new JLabel();
        timerLabel.setText("TIMER");
        timerPanel.add(timerLabel);
        secundeLabel = new JLabel();
        secundeLabel.setText("");
        timerPanel.add(secundeLabel);
        coadaClientiPanel = new JPanel();
        coadaClientiPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        coadaClientiPanel.setBackground(new Color(-4882724));
        coadaClientiPanel.setPreferredSize(new Dimension(1300, 350));
        mainPanel.add(coadaClientiPanel);
    }

}
