package com.example.goo.calculator.calculatorTask.tokenizer;

//import net.objecthunter.exp4j.tokenizer.Token;

import com.example.goo.calculator.calculatorTask.tokenizer.Token;

/**
 * Created by Goo on 2019-08-03.
 */

public class VariableToken extends Token {
    private final String name;

    /**
     * Get the name of the setVariable
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new instance
     * @param name the name of the setVariable
     */
    public VariableToken(String name) {
        super(TOKEN_VARIABLE);
        this.name = name;
    }
}
