package snake;

import java.awt.*;
import java.util.Random;

public class SnakePart {
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private Color color;

    public SnakePart() {

    }

    /**
     * constructor only for head of snake
     *
     * @param startX starting x position for snake head
     * @param startY starting y position for snake head
     */
    public SnakePart(int startX, int startY) {
        setPosition(startX, startY);
        oldX = startX - 1;
        oldY = startY;

        Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE};
        color = colors[new Random().nextInt(colors.length)];
    }

    public void setPosition(int x, int y) {
        oldX = this.x;
        oldY = this.y;
        this.x = x;
        this.y = y;
    }


    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

}
