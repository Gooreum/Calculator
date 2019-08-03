package com.example.goo.calculator.calculatorTask.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Goo on 2019-08-03.
 */

public abstract class Operators {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final int INDEX_ADDITION = 0;
    private static final int INDEX_SUBTRACTION = 1;
    private static final int INDEX_MUTLIPLICATION = 2;
    private static final int INDEX_DIVISION = 3;
    private static final int INDEX_POWER = 4;
    private static final int INDEX_MODULO = 5;
    private static final int INDEX_UNARYMINUS = 6;
    private static final int INDEX_UNARYPLUS = 7;

    private static final Operator[] builtinOperators = new Operator[8];

    static {
        builtinOperators[INDEX_ADDITION] = new Operator("+", 2, true, Operator.PRECEDENCE_ADDITION) {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return args[0].add(args[1]);
            }
        };
        builtinOperators[INDEX_SUBTRACTION] = new Operator("-", 2, true, Operator.PRECEDENCE_ADDITION) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                return args[0].subtract(args[1]);
            }
        };
        builtinOperators[INDEX_UNARYMINUS] = new Operator("-", 1, false, Operator.PRECEDENCE_UNARY_MINUS) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                return args[0].negate();
            }
        };
        builtinOperators[INDEX_UNARYPLUS] = new Operator("+", 1, false, Operator.PRECEDENCE_UNARY_PLUS) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                return args[0].abs();
            }
        };
        builtinOperators[INDEX_MUTLIPLICATION] = new Operator("*", 2, true, Operator.PRECEDENCE_MULTIPLICATION) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                return args[0].multiply(args[1]);
            }
        };
        builtinOperators[INDEX_DIVISION] = new Operator("/", 2, true, Operator.PRECEDENCE_DIVISION) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                if (args[1].compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero!");
                }
                return args[0].divide(args[1],3, RoundingMode.HALF_EVEN);
            }
        };
        builtinOperators[INDEX_POWER] = new Operator("^", 2, false, Operator.PRECEDENCE_POWER) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                return args[0].pow(args[1].intValue());
            }
        };
        builtinOperators[INDEX_MODULO] = new Operator("%", 2, true, Operator.PRECEDENCE_MODULO) {
            @Override
            public BigDecimal apply(final BigDecimal... args) {
                if (args[1].equals(BigDecimal.ZERO)) {
                    throw new ArithmeticException("Division by zero!");
                }
                return args[0].multiply(args[1]).divide(ONE_HUNDRED);
            }
        };
    }

    public static Operator getBuiltinOperator(final char symbol, final int numArguments) {
        switch (symbol) {
            case '+':
                if (numArguments != 1) {
                    return builtinOperators[INDEX_ADDITION];
                } else {
                    return builtinOperators[INDEX_UNARYPLUS];
                }
            case '-':
                if (numArguments != 1) {
                    return builtinOperators[INDEX_SUBTRACTION];
                } else {
                    return builtinOperators[INDEX_UNARYMINUS];
                }
            case '*':
                return builtinOperators[INDEX_MUTLIPLICATION];
            case '/':
                return builtinOperators[INDEX_DIVISION];
            case '^':
                return builtinOperators[INDEX_POWER];
            case '%':
                return builtinOperators[INDEX_MODULO];
            default:
                return null;
        }
    }

}
