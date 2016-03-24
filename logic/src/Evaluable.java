import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The interface {@code Evaluable} represents any operand that is able to be evaluated numerically.
 *
 * <p>
 * Any class implementing this interface must provide implementations for producing a decimal value and an integer value as
 * a result of evaluation.<br/>
 * It is the responsibility of the implementors to ensure toDecimal() and toInteger() produces equivalent values, or
 * it should be made aware by users otherwise.
 *</p>
 *
 *
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         Date created: 19/03/2016
 */
public interface Evaluable {

    /**
     * Return the evaluated decimal.
     *
     * How the object is evaluated is entirely up to the implementation and it is the job of the implementor to make sure
     * the decimal number evaluated is correct.
     *
     * @return the decimal evaluated from the object
     */
    BigDecimal toDecimal();

    /**
     * Return the evaluated integer.
     *
     * How the object is evaluated is entirely up to the implementation and it is the job of the implementor to make sure
     * the integer evaluated is correct.
     *
     * @return the integer evaluated from the object
     */
    BigInteger toInteger();


}
