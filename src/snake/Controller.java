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
         * necessary, cause some information (width, height) cannot be accessed by canvas
         * while still being initialized
         */
        snakeCanvas.init();
        frog.setRandomPosition(snakeCanvas.getGridWidth(), snakeCanvas.getGridHeight());

        running = true;

        while (running) {
            snake.move();
            if (snake.isBitingItself() || snakeCrashesIntoWall()) {
                System.out.println("Game over!");
                System.exit(0);
            }

            if (snake.getSnakeParts().get(0).getX() == frog.getX() && snake.getSnakeParts().get(0).getY() == frog.getY()) {
                frog.setRandomPosition(snakeCanvas.getGridWidth(), snakeCanvas.getGridHeight());
                snake.increaseSnakeLength();
            }

            snakeCanvas.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean snakeCrashesIntoWall() {
        SnakePart snakeHead = snake.getSnakeParts().get(0);

        return snakeHead.getX() >= snakeCanvas.getGridWidth() || snakeHead.getX() < 0
                || snakeHead.getY() >= snakeCanvas.getGridHeight() || snakeHead.getY() < 0;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        snake.setDirection((int) evt.getNewValue());
    }
}
