package com.example.goo.calculator.calculatorTask.function;


import java.math.BigDecimal;

/**
 * Created by Goo on 2019-08-03.
 */


public class Functions {
    private static final int INDEX_SIN = 0;
    private static final int INDEX_COS = 1;
    private static final int INDEX_TAN = 2;
    private static final int INDEX_LOG = 3;
    private static final int INDEX_LOG1P = 4;
    private static final int INDEX_ABS = 5;
    private static final int INDEX_ACOS = 6;
    private static final int INDEX_ASIN = 7;
    private static final int INDEX_ATAN = 8;
    private static final int INDEX_CBRT = 9;
    private static final int INDEX_CEIL = 10;
    private static final int INDEX_FLOOR = 11;
    private static final int INDEX_SINH = 12;
    private static final int INDEX_SQRT = 13;
    private static final int INDEX_TANH = 14;
    private static final int INDEX_COSH = 15;
    private static final int INDEX_POW = 16;
    private static final int INDEX_EXP = 17;
    private static final int INDEX_EXPM1 = 18;
    private static final int INDEX_LOG10 = 19;
    private static final int INDEX_LOG2 = 20;

    private static final Function[] builtinFunctions = new Function[21];

    static {
        builtinFunctions[INDEX_SIN] = new Function("sin") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.sin(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_COS] = new Function("cos") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.cos(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_TAN] = new Function("tan") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.tan(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_LOG] = new Function("log") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.log(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_LOG2] = new Function("log2") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.log1p(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_LOG10] = new Function("log10") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.log10(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_LOG1P] = new Function("log1p") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.log1p(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_ABS] = new Function("abs") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.abs(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_ACOS] = new Function("acos") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.acos(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_ASIN] = new Function("asin") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.asin(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_ATAN] = new Function("atan") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.atan(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_CBRT] = new Function("cbrt") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.cbrt(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_FLOOR] = new Function("floor") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.floor(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_SINH] = new Function("sinh") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.sinh(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_SQRT] = new Function("sqrt") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.sqrt(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_TANH] = new Function("tanh") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.tanh(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_COSH] = new Function("cosh") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.cosh(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_CEIL] = new Function("ceil") {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.ceil(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_POW] = new Function("pow", 2) {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.pow(args[0].doubleValue(), 2));
            }
        };
        builtinFunctions[INDEX_EXP] = new Function("exp", 1) {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.exp(args[0].doubleValue()));
            }
        };
        builtinFunctions[INDEX_EXPM1] = new Function("expm1", 1) {
            @Override
            public BigDecimal apply(BigDecimal... args) {
                return BigDecimal.valueOf(Math.expm1(args[0].doubleValue()));
            }
        };
    }

    /**
     * Get the builtin function for a given name
     *
     * @param name te name of the function
     * @return a Function instance
     */
    public static Function getBuiltinFunction(final String name) {

        if (name.equals("sin")) {
            return builtinFunctions[INDEX_SIN];
        } else if (name.equals("cos")) {
            return builtinFunctions[INDEX_COS];
        } else if (name.equals("tan")) {
            return builtinFunctions[INDEX_TAN];
        } else if (name.equals("asin")) {
            return builtinFunctions[INDEX_ASIN];
        } else if (name.equals("acos")) {
            return builtinFunctions[INDEX_ACOS];
        } else if (name.equals("atan")) {
            return builtinFunctions[INDEX_ATAN];
        } else if (name.equals("sinh")) {
            return builtinFunctions[INDEX_SINH];
        } else if (name.equals("cosh")) {
            return builtinFunctions[INDEX_COSH];
        } else if (name.equals("tanh")) {
            return builtinFunctions[INDEX_TANH];
        } else if (name.equals("abs")) {
            return builtinFunctions[INDEX_ABS];
        } else if (name.equals("log")) {
            return builtinFunctions[INDEX_LOG];
        } else if (name.equals("log10")) {
            return builtinFunctions[INDEX_LOG10];
        } else if (name.equals("log2")) {
            return builtinFunctions[INDEX_LOG2];
        } else if (name.equals("log1p")) {
            return builtinFunctions[INDEX_LOG1P];
        } else if (name.equals("ceil")) {
            return builtinFunctions[INDEX_CEIL];
        } else if (name.equals("floor")) {
            return builtinFunctions[INDEX_FLOOR];
        } else if (name.equals("sqrt")) {
            return builtinFunctions[INDEX_SQRT];
        } else if (name.equals("cbrt")) {
            return builtinFunctions[INDEX_CBRT];
        } else if (name.equals("pow")) {
            return builtinFunctions[INDEX_POW];
        } else if (name.equals("exp")) {
            return builtinFunctions[INDEX_EXP];
        } else if (name.equals("expm1")) {
            return builtinFunctions[INDEX_EXPM1];
        } else {
            return null;
        }
    }

}
