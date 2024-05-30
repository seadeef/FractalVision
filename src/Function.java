import org.apache.commons.numbers.complex.Complex;

/**
 * Represents a complex function and its derivative
 */
public interface Function {

    /**
     * Calculate the function at num
     * @param num the complex number
     * @return the reslt
     */
    Complex calc(Complex num);

    /**
     * Calculate the derivative at num
     * @param num the complex number
     * @return the result
     */
    Complex calcDerivative(Complex num);

    /**
     * Get description of the function
     * @return description
     */
    String description();
}
