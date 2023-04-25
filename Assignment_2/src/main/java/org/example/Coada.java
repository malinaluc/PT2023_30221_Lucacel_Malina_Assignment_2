package org.example;

import org.example.controller.MainFrameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Coada extends JPanel {

    MainFrameController mainFrameController;
    private JPanel clientPanel;
    private JLabel iconitaClientLabel;
    private JLabel timpClientLabel;
    private Integer timpAsteptareTotal = 0;

    private final int tSimulationMax;
    private final BlockingQueue<Client> casa = new LinkedBlockingQueue<>();


    public Coada(int tSimulationMax) {
        this.tSimulationMax = tSimulationMax;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setBackground(new Color(-586040));
        this.setPreferredSize(new Dimension(40, 300));

        startQueue();
    }

    public void addClient(Client client){
        client.arrivalToServiceTime();

        this.casa.add(client);

        this.add(client);

        timpAsteptareTotal += client.gettService().get();

        this.revalidate();
        this.repaint();
    }

    public void startQueue()
    {
        Thread queueThread = new Thread( () ->
        {
            try
            {
                while(true) {
                    Thread.sleep(1000);
                    if (!casa.isEmpty()) {
                        timpAsteptareTotal--;
                        Client client = casa.peek();

                        // daca timpul de petrecut in coada a clientului a expirat, il scoatem din coada
                        if (client.timpLaCasa() == 0) {
                            removeClient();
                        }
                    }
                }

            }catch(InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });
        queueThread.start();
    }



    public void removeClient()
    {
        if(casa.isEmpty()) return;

        Client aux = casa.peek();
        casa.remove(aux);

        ///sterge din interfata
        this.remove(aux);
        this.revalidate();
        this.repaint();

    }
    public Integer getTimpAsteptareTotal() {
        return timpAsteptareTotal;
    }

    public boolean isEmpty(){
        return casa.isEmpty();
    }

    public BlockingQueue<Client> getCasa() {
        return casa;
    }
}
