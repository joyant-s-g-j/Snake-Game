import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel implements ActionListener, KeyListener{
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

    Timer gameLoop;
    int velocityX;
    int velocityY;

    SnakeGame(int boradWidth, int boradHeight)
    {
        this.boradWidth = boradWidth;
        this.boradHeight = boradHeight;
        setPreferredSize(new Dimension(this.boradWidth, this.boradHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
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
    public void move()
    {
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1)
        {
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1)
        {
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1)
        {
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1)
        {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
