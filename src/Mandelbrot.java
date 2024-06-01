import org.apache.commons.numbers.complex.Complex;

import java.awt.*;

/**
 * Class used to represent an escape point of the mandelbrot set
 */
final class Escape {
    private Complex z;
    private int n;

    /**
     * Constructs a new Escape point
     * @param z the complex point where it diverged
     * @param n the amount of iterations required to reach divergence
     */
    public Escape(Complex z, int n) {
        this.z = z;
        this.n = n;
    }

    /**
     * Get the escape point
     * @return escape point
     */
    public Complex getZ() {
        return z;
    }

    /**
     * Get the iterations
     * @return iterations
     */
    public int getN() {
        return n;
    }
}

/**
 * Class with utility functions for calculating a mandelbrot fractal
 */
public class Mandelbrot extends Fractal {
    private final static double MAX_ITER = 100;
    private final static double LN_2 = Math.log(2);

    /**
     * Constructs a mandelbrot fractal with custom range
     * @param range abs(xmax-xmin)
     */
    public Mandelbrot(double range) {
        super("Mandelbrot", range);
    }

    /**
     * Constructs mandelbrot fractal with default range
     */
    public Mandelbrot() {
        super("Mandelbrot", 5);
    }

    /**
     * Returns an Escape object representing info about the Mandelbrot set
     * at the complex point
     * @param real the real part
     * @param imaginary the imaginary part
     * @return Escape object
     */
    public Escape getEscape(double real, double imaginary) {
        double magnitude = 0;
        int count = 0;
        Complex z = Complex.ofCartesian(0, 0);
        Complex c = Complex.ofCartesian(real, imaginary);
        while (magnitude < 2.0 && count<MAX_ITER) {
            z = z.pow(2).add(c);
            magnitude = z.abs();
            count += 1;
        }

        return new Escape(z, count);
    }

    /**
     * Linear interpolation function
     * @param a start
     * @param b end
     * @param f range
     * @return output
     */
    public double lerp(double a, double b, double f)
    {
        return (a * (1.0 - f) + (b * f));
    }

    /**
     * Smooth coloring function for the mandelbrot set
     * @param real the real part
     * @param imaginary the imaginary part
     * @return the computed color at the imaginary point
     */
    @Override
    public int getColor(double real, double imaginary) {
        Escape p = getEscape(real, imaginary);
       //https://en.wikipedia.org/wiki/Plotting_algorithms_for_the_Mandelbrot_set

        int n = p.getN();
        double zr = p.getZ().getReal();
        double zi = p.getZ().getImaginary();

        if (n == MAX_ITER) {
            return 0;
        }

        double log_zn = Math.log(zr*zr + zi*zi) / 2;
        double nu = Math.log(log_zn / LN_2) / LN_2;
        double i = n + 1 - nu;

        Color c1 = new Color((int) Math.pow(Math.floor(i), 1.5)%255,
                           (int) Math.pow(Math.floor(i), 1.7)%255,
                           (int) Math.pow(Math.floor(i), 1.9)%255);

        Color c2 = new Color((int) Math.pow(Math.floor(i)+1, 1.5)%255,
                           (int) Math.pow(Math.floor(i)+1, 1.7)%255,
                           (int) Math.pow(Math.floor(i)+1, 1.9)%255);



        return new Color(
                (int) lerp(c1.getRed(), c2.getRed(), i%1),
                (int) lerp(c1.getGreen(), c2.getGreen(), i%1),
                (int) lerp(c1.getBlue(), c2.getBlue(), i%1)
        ).getRGB();
    }
}
