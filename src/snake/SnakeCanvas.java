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

    /**
     * has to be called after being created immediately after object has been created
     * <p>
     * neccessary to determine values not accessible while instantiating process
     */
    public void init() {
        paintedWidth = getWidth() - (getWidth() % gridSize);
        paintedHeight = getHeight() - (getHeight() % gridSize);
    }

    /*
     * main paint method, gets called by update() which is called by controller periodically
     */
    @Override
    public void paint(Graphics g) {


        Graphics2D g2 = (Graphics2D) g;

        /*
         * paint borders
         */
        g2.setColor(Color.gray);
        g2.draw(new Line2D.Double(0, 0, paintedWidth, 0));
        g2.draw(new Line2D.Double(0, 0, 0, paintedHeight));
        g2.draw(new Line2D.Double(0, paintedHeight, paintedWidth, paintedHeight));
        g2.draw(new Line2D.Double(paintedWidth, 0, paintedWidth, paintedHeight));

        /*
        // paint grid
        g2.setColor(Color.gray);
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
            g2.fill(new Ellipse2D.Double(snakePart.getX() * gridSize, snakePart.getY() * gridSize, gridSize, gridSize));
        }

        g2.setColor(frog.getColor());
        g2.fill(new Ellipse2D.Double(frog.getX() * gridSize, frog.getY() * gridSize, gridSize, gridSize));

    }

    /**
     * repaint the whole canvas after models have been updated
     */
    public void update() {
        repaint();
        paint(getGraphics());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*
     * gets called when a key is pressed on the canvas
     *
     * mainly used to determine if snake should change direction, fires off event to controller which then
     * forces the snake to change direction
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // avoid reacting to other keys than arrow keys
        // TODO this can be done better...
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
