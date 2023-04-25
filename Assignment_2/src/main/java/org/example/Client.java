package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class Client extends JLayeredPane {

    private final int id;
    private final int tArrival;
    private final AtomicInteger tService;
    JLabel timpClientLabel;





    public Client(Integer id, Integer tArrival, Integer tService) {
        this.id = id;
        this.tArrival = tArrival;
        this.tService = new AtomicInteger(tService);

        this.setPreferredSize(new Dimension(35, 50));
        this.setLayout(null);
        this.setBackground(new Color(0x0FFFFFF, true));


        JLabel iconitaClientLabel = new JLabel();
        iconitaClientLabel.setIcon(new ImageIcon(getClass().getResource("/client.png")));
        iconitaClientLabel.setBounds(0,0,25, 25);
        this.add(iconitaClientLabel);
        timpClientLabel = new JLabel(String.valueOf(tArrival), SwingConstants.CENTER);
        timpClientLabel.setBounds(0, 25, 20, 15);
        timpClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timpClientLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        this.add(timpClientLabel);

        this.setVisible(true);
    }

    public void arrivalToServiceTime() //Trecere de la tArrival la tService
    {
//        int time = gettService().get();
        this.timpClientLabel.setText(String.valueOf(tService));
//        this.add(timpClientLabel);

    }


    public int getId() {
        return id;
    }

    public int gettArrival() {
        return tArrival;
    }

    public AtomicInteger gettService() {
        return tService;
    }

    public int timpLaCasa() //cat timp mai are de petrecut clientul la casa - Decrementare tService
    {
        int aux = tService.decrementAndGet();
        timpClientLabel.setText(String.valueOf(aux));

        //this.add(timpClientLabel);
        return aux;
    }
}
