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

    /**
     * starting the game
     */
    public void start() {

        snakeCanvas.init();
        // set initial frog position
        frog.setRandomPosition(snakeCanvas.getGridWidth(), snakeCanvas.getGridHeight());

        running = true;

        while (running) {
            snake.move();
            /*
             * check if game is over
             *
             * if true: exit after delay of 3 seconds
             */
            // TODO implement better exit "strategy" or resetting the game
            if (snake.isBitingItself() || snakeCrashesIntoWall()) {
                System.out.println("Game over!");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }

            /*
             * check if frog has been eaten
             *
             * if so: increase length of snake and reset frog to a position not colliding with snake
             */
            if (snake.getSnakeParts().get(0).getX() == frog.getX() && snake.getSnakeParts().get(0).getY() == frog.getY()) {
                do {
                    frog.setRandomPosition(snakeCanvas.getGridWidth(), snakeCanvas.getGridHeight());
                } while (snake.getSnakeParts().stream().anyMatch(e -> e.getX() == frog.getX() && e.getY() == frog.getY()));

                snake.increaseSnakeLength();
            }

            // repaint the canvas!
            snakeCanvas.update();
            // main loop
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

    /*
     * gets called when notified by view (SnakeCanvas)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        snake.setDirection((int) evt.getNewValue());
    }
}
