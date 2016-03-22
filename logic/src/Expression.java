import sun.awt.image.ImageWatched;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         Date created: 21/03/2016
 */
public class Expression {

    private LinkedList<Expressible> internals;

    private Expression r;

    private String rep;


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


}
