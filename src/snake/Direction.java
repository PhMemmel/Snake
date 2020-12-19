package snake;

import java.awt.event.KeyEvent;

public enum Direction {
    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT);

    private final int value;

    private Direction(int value) {
        this.value = value;
    }

    public static Direction getDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                return UP;
            case KeyEvent.VK_DOWN:
                return DOWN;
            case KeyEvent.VK_LEFT:
                return LEFT;
            case KeyEvent.VK_RIGHT:
                return RIGHT;
        }
        return null;
    }

}
