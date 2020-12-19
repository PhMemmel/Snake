package snake;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    private final static int gridSize = 20;

    public static void main(String[] args) {

        Snake snake = new Snake();

        // initialize view
        SnakeCanvas snakeCanvas = new SnakeCanvas(gridSize, snake);
        // initialize controller
        Controller controller = new Controller(snakeCanvas, snake);


        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.add(snakeCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        controller.start();
    }
}
