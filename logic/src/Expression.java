import java.util.*;
import java.util.stream.Collectors;
/**
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         Date created: 21/03/2016
 */
public class Expression {

    private LinkedList<Expressible> internals_original;

    private LinkedList<Expressible> internals_mutated;

    private ListIterator<Expressible> iterator;

    private String rep;

    private boolean resolved = false;

    private Expression(String rep) {
        this.rep = rep;
        internals_original = new LinkedList<>();
        internals_mutated = new LinkedList<>(internals_original);
        iterator = internals_mutated.listIterator();
        resolved = isResolved_internal(internals_mutated);
    }


    //todo
    public static Expression parse(String rep) {
        Expression e = new Expression(rep);
        return null;
    }

    private boolean isResolved_internal(LinkedList<Expressible> e) {
        return e.size() == 1 && e.getFirst().toCategory() == Expressible.Category.OPERAND;
    }

    public boolean isResolved() {
        return isResolved_internal(internals_mutated);
    }

    private void locateExpressible(Expressible e) {
        iterator = internals_mutated.listIterator(internals_mutated.indexOf(e));
    }

    /**
     *
     * @param o
     * @return the index of the newly evaluated result.
     */
    private int replaceOperationByResult(Operation o) {
        if (!o.isOperationCompleted()) {
            throw new UnsupportedOperationException("Operation " + o + "is not completed. Hence the substitution can't proceed.");
        }
        ArrayList<Integer> indices = new ArrayList<>();

        // get indices of involved Expressible including the operator and all operands.
        indices.add(internals_mutated.indexOf(o.getOperator()));
        indices.addAll(o.getOperands().stream().map(e -> internals_mutated.indexOf(e)).collect(Collectors.toList()));
        Debug.debug("Indices of Expressible obs are: %s\n", Arrays.toString(indices.toArray()));
        Collections.sort(indices);
        Collections.reverse(indices);

        Debug.debug("Indices of Expressible obs sorted are: %s\n", Arrays.toString(indices.toArray()));
        // pivot
        int i = indices.get(indices.size() - 1);
        // add the result...
        internals_mutated.add(i, (Expressible) ((Number) o.getResult()));
        // we need to remove indices in descending order to prevent dynamic indices reallocation
        for (Integer k: indices)
            internals_mutated.remove(k + 1);
        Debug.debug("After operation is replaced: %s\n",toString());
        return i;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "internals=" + Arrays.toString(internals_mutated.stream().map(Expressible::toRep).collect(Collectors.toList()).toArray()) + "resolved=" + isResolved() +
                "}";
    }

    /**
     * Algorithm for resolving operators' priorities
     *
     * resolvePriority(Expression e) {
     *
     * }
     */

    /**
     * Algorithm for evaluating expressions with RESOLVED PRIORITIES
     *
     * compute(Expression e)
     *
     *
     * //todo
     * while e.internal.size != 1
     *  traverse through all elements
     *      Make a hash map (Operator, Operation) for each encountered operator
     *
     *  traverse through all elements
     *  if element is an operand
     *      Look behind and look ahead for neighbor operators.
     *
     *      If either neighbor is operand
     *          throw IllegalFormatException
     *
     *      Determine the operator to associate with
     *          (
     *              algorithm Left.compare(Right)
     *              if equals then Left wins
     *              return winning operator
     *          )
     *      Associate operand with the winning operator
     *          (
     *              Winning operator's operation.associate(operand)
     *          )
     *      If Winning operator is ready for computation
     *          do Computation with the operation of the operator
     *          (
     *              doComputation;
     *              return a number;
     *          )
     *          unlink operation and replace by the returned value.
     *          (
     *              remove operand(s), operator
     *              insert the number.
     *          )
     *          continue;
     *      Else element == next;
     *  else if element is an operator
     *      If operator is ready for computation
     *           do Computation with the operation of the operator
     *          (
     *              doComputation;
     *              return a number;
     *          )
     *          unlink operation and replace by the returned value.
     *          (
     *              remove operand(s), operator
     *              insert the number.
     *          )
     *          continue;
     *     Else
     *      element == next
     * end while;
     *
     * if e.internal[0].toCat = OPERAND
     *      return (Evaluable) e.internal[0];
     *  else if e.internal[0].toCat = OPERATOR
     *      throw IllegalFormatException
     *
     *
     */

    /**
     * Computes an valid expression
     * @param e the expression to be computed
     * @return the result
     *
     * @throws ClassCastException
     * @throws IllegalStateException
     */
    private Evaluable compute_internal(Expression e) {
        if (e.isResolved())
            return e.internals_mutated.getFirst().toEvaluable();
        HashMap<Operator, Operation> opsMap = new HashMap<>();
        for (Expressible expressible : e.internals_mutated) {
            if (expressible.toCategory() == Expressible.Category.OPERATOR) {
                opsMap.put((Operator) expressible, new Operation.OperationBuilder().setOperator((Operator) expressible).build());
            }
        }

        Expressible current = e.internals_mutated.getFirst();
        ListIterator<Expressible> iterator = e.internals_mutated.listIterator(0);
        while (!e.isResolved()) {
            int index = e.internals_mutated.indexOf(current);
            Debug.debug("**********Current Expressible: %s[%d]\n", current.toRep(), e.internals_mutated.indexOf(current));
            switch (current.toCategory()) {
                case OPERAND: {
                    List<Operator> neighbors = new ArrayList<>();
                    try {
                        if (index > 0) {
                            neighbors.add((Operator) internals_mutated.get(index - 1));
                        }
                        // advance past the current
                        //iterator.next();
                        if (index < internals_mutated.size() - 1) {
                            Debug.debug("Next is: %s\n", internals_mutated.get(index+1));
                            neighbors.add((Operator) internals_mutated.get(index+1));
                            //iterator.previous();
                        }
                    } catch (ClassCastException exception) {
                        exception.printStackTrace();
                        Debug.error(true, "Some neighbor of Operand %s[%d] is not Operator. Check again.\n", current.toRep(), e.internals_mutated.indexOf(current));
                    }

                    Operator winner;

                    switch (neighbors.size()) {
                        case 0:
                            continue;
                        case 1: {
                            winner = neighbors.get(0);
                            break;
                        }
                        case 2: {
                            Operator left = neighbors.get(0);
                            Operator right = neighbors.get(1);
                            int c = left.compareTo(right);
                            winner = c > 0 ? left : c < 0 ? right : left; // left takes precedence on equality
                            break;
                        }
                        default:
                            throw new IllegalArgumentException("Something wrong with Expression at " + e.internals_mutated.indexOf(current) + ": " + neighbors.size() + " neighbors");
                    }
                    Debug.debug("Winner at Operand %s[%d] is: Operator %s[%d]\n", current.toRep(), e.internals_mutated.indexOf(current), winner.toRep(), e.internals_mutated.indexOf(winner));

                    Operation operationWinner = opsMap.get(winner);
                    operationWinner.associateOperand(current.toEvaluable());

                    if (operationWinner.isOperationComputable()) {
                        Debug.debug("Operation of %s[%d] is ready to be computed. %s\n", winner.toRep(), e.internals_mutated.indexOf(winner), operationWinner.toString());
                        Evaluable r = operationWinner.compute();
                        Debug.debug("Operation of %s[%d] is computed. R is: %s\n", winner.toRep(), e.internals_mutated.indexOf(winner), r.toDecimal());
                        e.replaceOperationByResult(operationWinner);
                        Debug.debug("NEW EXPRESSION after operand is replaced: %s\n", e.toString());
                    }

                    // advance to the next Expressible or cycle back
                    current = internals_mutated.get(++index % internals_mutated.size());
                    continue;
                }
                case OPERATOR: {
                    Operation operationWinner = opsMap.get((Operator) current);
                    if (operationWinner.isOperationComputable()) {
                        Evaluable r = operationWinner.compute();
                        Debug.debug("Operation of %s[%d] is computed. R is: %s\n", current.toRep(), e.internals_mutated.indexOf(current), r.toDecimal());
                        e.replaceOperationByResult(operationWinner);
                        Debug.debug("NEW EXPRESSION: %s", e.toString());
                    }
                    // advance to the next Expressible or cycle back
                    current = internals_mutated.get(++index % internals_mutated.size());
                    break;
                }
            }
        }
        Debug.debug("Final internals: %s\n", e.toString());
        if (e.internals_mutated.getFirst().toCategory() == Expressible.Category.OPERAND)
            return (Evaluable) e.internals_mutated.getFirst().toEvaluable();
        else
            throw new IllegalStateException("Something went wrong after the computation.");
    }

    public Expression addExpressible(Expressible e) {
        internals_original.add(e);
        internals_mutated.add(e);
        return this;
    }

    public static void main(String[] args) {
       Expression e = new Expression("3 + 2 * 5");
        Number a = new Number(3);
        Number b = new Number(2);
        Operator plus = Operator.getOperator(Operator.ADDITION);
        Operator multi = Operator.getOperator(Operator.MULTIPLICATION);
        Number c = new Number(4);
        e.addExpressible(a);
        e.addExpressible(plus);
        e.addExpressible(b);
        e.addExpressible(multi);
        e.addExpressible(c);
        System.out.println(e.compute_internal(e));
    }
}
