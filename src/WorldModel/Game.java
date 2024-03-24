package WorldModel;

import javax.swing.*;
import Entities.Player;
import Tools.Direction;
import Tools.EntityLoader;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.List;

public class Game extends JFrame implements KeyListener, MouseListener {
    public static final int width = 1920;
    public static final int height = 1024;
    private List<Player> worldEntities;
    private JLayeredPane pane;
    private boolean isActive;
    private HashSet<Direction> directionSet = new HashSet<>();
    private ImmediateWorld immediateWorld;
    private Player character;
    private int frameTime = 0;

    public Game() {
        initializeGame();
        
        while (isActive) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
            checkMove();
            updatePlayer();
            frameTime++;
            
        }
    }

    public Game(int time) {
        int endTime = time * 10;
        initializeTestGame();

        while (isActive && endTime > 0) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
            checkMove();
            updatePlayer();
            endTime--;
        }
    }

    public void updatePlayer() {
        if (!character.isWalking && frameTime >= 25) {
            frameTime = 0;
            this.character.updateFrame();
        } else if (character.isWalking && frameTime >= 10) {
            frameTime = 0;
            this.character.updateFrame();
        }
    }

    public void setEntities() {
        new EntityLoader(immediateWorld);
        this.worldEntities = immediateWorld.getEntities();
        this.character = worldEntities.get(0);
        for (Player zombie : worldEntities) {
            this.pane.add(zombie, JLayeredPane.PALETTE_LAYER);
        }
    }

    public void checkMove() {
        if (directionSet.size() > 0) {
            character.isWalking = true;
            boolean up = directionSet.contains(Direction.Up);
            boolean right = directionSet.contains(Direction.Right);
            boolean down = directionSet.contains(Direction.Down);
            boolean left = directionSet.contains(Direction.Left);

            int dx = 0;
            int dy = 0;
            double pdx = 0;
            double pdy = 0;

            if (up) {
                character.facingDirection = Direction.Up;
                dy += 16;
                pdy -= .25;
            }

            if (down) {
                character.facingDirection = Direction.Down;
                dy -= 16;
                pdy += .25;
            }
            if (left) {
                character.facingDirection = Direction.Left;
                dx += 16;
                pdx -= 0.25;
            }
            if (right) {
                character.facingDirection = Direction.Right;
                dx -= 16;
                pdx += .25;
            }

            this.immediateWorld.move(dx, dy, pdx, pdy);

        } else {
            character.isWalking = false;
        }
        character.repaint();
        this.repaint();

    }

    public void setupPane() {
        this.pane = new JLayeredPane();
        this.pane.setOpaque(true);
        this.pane.setBackground(Color.black);
        this.setImmediateWorld();
        this.setEntities();
        this.add(pane);
    }

    public void setImmediateWorld() {
        this.immediateWorld = new ImmediateWorld();
        this.pane.add(this.immediateWorld, JLayeredPane.DEFAULT_LAYER);
        // this.immediateWorld.addWorldToPane(this.pane);
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
        this.setTitle("Ash");
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        this.setupPane();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initializeTestGame() {
        this.isActive = true;
        this.setSize(new Dimension(width, height));
        this.setupPane();
    }

    public JLayeredPane getPane() {
        return this.pane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xpx = e.getX();
        int ypx = e.getY();
        System.out.println("Mouse clicked at: (" + xpx + ", " + ypx + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
