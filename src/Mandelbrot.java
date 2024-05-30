import org.apache.commons.numbers.complex.Complex;

import java.awt.*;

/**
 * Class with utility functions for calculating a mandelbrot fractal
 */
public class Mandelbrot extends Fractal {
    private final static double MAX_ITER = 80;

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
     * Gets the amount of iterations required to diverge at complex point
     * Capped by MAX_ITER
     * @param real the real part
     * @param imaginary the imaginary part
     * @return number of iterations
     */
    public int getIters(double real, double imaginary) {
        double magnitude = 0;
        int count = 0;
        Complex z = Complex.ofCartesian(0, 0);
        Complex c = Complex.ofCartesian(real, imaginary);
        while (magnitude < 2.0 && count<MAX_ITER) {
            z = z.pow(2).add(c);
            magnitude = z.abs();
            count += 1;
        }

        return count;
    }

    /**
     * Calculates the color of the mandelbrot fractal at point
     * based on the number of iterations required to diverge
     * @param real the real part
     * @param imaginary the imaginary part
     * @return
     */
    @Override
    public int getColor(double real, double imaginary) {
        int n = getIters(real, imaginary);
       //https://en.wikipedia.org/wiki/Plotting_algorithms_for_the_Mandelbrot_set
        if (n == MAX_ITER) {
            return 0;
        }

        return Color.HSBtoRGB((float) Math.pow(((n/MAX_ITER)*360), 1.5)%360,
                              100F, (float) ((n/MAX_ITER)*100));

    }
}
