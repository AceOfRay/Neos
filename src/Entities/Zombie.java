package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import WorldModel.Game;

public class Zombie extends JPanel {
    private Point position;

    public Zombie() {
        setBounds(0, 0, 1920, 1080);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        URL dudeLocation = getClass().getResource("/GameResources/Images/ZombieCharacter.png");
        try {
            BufferedImage dude = ImageIO.read(dudeLocation);
            g.drawImage((Image) dude, Game.width / 2, Game.height / 2, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Point getPosition() {
        return this.position;
    }
}
