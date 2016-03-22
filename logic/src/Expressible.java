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
    enum Category {OPERAND, OPERATOR};

    String toRep();

    Category toCategory();
}
