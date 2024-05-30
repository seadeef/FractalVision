import org.apache.commons.numbers.complex.Complex;

/**
 * Represents the complex function sin(z)-1 and its derivative
 */
public class Sine implements Function {

    /**
     * Calculate sin(num)-1
     * @param num the complex number
     * @return the result
     */
    @Override
    public Complex calc(Complex num) {
        return num.sin().subtract(1);
    }

    /**
     * Calculate the derivative cos(z)
     * @param num the complex number
     * @return the result
     */
    public Complex calcDerivative(Complex num) {
        return num.cos();
    }

    /**
     * Text description of the function
     * @return description
     */
    public String description() {
        return "Sin(z)";
    }
}
