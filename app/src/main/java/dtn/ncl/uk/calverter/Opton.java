package dtn.ncl.uk.calverter;
import java.util.Objects;

import calverter.*;
import calverter.Number;

/**
 * Created by dtn on 11/05/2016.
 */
public class Opton {

    static enum Opton_Type {OPERAND, OPERATOR, CLEAR_ALL, CLEAR,
        PAREN_OPEN, PAREN_CLOSE,
        FLOAT, COMMIT, NULL};

    static Opton_Type[] calculatorLayout = {
            Opton_Type.OPERATOR, Opton_Type.OPERATOR, Opton_Type.OPERATOR,
            Opton_Type.OPERATOR, Opton_Type.OPERATOR, Opton_Type.OPERATOR,

            Opton_Type.NULL, Opton_Type.NULL, Opton_Type.NULL,
            Opton_Type.OPERATOR, Opton_Type.OPERATOR, Opton_Type.OPERATOR,

            Opton_Type.OPERAND, Opton_Type.OPERAND, Opton_Type.OPERAND,
            Opton_Type.OPERAND, Opton_Type.OPERAND, Opton_Type.OPERATOR,

            Opton_Type.OPERAND, Opton_Type.OPERAND, Opton_Type.OPERAND,
            Opton_Type.OPERAND, Opton_Type.OPERAND, Opton_Type.OPERATOR,

            Opton_Type.OPERAND, Opton_Type.OPERAND, Opton_Type.OPERAND,
            Opton_Type.OPERAND, Opton_Type.OPERAND, Opton_Type.OPERATOR,

            Opton_Type.PAREN_OPEN, Opton_Type.PAREN_CLOSE, Opton_Type.OPERATOR,
            Opton_Type.OPERAND, Opton_Type.FLOAT, Opton_Type.COMMIT,
    };

    static Opton[] CALCULATOR_OPTONS = {
            // row1
            new Opton(Operator.get(Operator.BITWISE_NOT), "Not"),
            new Opton(Operator.get(Operator.BITWISE_AND), "And"),
            new Opton(Operator.get(Operator.BITWISE_OR), "Or"),
            new Opton(Operator.get(Operator.BITWISE_XOR), "Xor"),
            new Opton(Operator.get(Operator.SHIFT_LEFT), "<<"),
            new Opton(Operator.get(Operator.SHIFT_RIGHT), ">>"),

//            // row 2
            new Opton(new Object(), "_"),
            new Opton(new Object(), "_"),
            new Opton(new Object(), "_"),
            new Opton(Expression.Clearance.getClear(), "C"),
            new Opton(Expression.Clearance.getClearAll(), "CE"),
            new Opton(Operator.get(Operator.DIVISION) , "/"),


//
//            //row 3
            new Opton(new Number(Number.HEX_MODE, "A"), "A"),
            new Opton(new Number(Number.HEX_MODE, "B"), "B"),
            new Opton(new Number(Number.DEC_MODE, "1"), "1"),
            new Opton(new Number(Number.DEC_MODE, "2"), "2"),
            new Opton(new Number(Number.DEC_MODE, "3"), "3"),
            new Opton(Operator.get(Operator.MULTIPLICATION), "*"),
//
            //row 4
            new Opton(new Number(Number.HEX_MODE, "C"), "C"),
            new Opton(new Number(Number.HEX_MODE, "D"), "D"),
            new Opton(new Number(Number.DEC_MODE, "4"), "4"),
            new Opton(new Number(Number.DEC_MODE, "5"), "5"),
            new Opton(new Number(Number.DEC_MODE, "6"), "6"),
            new Opton(Operator.get(Operator.SUBTRACTION), "-"),
//
//            // row 5
            new Opton(new Number(Number.HEX_MODE, "E"), "E"),
            new Opton(new Number(Number.HEX_MODE, "F"), "F"),
            new Opton(new Number(Number.DEC_MODE, "7"), "7"),
            new Opton(new Number(Number.DEC_MODE, "8"), "8"),
            new Opton(new Number(Number.DEC_MODE, "9"), "9"),
            new Opton(Operator.get(Operator.ADDITION), "+"),
//
//            // row 6
            new Opton(Expression.Parenthesis.getOpening(), "("),
            new Opton(Expression.Parenthesis.getClosing(), ")"),
            new Opton(Operator.get(Operator.NEGATION), "+/-"),
            new Opton(new Number(Number.DEC_MODE, "0"), "0"),
            new Opton(Expression.FloatPoint.get(), "."),
            new Opton(new Object(), "="),
    };

    private Object object;
    private String rep;

    public Opton(Object object, String rep) {
        this.object = object;
        this.rep = rep;
    }

    public Object getObject() {
        return object;
    }

    public String getRep() {
        return rep;
    }
}
