import org.apache.commons.numbers.complex.Complex;

/**
 * Represents the complex function sinh(z)-1 and its derivative
 */
public class Sinh implements Function {

    /**
     * Calculate sinh(num)-1
     * @param num the complex number
     * @return the result
     */

    @Override
    public Complex calc(Complex num) {
        return num.sinh().subtract(1);
    }

    /**
     * Calculate the derivative cosh(z)
     * @param num the complex number
     * @return the result
     */
    public Complex calcDerivative(Complex num) {
        return num.cosh();
    }

    /**
     * Text description of the function
     * @return description
     */
    public String description() {
        return "Sinh(z)";
    }
}
