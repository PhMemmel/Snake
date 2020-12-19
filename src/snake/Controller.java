package snake;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controller implements PropertyChangeListener {

    private boolean running = false;
    private final SnakeCanvas snakeCanvas;
    private final Snake snake;
    private Frog frog;


    public Controller(SnakeCanvas snakeCanvas, Snake snake, Frog frog) {
        this.snakeCanvas = snakeCanvas;
        snakeCanvas.addPropertyChangeListener(this);
        this.snake = snake;
        this.frog = frog;

    }

    public void start() {
        /*
         * neccessary, cause some information (width, height) cannot be accessed by canvas
         * while still being initialized
         */
        snakeCanvas.init();
        frog.setRandomPosition(snakeCanvas.getGridWidth(), snakeCanvas.getGridHeight());

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
        snake.setDirection((int) evt.getNewValue());
    }
}
