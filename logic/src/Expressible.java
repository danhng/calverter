/**
 * The interface {@code Expressible} represents objects that are allowed in a typical mathematical expression including:
 * <u>
 *     <li>Operands: see {@code Evaluable}</li>
 *     <li>Operators: see {@code Operator}</li>
 * </u>
 *
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         Date created: 21/03/2016
 */
public interface Expressible {

    /**
     * Any Expressible can take on only one category which is either an operand (OPERAND) or an operator (OPERATOR)
     */
    enum Category {OPERAND, OPERATOR};

    /**
     * @return the string representation of the Expressible
     */
    String toRep();

    /**
     * @return the category of the Expressible.
     */
    Category toCategory();

    /**
     * Attempts to cast the Expressible to an Evaluable
     * @return the cast Evaluable
     *
     * @throws  ClassCastException if the Expressible cannot be cast to Evaluable
     */
    Evaluable toEvaluable();

    /**
     * Attempts to cast the Expressible to an Operator
     * @return the cast Operator
     *
     * @throws ClassCastException if the Expressible cannot be cast to Operator
     */
    Operator toOperator();
}
