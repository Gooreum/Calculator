package com.example.goo.calculator.calculatorTask.tokenizer;


import com.example.goo.calculator.calculatorTask.function.Function;

/**
 * Created by Goo on 2019-08-03.
 */
public class FunctionToken extends Token {
    private final Function function;
    public FunctionToken(final Function function) {
        super(Token.TOKEN_FUNCTION);
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
