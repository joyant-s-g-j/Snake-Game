import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boradWidth = 600;
        int boradHeight = boradWidth;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boradWidth, boradHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(boradWidth, boradHeight);
        frame.add(snakeGame);
        frame.pack();
    }
}
