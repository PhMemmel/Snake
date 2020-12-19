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
    int lastKey;

    Snake snake;

    private PropertyChangeSupport changes = new PropertyChangeSupport( this );


    public SnakeCanvas(int gridSize, Snake snake) {
        this.gridSize = gridSize;
        this.snake = snake;
        setBackground(Color.lightGray);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        int paintedWidth = getWidth() - (getWidth() % gridSize);
        int paintedHeight = getHeight() - (getHeight() % gridSize);

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

        if (lastKey == e.getKeyCode()) {
            return;
        } else {
            changes.firePropertyChange("key", lastKey, e.getKeyCode() );
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void addPropertyChangeListener( PropertyChangeListener l )
    {
        changes.addPropertyChangeListener( l );
    }

    public void removePropertyChangeListener( PropertyChangeListener l )
    {
        changes.removePropertyChangeListener( l );
    }

}
