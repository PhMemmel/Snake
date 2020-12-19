package snake;

import javax.swing.*;

public class Main {

    private final static int gridSize = 20;

    public static void main(String[] args) {

        Snake snake = new Snake();
        Frog frog = new Frog();


        // initialize view
        SnakeCanvas snakeCanvas = new SnakeCanvas(gridSize, snake, frog);
        // initialize controller
        Controller controller = new Controller(snakeCanvas, snake, frog);


        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.add(snakeCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        controller.start();
    }
}
