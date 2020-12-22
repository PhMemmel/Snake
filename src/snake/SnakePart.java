package snake;

import java.awt.*;
import java.util.Random;

public class SnakePart {
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private Color color;


    /**
     * constructor only for creating the initial snake
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

    /**
     * contructor for appending new snake parts to an already existing snake
     */
    public SnakePart() {

    }

    /**
     * setting position of snake part, this method saves current position before setting new
     *
     * @param x new x position
     * @param y new y position
     */
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

    /**
     * get x position before current x position
     *
     * @return x position before current x position
     */
    public int getOldX() {
        return oldX;
    }


    /**
     * get y position before current y position
     *
     * @return y position before current y position
     */
    public int getOldY() {
        return oldY;
    }

}
