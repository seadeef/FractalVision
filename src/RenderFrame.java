import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Represents a GUI Frame that can display images from a Render object
 */
public class RenderFrame extends JFrame implements KeyListener{
    private Render draw;
    private JLabel image;

    /**
     * Unused event handler for key presses
     * @param e the KeyEvent
     */
    public void keyPressed(KeyEvent e) {
        return;
    }

    /**
     * Event handler for key releases
     * Will pan or zoom the image given user input
     * @param e the KeyEvent
     */
    public void keyReleased(KeyEvent e) {
        double increment = (0.125)*(draw.getFractal().getRange()/draw.getZoom());
        if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
            draw.setCenter(draw.getCenterx() + increment, draw.getCentery());
        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            draw.setCenter(draw.getCenterx() - increment, draw.getCentery());
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
            draw.setCenter(draw.getCenterx(), draw.getCentery() - increment);
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            draw.setCenter(draw.getCenterx(), draw.getCentery()+increment);
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            draw.setCenter(draw.getCenterx(), draw.getCentery()+increment);
        } else if (e.getKeyCode()== KeyEvent.VK_COMMA) {
            draw.setZoom(draw.getZoom()*0.75);
        } else if (e.getKeyCode()== KeyEvent.VK_PERIOD) {
            draw.setZoom(draw.getZoom()*1.25);
        }
        image.setIcon(new ImageIcon(draw.getImage()));
        revalidate();
        repaint();
    }

    /**
     * Unused event handler for key type
     * @param e the KeyEvent
     */
    public void keyTyped(KeyEvent e) {return;}

    /**
     * Constructs a new RenderFrame with given Render object
     * @param draw the Render object
     */
    public RenderFrame(Render draw){
        this.draw = draw;
        this.image = new JLabel();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    /**
     * Starts the draw loop and displays the fractal
     */
    public void start() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setTitle("FractalVision");
                setResizable(false);
                setSize(draw.getWidth(), draw.getHeight());
                setMinimumSize(new Dimension(draw.getWidth(), draw.getHeight()));
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                getContentPane().add(image);
                image.setIcon(new ImageIcon(draw.getImage()));
                pack();
                setVisible(true);
            }
        });
    }
}
