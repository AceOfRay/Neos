package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Zombie extends JPanel {
    private Point position;

    public Zombie(int posX, int posY) {
        this.position = new Point(posX, posY);
        setBounds(0, 0, 1920, 1080);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        URL dudeLocation = getClass().getResource("/images/ZombieCharacter.png");
        try {
            BufferedImage dude = ImageIO.read(dudeLocation);
            g.drawImage((Image) dude, (int)position.getX(), (int) position.getY(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Point getPosition() {
        return this.position;
    }
}
