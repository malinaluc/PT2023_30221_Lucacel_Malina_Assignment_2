package org.example.singlePointAccess;


import javax.swing.*;

public class GUIFrameSinglePointAccess {

    private static final JFrame appFrame = initFrame();

    private static JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    public static void changePanel(JPanel panel, String frameTitle) {
        appFrame.setContentPane(panel);
        appFrame.setTitle(frameTitle);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public static void showDialogMessage(String message) {
        JOptionPane.showMessageDialog(appFrame, message);
    }
}
