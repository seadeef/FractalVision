import org.apache.commons.numbers.complex.Complex;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a root of a complex function
 */
final class Root {
    private final Complex point;
    private final int iterations;
    private final boolean exists;
    private final float hue;
    private final float saturation;

    /**
     * Constructs a new root
     * @param point the complex number where the root is located
     * @param iterations the number of iterations required to reach the root
     * @param hue randomly calculated hue for all points which converge to this root
     * @param saturation randomly calculated saturation for all points which converge to this root
     * @param exists false if point does not actually converge to a root
     */
    public Root(Complex point, int iterations, float hue, float saturation, boolean exists) {
        this.exists = exists;
        this.point = point;
        this.iterations = iterations;
        this.hue = hue;
        this.saturation = saturation;
    }

    /**
     * Gets the complex point where root is located
     * @return the point
     */
    public Complex getPoint() {
        return point;
    }

    /**
     * Gets the number of iterations required to reach the root
     * @return iterations
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * true if the point converges to a root, false if it doesn't
     * @return whether root exists
     */
    public boolean isExists() {
        return exists;
    }

    /**
     * Gets the hue for this root
     * @return hue
     */
    public float getHue() {
        return hue;
    }

    /** Gets the saturation for this root
     * @return saturation
     */
    public float getSaturation() {
        return saturation;
    }
}

/**
 *Class with utility methods to calculate the newton fractal
 * of a given function
 */
public class Newton extends Fractal {
    private Function func;
    ArrayList<Root> roots;
    private static final double TOL = 1.0e-8;
    private static final double MAX_ITER = 1000;

    /**
     * Constructs a newton fractal with custom range
     * @param func the complex function & derivative used to calculate fractal
     * @param range abs(xmax-xmin)
     */
    public Newton(Function func, double range) {
        super("Newton Fractal", range);
        this.func = func;
        roots = new ArrayList<Root>();
    }

    /**
     * Constructs a newton fractal with default range
     * @param func the complex function & derivative used to calculate fractal
     */
    public Newton(Function func) {
        this(func, 5);
    }

    /**
     * Returns a Root object that corresponds with the given complex point
     * NOTE: all root objects with the same complex point are identical except for
     * the amount of iterations (used for shading)
     * @param real the real part
     * @param imaginary the imaginary part
     * @return the root object
     */
    public Root findRoot(double real, double imaginary) {
        Complex z = Complex.ofCartesian(real, imaginary);
        Complex dz = null;
        Complex point = null;
        int count = 0;

        while (count<MAX_ITER) {
            dz = func.calc(z).divide(func.calcDerivative(z));
            z = z.subtract(dz);

            if (dz.abs() < TOL) {
                point = z;
                break;
            }

            count += 1;
        }

        if (point == null) {
            return new Root(null, 0, 0, 0, false); //Root doesnt exist
        }

        for (int i=0; i<roots.size(); i+=1) {
            Root storedRoot = roots.get(i);
            if (point.subtract(storedRoot.getPoint()).abs() < 4*TOL) {
                return new Root(storedRoot.getPoint(),
                                count,
                                storedRoot.getHue(),
                                storedRoot.getSaturation(),
                                true);
            }
        }

        float hue = (float) (Math.random()*361);
        float saturation = (float) (Math.random()*101);

        Root newRoot = new Root(point, count, hue, saturation, true);
        roots.add(newRoot);

        return newRoot;
    }

    /**
     * Get the function of the newton fractal
     * @return the function
     */
    public Function getFunc() {
        return func;
    }

    /**
     * Gets the color of the fractal at the given complex point
     * @param real the real part
     * @param imaginary the imaginary part
     * @return the color
     */
    @Override
    public int getColor(double real, double imaginary) {
        Root root = findRoot(real, imaginary);
        if (!root.isExists()) {
            return 0;
        }

        float brightness = (float) ((root.getIterations()/MAX_ITER)*100);
        int color = Color.HSBtoRGB(root.getHue(), root.getSaturation(), brightness);

        return color;
    }

    /**
     * String description of the fractal used for the CLI
     * @return description
     */
    @Override
    public String toString() {
        return super.toString() + " " + getFunc().description();
    }
}