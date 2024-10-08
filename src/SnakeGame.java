import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


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
    ArrayList<Tile> snakeBody;

    Tile food;
    Random random;

    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;
    int highScore;

    SnakeGame(int boradWidth, int boradHeight)
    {
        this.boradWidth = boradWidth;
        this.boradHeight = boradHeight;
        setPreferredSize(new Dimension(this.boradWidth, this.boradHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();

        highScore = 0;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        //food
        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        //Snake Head
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        //Snake Body
        for(int i=0; i<snakeBody.size(); i++)
        {
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        //Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(gameOver)
        {
            g.setColor(Color.RED);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
            g.drawString("High Score : " + String.valueOf(highScore), tileSize + 100, tileSize);
        }
        else
        {
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Score : " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
            g.drawString("High Score : " + String.valueOf(highScore), tileSize + 70, tileSize);
        }
    }

    public void placeFood()
    {
        food.x = random.nextInt(boradWidth/tileSize);
        food.y = random.nextInt(boradHeight/tileSize);
    }

    public boolean collision(Tile tile_one, Tile tile_two)
    {
        return tile_one.x == tile_two.x && tile_one.y == tile_two.y; 
    }

    public void playSound(String soundFile)
    {
        try{
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            e.printStackTrace();
        }
        
    }

    public void move()
    {
        if(collision(snakeHead, food))
        {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
            playSound("SnakeSound.wav");
        }

        for(int i=snakeBody.size()-1;i>=0; i--)
        {
            Tile snakePart = snakeBody.get(i);
            if(i == 0)
            {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else
            {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        for(int i=0; i<snakeBody.size(); i++)
        {
            Tile snakePart = snakeBody.get(i);
            if(collision(snakeHead, snakePart))
            {
                gameOver = true;
                playSound("gameOverSound.wav");
            }
        }

        if(snakeHead.x * tileSize < 0 || snakeHead.x * tileSize > boradWidth || snakeHead.y * tileSize < 0 || snakeHead.y * tileSize > boradHeight)
        {
            gameOver = true;
            playSound("gameOverSound.wav");        
        }

        if(gameOver)
        {
            int currentScore = snakeBody.size();
            if(currentScore > highScore)
            {
                highScore = currentScore;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver)
        {
            gameLoop.stop();
        }
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
        else if(e.getKeyCode() == KeyEvent.VK_SPACE && gameOver)
        {
            snakeHead = new Tile(5, 5);
            velocityX = 0;
            velocityY = 0;

            snakeBody.clear();
            placeFood();

            gameOver = false;
            gameLoop.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
