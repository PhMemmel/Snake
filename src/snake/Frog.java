package snake;

import java.awt.Color;
import java.util.Random;

public class Frog {

    private int x;
    private int y;

    public Frog() {

    }

    public void setRandomPosition(int maxX, int maxY) {
        Random random = new Random();
        x = random.nextInt(maxX);
        y = random.nextInt(maxY);
    }

    public Color getColor() {
        return Color.GREEN;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
