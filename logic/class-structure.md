    Evaluable Interface
        evaluateInt();
        evaluateFloat();

    AbstractComponent
        String rep;

#    Superclass of Expression
        Constructor(String rep)
        Parse
        isSingleton();
        isReadilyComputable();
        LinkedList of Expression and Operator
        LinkedList of Versions (Expression)

# Number is subclass of Expression
    isSingleton -> true
    isReadilyComputable() -> true
    evaluateInt -> iVal
    evaluateFloat ->fVal

# Operation
    public constructors (operator);
    int number of required operands.
    isReady? //todo important
    abstract equals();
    toResult(); // todo use cache here!!

    BinaryOperation
    Left
    Right
    applied direction

    UnaryOperation
    Operand

#   Operator
> each operator got a **static** instance which can be reused.
    
    String rep;
    Priority;  // todo important! This drives which operations got executed first.
     // todo Operations with higher priority will get executed before low-priority operations.
     // todo the number of operations depends on the number of operators
     // todo decide the priority
       If not in any parentheses
            => priority = default;
       Else
            priority of parentheses (highest amongst defaults) x nested level + default

>> Whenever a new operator is input then we check if all old operators are satisfied. if yes then output the result as the new operator won't affect
the calculated result.

     // todo whenever we finished an operation we cache the result.
     // todo => if we ever have to redo it we know it's already been done.
     => is this faster than doing the calculation again? (1 + 1)
     => //todo this is a to-do at first time use (is splash page)

     // todo is there a way to partially CACHE the result?




