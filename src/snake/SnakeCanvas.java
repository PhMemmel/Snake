package snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SnakeCanvas extends Canvas implements KeyListener {

    private final int gridSize;
    private final Frog frog;
    private int paintedWidth;
    private int paintedHeight;
    private int lastKey;

    Snake snake;

    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);


    public SnakeCanvas(int gridSize, Snake snake, Frog frog) {
        this.gridSize = gridSize;
        this.snake = snake;
        this.frog = frog;
        setBackground(Color.lightGray);
        addKeyListener(this);
    }

    public void init() {
        paintedWidth = getWidth() - (getWidth() % gridSize);
        paintedHeight = getHeight() - (getHeight() % gridSize);
    }

    @Override
    public void paint(Graphics g) {


        Graphics2D g2 = (Graphics2D) g;

        /*g2.setColor(Color.gray);
        // paint horizontal lines
        for (int i=0; i<paintedHeight; i++) {
            g2.draw(new Line2D.Double(0, i*gridSize, paintedWidth, i*gridSize));
        }
        // paint vertical lines
        for (int i=0; i<paintedWidth; i++) {
            g2.draw(new Line2D.Double(i*gridSize, 0, i*gridSize, paintedHeight));
        }*/


        for (SnakePart snakePart : snake.getSnakeParts()) {
            g2.setColor(snakePart.getColor());
            g2.draw(new Ellipse2D.Double(snakePart.getX() * gridSize, snakePart.getY() * gridSize, gridSize, gridSize));
            g2.fill(new Ellipse2D.Double(snakePart.getX() * gridSize, snakePart.getY() * gridSize, gridSize, gridSize));
        }

        g2.setColor(frog.getColor());
        g2.draw(new Ellipse2D.Double(frog.getX() * gridSize, frog.getY() * gridSize, gridSize, gridSize));
        g2.fill(new Ellipse2D.Double(frog.getX() * gridSize, frog.getY() * gridSize, gridSize, gridSize));

    }

    public void update() {


        repaint();
        paint(getGraphics());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // avoid reacting to other keys than arrow keys
        if (e.getKeyCode() != KeyEvent.VK_UP
                && e.getKeyCode() != KeyEvent.VK_DOWN
                && e.getKeyCode() != KeyEvent.VK_LEFT
                && e.getKeyCode() != KeyEvent.VK_RIGHT) {
            return;
        }


        if (lastKey != e.getKeyCode()) {
            changes.firePropertyChange("key", lastKey, e.getKeyCode());
            lastKey = e.getKeyCode();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public int getGridWidth() {
        return paintedWidth / gridSize;
    }

    public int getGridHeight() {
        return paintedHeight / gridSize;
    }
}
