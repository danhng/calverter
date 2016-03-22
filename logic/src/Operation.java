import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 *
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         Date created: 19/03/2016
 */
public class Operation {

    private List<Evaluable> operands;

    private Operator operator;

    // todo fixme incompatible with Action interface
    private Evaluable result;

    class OperationBuilder {
        private List<Evaluable> operands;

        private Operator operator;

        public OperationBuilder() {
            //       operands = new ArrayList<>();
        }

        /**
         * Sets operands
         * @param operands the operands to be set
         * @return the {@code OperationBuilder} instance
         * @throws IllegalArgumentException if the operands can't be set due to a mismatch between input operand number
         * and expected operand number which could have been set already.
         * @throws NullPointerException if the operands collection is null
         */
        public OperationBuilder setOperands(Collection<Evaluable> operands) {
            if (operands != null && operands.size() != operator.getOperandsCount())
                Operator.throwMismatchOperandCount(operator.getName(), operator.getOperandsCount(), operands.size());
            if (operands == null)
                throw new NullPointerException("operands to be set cannot be null.");
            this.operands = new ArrayList<>(operands);
            return this;
        }

        /**
         * Sets the operator
         * @param operator the operator to be set
         * @return the {@code OperationBuilder} instance
         * @throws IllegalArgumentException if the operator can't be set due to a mismtach between required operand number
         * and actual operand number which could have been set already.
         * @throws NullPointerException if the operator to be set is null
         */
        public OperationBuilder setOperator(Operator operator) {
            this.operator = operator;
            if (operands != null && !operands.isEmpty() && operands.size() != operator.getOperandsCount())
                Operator.throwMismatchOperandCount(operator.getName(), operator.getOperandsCount(), operands.size());
            if(operator == null)
                throw new NullPointerException("operator to be set cannot be null");
            this.operator = operator;
            return this;
        }

        /**
         * Builds the operation
         * @return the {@code Operation} instance to be built
         *
         * @throws NullPointerException if the operands or operator is null
         * @throws IllegalArgumentException if the operator can't be set due to a mismatch between required operand number
         * in the operator and the operand number in the operands collection.
         */
        public Operation build() {
            if (operator == null)
                throw new NullPointerException("operator to be set cannot be null");
            if (operands == null)
                throw new NullPointerException("operands to be set cannot be null");
            if (!operands.isEmpty() && operands.size() != operator.getOperandsCount())
                Operator.throwMismatchOperandCount(operator.getName(), operator.getOperandsCount(), operands.size());
            return new Operation(operands, operator);
        }
    }

    private Operation(List<Evaluable> operands, Operator operator) {
        this.operands = operands;
        this.operator = operator;
    }

    public void associateOperand(Evaluable e) {
        checkIllegalModification();
        if (operands.size() >= operator.getOperandsCount())
        {
            Debug.warn("Can't associate any more operands. Operand count reached: %d\n", operator.getOperandsCount());
            return;
        }
        operands.add(e);
    }

    public boolean isOperationReadyForComputation() {
        return operands.size() == operator.getOperandsCount();
    }

    public Evaluable compute() {
        if (!isOperationReadyForComputation())
            return null;
        return result == null ? result = operator.actionDiscrete((Evaluable[]) operands.toArray()) : result;
    }

    public List<Evaluable> getOperands() {
        return operands;
    }

    public Operator getOperator() {
        return operator;
    }

    public Evaluable getResult() {
        return result;
    }

    private void checkIllegalModification() {
        if (result != null) {
            throw new IllegalStateException("Operation " + this + "has already been computed. No more modification is allowed.");
        }
    }
}