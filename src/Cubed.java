import org.apache.commons.numbers.complex.Complex;

/**
 * Represents the complex function z^3-1 and its derivative
 */
public class Cubed implements Function {
    /**
     * Calculate num^3-1
     * @param num the complex number
     * @return the result
     */
    @Override
    public Complex calc(Complex num) {
        return num.pow(3).subtract(1);
    }

    /**
     * Calculate the derivative 3*num^2
     * @param num the complex number
     * @return the result
     */
    public Complex calcDerivative(Complex num) {
        return num.pow(2).multiply(3);
    }

    /**
     * Text description of the function
     * @return description
     */
    public String description() {
        return "z^3";
    }
}
