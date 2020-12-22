package snake;

import java.util.ArrayList;

public class Snake {

    ArrayList<SnakePart> snakeParts;
    private Direction direction;

    public Snake() {
        direction = Direction.RIGHT;
        snakeParts = new ArrayList<>();
        snakeParts.add(new SnakePart(5, 5));
        snakeParts.add(new SnakePart(5, 4));
        snakeParts.add(new SnakePart(5, 3));
    }

    /**
     * method to move snake
     * <p>
     * gets called periodically by Controller
     */
    public void move() {
        /*
         * set new snake head position according to current direction
         */
        SnakePart snakeHead = snakeParts.get(0);
        switch (direction) {
            case UP:
                snakeHead.setPosition(snakeHead.getX(), snakeHead.getY() - 1);
                break;
            case DOWN:
                snakeHead.setPosition(snakeHead.getX(), snakeHead.getY() + 1);
                break;
            case LEFT:
                snakeHead.setPosition(snakeHead.getX() - 1, snakeHead.getY());
                break;
            case RIGHT:
                snakeHead.setPosition(snakeHead.getX() + 1, snakeHead.getY());
        }

        /*
         * all other snake parts: jump to old position of predecessor
         */
        for (int i = 1; i < snakeParts.size(); i++) {
            snakeParts.get(i).setPosition(snakeParts.get(i - 1).getOldX(), snakeParts.get(i - 1).getOldY());

        }

    }

    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void setDirection(int keyCode) {
        this.direction = Direction.getDirection(keyCode);
    }

    public void increaseSnakeLength() {
        snakeParts.add(new SnakePart(snakeParts.get(snakeParts.size() - 1).getX(),
                snakeParts.get(snakeParts.size() - 1).getY()));
    }

    public boolean isBitingItself() {
        boolean isBiting = false;
        SnakePart snakeHead = snakeParts.get(0);
        for (int i = 1; i < snakeParts.size(); i++) {
            SnakePart currentSnakePart = snakeParts.get(i);
            if (currentSnakePart.getX() == snakeHead.getX()
                    && currentSnakePart.getY() == snakeHead.getY()) {
                isBiting = true;
                break;
            }
        }
        return isBiting;
    }
}
