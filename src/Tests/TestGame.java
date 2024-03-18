package Tests;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JLayeredPane;

import org.junit.jupiter.api.Test;

import WorldModel.Game;

public class TestGame {
    
    @Test
    public void testPanelContents() {
        Game ng = new Game();
        JLayeredPane pane = ng.getPane();
        System.out.println(pane.getComponentCount());
    }
}
