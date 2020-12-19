package snake;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controller implements PropertyChangeListener {

    private boolean running = false;
    private SnakeCanvas snakeCanvas;
    private Snake snake;


    public Controller(SnakeCanvas snakeCanvas, Snake snake) {
        this.snakeCanvas = snakeCanvas;
        snakeCanvas.addPropertyChangeListener(this);
        this.snake = snake;


    }

    public void start() {
        running = true;

        while (running) {
            snake.move();
            snakeCanvas.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        snake.setDirection((int)evt.getNewValue());
    }
}
