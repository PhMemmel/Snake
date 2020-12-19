package snake;

import java.util.ArrayList;
import java.util.Iterator;

public class Snake {

    ArrayList<SnakePart> snakeParts;
    private Direction direction;


    public Snake() {
        direction = Direction.RIGHT;
        snakeParts = new ArrayList<>();
        SnakePart snakePart = new SnakePart();
        snakePart.setPosition(4, 2);
        snakeParts.add(snakePart);
        snakePart = new SnakePart();
        snakePart.setPosition(3, 2);
        snakeParts.add(snakePart);
        snakePart = new SnakePart();
        snakePart.setPosition(2, 2);
        snakeParts.add(snakePart);
    }


    public void move() {

        Iterator<SnakePart> iterator = snakeParts.iterator();
        SnakePart snakeHead = iterator.next();
        int snakeHeadX = snakeHead.getX();
        int snakeHeadY = snakeHead.getY();
        switch (direction) {
            case UP:
                snakeHead.setY(snakeHead.getY() - 1);
                break;
            case DOWN:
                snakeHead.setY(snakeHead.getY() + 1);
                break;
            case LEFT:
                snakeHead.setX(snakeHead.getX() - 1);
                break;
            case RIGHT:
                snakeHead.setX(snakeHead.getX() + 1);
        }
        SnakePart secondSnakePart = iterator.next();
        int lastPositionX = secondSnakePart.getX();
        int lastPositionY = secondSnakePart.getY();
        secondSnakePart.setX(snakeHeadX);
        secondSnakePart.setY(snakeHeadY);
        while (iterator.hasNext()) {
            SnakePart snakePart = iterator.next();

            snakePart.setX(lastPositionX);
            snakePart.setY(lastPositionY);
            lastPositionX = snakePart.getX();
            lastPositionY = snakePart.getY();
        }


    }

    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void setDirection(int keyCode) {
        this.direction = Direction.getDirection(keyCode);
    }
}
