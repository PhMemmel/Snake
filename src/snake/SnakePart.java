package snake;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SnakePart {
    private int x;
    private int y;

    public SnakePart() {

    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return Color.RED;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
