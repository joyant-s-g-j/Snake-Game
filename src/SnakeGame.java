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

    Tile food;
    Random random;

    SnakeGame(int boradWidth, int boradHeight)
    {
        this.boradWidth = boradWidth;
        this.boradHeight = boradHeight;
        setPreferredSize(new Dimension(this.boradWidth, this.boradHeight));
        setBackground(Color.BLACK);

        snakeHead = new Tile(5, 5);

        food = new Tile(10, 10);
        random = new Random();
        placeFood();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        //Grid
        for(int i=0; i<boradWidth/tileSize; i++)
        {
            g.drawLine(i*tileSize, 0, i*tileSize, boradHeight);
            g.drawLine(0, i*tileSize, boradWidth, i*tileSize);
        }

        //food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //Snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void placeFood()
    {
        food.x = random.nextInt(boradWidth/tileSize);
        food.y = random.nextInt(boradHeight/tileSize);
    }
    
}
