import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel {
    int boradWidth;
    int boradHeight;
    
    SnakeGame(int boradWidth, int boradHeight)
    {
        this.boradWidth = boradWidth;
        this.boradHeight = boradHeight;
        setPreferredSize(new Dimension(this.boradWidth, this.boradHeight));
        setBackground(Color.BLACK);
    }
}
