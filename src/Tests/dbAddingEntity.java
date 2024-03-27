package Tests;
import javax.swing.*;

import Entities.LavenderTree;
import WorldModel.ImmediateWorld;

import java.awt.*;

public class dbAddingEntity {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(750, 750);

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0, 0, 750, 750);
        pane.setBackground(Color.black);
        pane.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 750, 750);
        panel.setVisible(true);

        JLayeredPane innerPane = new JLayeredPane();
        innerPane.setBounds(50, 50, 400, 400); // Adjusted bounds to fit within the panel
        innerPane.setPreferredSize(new Dimension(400, 400));
        innerPane.setVisible(true);

        JPanel top = new LavenderTree(0, 0, new ImmediateWorld());
        top.setBounds(50, 50, 100, 100); // Adjusted position within innerPane
        // top.setBackground(Color.blue);
        top.setOpaque(true); // Set opaque to true to make background color visible
        top.setVisible(true); // Set visible to true

        JLabel bot = new JLabel("Bottom Label");
        bot.setBounds(60, 60, 100, 100); // Adjusted position within innerPane
        bot.setBackground(Color.red);
        bot.setOpaque(true); // Set opaque to true to make background color visible
        bot.setVisible(true); // Set visible to true

        innerPane.add(bot, JLayeredPane.DEFAULT_LAYER);
        innerPane.add(top, JLayeredPane.PALETTE_LAYER);

        panel.add(innerPane);

        pane.add(panel);
        frame.add(pane);
        frame.setVisible(true);
    }
}
