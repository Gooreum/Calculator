package com.example.goo.calculator.calculatorTask;

/*
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.tokenizer.FunctionToken;
import net.objecthunter.exp4j.tokenizer.NumberToken;
import net.objecthunter.exp4j.tokenizer.OperatorToken;
import net.objecthunter.exp4j.tokenizer.Token;
import net.objecthunter.exp4j.tokenizer.VariableToken;
*/

import com.example.goo.calculator.calculatorTask.function.Function;
import com.example.goo.calculator.calculatorTask.operator.Operator;
import com.example.goo.calculator.calculatorTask.tokenizer.FunctionToken;
import com.example.goo.calculator.calculatorTask.tokenizer.NumberToken;
import com.example.goo.calculator.calculatorTask.tokenizer.OperatorToken;
import com.example.goo.calculator.calculatorTask.tokenizer.Token;
import com.example.goo.calculator.calculatorTask.tokenizer.VariableToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Goo on 2019-08-02.
 */

public class Expression {

    private final Token[] tokens;

    private final Map<String, BigDecimal> variables;

    private final Set<String> userFunctionNames;


    Expression(final Token[] tokens) {
        this.tokens = tokens;
        this.variables = new HashMap<String, BigDecimal>(4);
        this.userFunctionNames = Collections.<String>emptySet();
    }

    Expression(final Token[] tokens, Set<String> userFunctionNames) {
        this.tokens = tokens;
        this.variables = new HashMap<String, BigDecimal>(4);
        this.userFunctionNames = userFunctionNames;
    }

    public Expression setVariable(final String name, final BigDecimal value) {
        this.checkVariableName(name);
        this.variables.put(name, value);
        return this;
    }

    private void checkVariableName(String name) {
        if (this.userFunctionNames.contains(name)) {
            throw new IllegalArgumentException("The setVariable name '" + name + "' is invalid. Since there exists a function with the same name");
        }
    }

    public Expression setVariables(Map<String, BigDecimal> variables) {
        for (Map.Entry<String, BigDecimal> v : variables.entrySet()) {
            this.setVariable(v.getKey(), v.getValue());
        }
        return this;
    }

    public ValidationResult validate(boolean checkVariablesSet) {
        final List<String> errors = new ArrayList<String>(0);
        if (checkVariablesSet) {
            /* check that all vars have a value set */
            for (final Token t : this.tokens) {
                if (t.getType() == Token.TOKEN_VARIABLE) {
                    final String var = ((VariableToken) t).getName();
                    if (!variables.containsKey(var)) {
                        errors.add("The setVariable '" + var + "' has not been set");
                    }
                }
            }
        }

        /* Check if the number of operands, functions and operators match.
           The idea is to increment a counter for operands and decrease it for operators.
           When a function occurs the number of available arguments has to be greater
           than or equals to the function's expected number of arguments.
           The count has to be larger than 1 at all times and exactly 1 after all tokens
           have been processed */
        int count = 0;
        for (Token tok : this.tokens) {
            switch (tok.getType()) {
                case Token.TOKEN_NUMBER:
                case Token.TOKEN_VARIABLE:
                    count++;
                    break;
                case Token.TOKEN_FUNCTION:
                    Function func = ((FunctionToken) tok).getFunction();
                    //final Function func = ((FunctionToken) tok).getFunction();
                    if (func.getNumArguments() > count) {
                        errors.add("Not enough arguments for '" + func.getName() + "'");
                    }
                    break;
                case Token.TOKEN_OPERATOR:
                    Operator op = ((OperatorToken) tok).getOperator();
                    if (op.getNumOperands() == 2) {
                        count--;
                    }
                    break;
            }
            if (count < 1) {
                errors.add("Too many operators");
                return new ValidationResult(false, errors);
            }
        }
        if (count > 1) {
            errors.add("Too many operands");
        }
        return errors.size() == 0 ? ValidationResult.SUCCESS : new ValidationResult(false, errors);

    }

    public ValidationResult validate() {
        return validate(true);
    }

    public Future<BigDecimal> evaluateAsync(ExecutorService executor) {
        return executor.submit(new Callable<BigDecimal>() {
            @Override
            public BigDecimal call() throws Exception {
                return evaluate();
            }
        });
    }

    public BigDecimal evaluate() {
        final Stack<BigDecimal> output = new Stack<BigDecimal>();
        for (int i = 0; i < tokens.length; i++) {
            Token t = tokens[i];
            if (t.getType() == Token.TOKEN_NUMBER) {
                output.push(BigDecimal.valueOf(((NumberToken) t).getValue()));
            } else if (t.getType() == Token.TOKEN_VARIABLE) {
                final String name = ((VariableToken) t).getName();
                final BigDecimal value = this.variables.get(name);
                if (value == null) {
                    throw new IllegalArgumentException("No value has been set for the setVariable '" + name + "'.");
                }
                output.push(value);
            } else if (t.getType() == Token.TOKEN_OPERATOR) {
                OperatorToken op = (OperatorToken) t;
                if (output.size() < op.getOperator().getNumOperands()) {
                    throw new IllegalArgumentException("Invalid number of operands available");
                }
                if (op.getOperator().getNumOperands() == 2) {
                    /* pop the operands and push the result of the operation */
                    BigDecimal rightArg = output.pop();
                    BigDecimal leftArg = output.pop();
                    output.push(BigDecimal.valueOf(op.getOperator().apply(leftArg.doubleValue(), rightArg.doubleValue())));
                } else if (op.getOperator().getNumOperands() == 1) {
                    /* pop the operand and push the result of the operation */
                    BigDecimal arg = output.pop();
                    output.push(BigDecimal.valueOf(op.getOperator().apply(arg.doubleValue())));
                }
            } else if (t.getType() == Token.TOKEN_FUNCTION) {
                FunctionToken func = (FunctionToken) t;
                if (output.size() < func.getFunction().getNumArguments()) {
                    throw new IllegalArgumentException("Invalid number of arguments available");
                }
                /* collect the arguments from the stack */
                BigDecimal[] args = new BigDecimal[func.getFunction().getNumArguments()];
                for (int j = 0; j < func.getFunction().getNumArguments(); j++) {
                    args[j] = output.pop();
                }
                output.push(func.getFunction().apply(this.reverseInPlace(args)));
            }
        }
        if (output.size() > 1) {
            throw new IllegalArgumentException("Invalid number of items on the output queue. Might be caused by an invalid number of arguments for a function.");
        }
        return output.pop();
    }

    private BigDecimal[] reverseInPlace(BigDecimal[] args) {
        int len = args.length;
        for (int i = 0; i < len / 2; i++) {
            BigDecimal tmp = args[i];
            args[i] = args[len - i - 1];
            args[len - i - 1] = tmp;
        }
        return args;
    }
}