package org.example;
import org.example.controller.MainFrameController;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Timer extends JPanel {

    static int sec =0;
    private JLabel secundeLabel;
    private JLabel timerLabel;
    private final Integer tSimulationMax;

    private FileOutputStream LOG;

    {
        try {
            LOG = new FileOutputStream("LOG_test");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Timer(int tSimulationMax) {
        this.tSimulationMax = tSimulationMax;
    }
    public void startTimer(JLabel secundeLabel)
    {
        Thread timer = new Thread( () ->
        {
            try
            {
                while(sec < tSimulationMax)
                {
                    if(sec < 10) {
                        secundeLabel.setText("00" + sec);
                    }
                    else if(sec < 100) {
                        secundeLabel.setText("0" + sec);
                    }
                    else {
                        secundeLabel.setText(String.valueOf(sec));
                    }
                    MainFrameController.distribuireClienti(sec);

                    Thread.sleep(1000);
                    sec++;
                    LOGfile();
                    if(MainFrameController.nuMaiAvemClienti()) System.exit(0);
                }
                if(sec >= tSimulationMax) System.exit(0);

            }catch(InterruptedException e)
            {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timer.start();
    }

    public static int getSec() {
        return sec;
    }

    private void LOGfile() throws IOException {
        LOG.write(("Time " + sec +"\n").getBytes());
        LOG.write(MainFrameController.clientiInAsteaptareLOG().getBytes());
        LOG.write(MainFrameController.clentiDinCoziLOG().getBytes());
        LOG.write(("\n").getBytes());
    }
}
