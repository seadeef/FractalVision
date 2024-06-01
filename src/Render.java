import java.awt.image.*;
import java.awt.Color;

/**
 * Class used to generate a visual image of a Fractal object
 */
public class Render {
    private int width;
    private int height;
    private double zoom;
    private Fractal fractal;
    private double centerx;
    private double centery;

    /**
     * Constructs new Render object with default center
     * @param width the width of the generated image in pixels
     * @param height the height of the generated image in pixels
     * @param zoom the zoom magnitude of the image around the center
     * @param fractal the Fractal object used to calculate colors
     */
    public Render(int width, int height, double zoom, Fractal fractal) {
        this.width = width;
        this.height = height;
        this.zoom = zoom;
        this.fractal = fractal;
        this.centerx = 0;
        this.centery = 0;
    }

    /**
     * Constructs new Render object with custom center
     * @param width the width of the generated image in pixels
     * @param height the height of the generated image in pixels
     * @param zoom the zoom magnitude of the image around the center
     * @param fractal the Fractal object used to calculate colors
     * @param centerx the x coordinate of the center point
     * @param centery the y coordinate of the center point
     */
    public Render(int width, int height, double zoom, Fractal fractal, double centerx, double centery) {
        this(width, height, zoom, fractal);
        this.centerx = centerx;
        this.centery = centery;
    }

    /**
     * Set the center of the image
     * @param x the center x coordinate
     * @param y the center y coordinate
     */
    public void setCenter(double x, double y) {
        centerx = x;
        centery = y;
    }

    /**
     * Creates an image of the fractal with correct width and height
     * given the current zoom and center
     * @return the image
     */
    public BufferedImage getImage() {
        double pixelRatio = (fractal.getRange()/width)*(1/zoom);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double real = centerx + ((x-width/2)*pixelRatio);
                double imaginary = centery + (-1*((y-height/2)*pixelRatio));
                img.setRGB(x, y, fractal.getColor(real, imaginary));
            }
        }
        return img;
    }

    /**
     * Get the width of the image in pixels
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the image in pixels
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the zoom magnitude
     * @return zoom
     */
    public double getZoom() {
        return zoom;
    }

    /**
     * Sets the zoom magnitude
     * @param zoom the new zoom
     */
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    /**
     * Get the fractal object
     * @return the fractal
     */
    public Fractal getFractal() {
        return fractal;
    }

    /**
     * Get the x coordinate of the center
     * @return centerx
     */
    public double getCenterx() {
        return centerx;
    }

    /**
     * Get the y coordinate of the center
     * @return centery
     */
    public double getCentery() {
        return centery;
    }
}
