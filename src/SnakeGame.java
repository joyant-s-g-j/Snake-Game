import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel {
    private class Tile {
        int x;
        int y;
        
        Tile(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
    
    int boradWidth;
    int boradHeight;
    int tileSize = 25;
    
    Tile snakeHead;

    SnakeGame(int boradWidth, int boradHeight)
    {
        this.boradWidth = boradWidth;
        this.boradHeight = boradHeight;
        setPreferredSize(new Dimension(this.boradWidth, this.boradHeight));
        setBackground(Color.BLACK);

        snakeHead = new Tile(5, 5);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }
    
}
