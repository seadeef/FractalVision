import java.awt.Color;

/**
 * Base class to represent a fractal
 */
abstract class Fractal {
    private String name;
    private double range;

    /**
     * Constructs a fractal object
     * @param name the name of the fractal
     * @param range abs(xmax-xmin)
     */
    public Fractal(String name, double range) {
        this.name = name;
        this.range = range;
    }

    /**
     * Gets the name of the fractal
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the range of the fractal abs(xmax-xmin)
     * @return range
     */
    public double getRange() {
        return range;
    }

    /**
     * Computes the color of the fractal at a complex point
     * @param real the real part
     * @param imaginary the imaginary part
     * @return the color
     */
    abstract int getColor(double real, double imaginary);

    /**
     * Returns a description of the fractal used in the CLI
     * (including the fractal name)
     * @return text description
     */
    public String toString() {
        return getName();
    }

}
