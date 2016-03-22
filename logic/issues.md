#What to do in loading screen?
- Work out how many digits would fit in to the screen width -> 
	Used to display big exponential results appropriately.
- Work out number limits:
	MIN MAX in dec mode
	MIN MAX in float mode

Operations on floating point number?
- Bin, Oct, Hex experimental with floating point.

- //todo when to cache?
    (It would not be very efficient to cache small valued computations)
    => we need to carry out tests to see the device's capability (to what level of computation does the response time 
    exceeds an acceptable value i.e 10ms?)
    // demo. device is at different states so cpus might be busy? carry out test every load time?

###Orders of operators (according to the C programming language) 
    Inner most parentheses
    Outer most parentheses
    !, --
    * / %
    + -
    << >>
    &
    ^
    |

>> 5_+_3_x_2_-_!_2_+_(_3_<<_2_)_%_4

>> 5_+_3_x_2_-_!_(_2_)_+_(_3_<<_(_2_+_1_)_)_%_4_#

Every operators must have the number of adjacent EXPRESSIONS as required.
A binary operator can't be followed and preceded by a binary operator.
A unary operator must be followed by either an expression, another unary operator.
A unary operator cannot be preceded by an operand
An expression MUST START WITH either an operand, an unary operator or a parenthesis
        and END WITH the closest non-nested closing parenthesis.
Any expression can't be followed or preceded immediately by another expression.
    Parenthesis can't be followed by an opposite parenthesis
    Any two numeric expressions can't be adjacent.

    Any valid expression must be of either of the following forms:
        - A valid numeric expression without any operator
        - If operators are present then
            For each operator
                the number of operands must conform to the required specification of that operator in terms of card operands and positions.

## Every operand proba-associates to at most 2 operators
    find until found those two or hit end #.
    If the same line of precedence then LEFT takes precedence.

>>An operator will be activated (Calculated) if:
    It has got the number of required asssociated operands (either 1 for unary and 2 for binary)
    ALL of these operands ARE SINGLETONs.
    Activate => Expression/Singleton/Value E
        Replace The whole operation with new E in the input chain. (Or make another accumylative version).

##    Operator
    Type: /Unary/Binary
    Required operands : 1/2 (Expression of type Singleton and value is not NAN)

Rules
 any binary operators must have a leading operand and a trailing operand and must specify the associativity direction
 any unary operators must be associated with an operand which is either right after or before it without any other operator between.
 and must specify the associativity direction
 parentheses can only be opened after an operator and not an operand. i.e. +( !( <<( ok, 2(, 212(, -0(
 parentheses can only be closed after an operand and not an operator.

 -x with x being some int may be represented as (0-x)?

Expression
    rep / String
    type Singleton/Complex
    value = NAN
    LinkedList of Component
        Expression
        Operator
            Type
                Unary
                Binary
            Associativity
        ...

innermost parentheses contain values only

BlockExpression
    Operator, Operand...
    return Operand

    // recursive ?
    run until operand.expression.size == 1 and valid

