package WorldModel;

import javax.swing.*;
import Entities.Zombie;
import Tools.Direction;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game extends JFrame implements KeyListener {
    public static final int width = 1920;
    public static final int height = 1024;
    private List<Zombie> worldEntities = new ArrayList<>();
    private Zombie dude;
    private JLayeredPane pane;
    private boolean isActive;
    private HashSet<Direction> directionSet;
    private ImmediateWorld immediateWorld;

    public Game() {
        this.directionSet = new HashSet<>();
        initializeGame();
        while (isActive) {
            move();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void setup() {
        // might read a file here to get the entities in worldEntites but for now
        worldEntities.add(new Zombie(width / 2, height / 2));
        this.createPaneAndEntities();
    }

    public void createPaneAndEntities() {
        this.pane = new JLayeredPane();
        this.add(pane);
        this.setImmediateWorld();
        this.setEntities();
    }

    public void setImmediateWorld() {
        this.immediateWorld = new ImmediateWorld();
        this.immediateWorld.addWorldToPane(this.pane);
    }

    public void setEntities() {
        for (Zombie zombie : worldEntities) {
            this.pane.add(zombie, JLayeredPane.PALETTE_LAYER);
            this.dude = zombie;
        }
    }

    public void move() {
        boolean up = directionSet.contains(Direction.Up);
        boolean right = directionSet.contains(Direction.Right);
        boolean down = directionSet.contains(Direction.Down);
        boolean left = directionSet.contains(Direction.Left);

        int dx = 0;
        int dy = 0;

        if (up)
            dy += 30;
        if (down)
            dy -= 30;
        if (left)
            dx += 30;
        if (right)
            dx -= 30;

        this.immediateWorld.move(dx, dy);
        this.repaint();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                directionSet.add(Direction.Up);
                break;
            case KeyEvent.VK_S:
                directionSet.add(Direction.Down);
                break;
            case KeyEvent.VK_A:
                directionSet.add(Direction.Left);
                break;
            case KeyEvent.VK_D:
                directionSet.add(Direction.Right);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!directionSet.isEmpty()) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    if (directionSet.contains(Direction.Up)) {
                        directionSet.remove(Direction.Up);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (directionSet.contains(Direction.Down)) {
                        directionSet.remove(Direction.Down);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (directionSet.contains(Direction.Left)) {
                        directionSet.remove(Direction.Left);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (directionSet.contains(Direction.Right)) {
                        directionSet.remove(Direction.Right);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    public void initializeGame() {
        this.isActive = true;
        ;
        this.setTitle("Grass World");
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        this.setup();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public JLayeredPane getPane() {
        return this.pane;
    }

}
