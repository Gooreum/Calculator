package com.example.goo.calculator.calculatorTask.shuntingYard;

import com.example.goo.calculator.calculatorTask.function.Function;
import com.example.goo.calculator.calculatorTask.tokenizer.OperatorToken;
import com.example.goo.calculator.calculatorTask.tokenizer.Token;
import com.example.goo.calculator.calculatorTask.tokenizer.Tokenizer;
import com.example.goo.calculator.calculatorTask.operator.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Goo on 2019-08-03.
 */

public class ShuntingYard {

    /**
     * Convert a Set of tokens from infix to reverse polish notation
     * @param expression the expression to convert
     * @param userFunctions the custom functions used
     * @param userOperators the custom operators used
     * @param variableNames the variable names used in the expression
     * @return a {@link net.objecthunter.exp4j.tokenizer.Token} array containing the result
     */
    public static Token[] convertToRPN(final String expression, final Map<String, Function> userFunctions,
                                       final Map<String, Operator> userOperators, final Set<String> variableNames){
        final Stack<Token> stack = new Stack<Token>();
        final List<Token> output = new ArrayList<Token>();

        final Tokenizer tokenizer = new Tokenizer(expression, userFunctions, userOperators, variableNames);
        while (tokenizer.hasNext()) {
            Token token = tokenizer.nextToken();
            switch (token.getType()) {
                case Token.TOKEN_NUMBER:
                case Token.TOKEN_VARIABLE:
                    output.add(token);
                    break;
                case Token.TOKEN_FUNCTION:
                    stack.add(token);
                    break;
                case Token.TOKEN_SEPARATOR:
                    while (!stack.empty() && stack.peek().getType() != Token.TOKEN_PARENTHESES_OPEN) {
                        output.add(stack.pop());
                    }
                    if (stack.empty() || stack.peek().getType() != Token.TOKEN_PARENTHESES_OPEN) {
                        throw new IllegalArgumentException("Misplaced function separator ',' or mismatched parentheses");
                    }
                    break;
                case Token.TOKEN_OPERATOR:
                    while (!stack.empty() && stack.peek().getType() == Token.TOKEN_OPERATOR) {
                        OperatorToken o1 = (OperatorToken) token;
                        OperatorToken o2 = (OperatorToken) stack.peek();
                        if (o1.getOperator().getNumOperands() == 1 && o2.getOperator().getNumOperands() == 2) {
                            break;
                        } else if ((o1.getOperator().isLeftAssociative() && o1.getOperator().getPrecedence() <= o2.getOperator().getPrecedence())
                                || (o1.getOperator().getPrecedence() < o2.getOperator().getPrecedence())) {
                            output.add(stack.pop());
                        }else {
                            break;
                        }
                    }
                    stack.push(token);
                    break;
                case Token.TOKEN_PARENTHESES_OPEN:
                    stack.push(token);
                    break;
                case Token.TOKEN_PARENTHESES_CLOSE:
                    while (stack.peek().getType() != Token.TOKEN_PARENTHESES_OPEN) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    if (!stack.isEmpty() && stack.peek().getType() == Token.TOKEN_FUNCTION) {
                        output.add(stack.pop());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Token type encountered. This should not happen");
            }
        }
        while (!stack.empty()) {
            Token t = stack.pop();
            if (t.getType() == Token.TOKEN_PARENTHESES_CLOSE || t.getType() == Token.TOKEN_PARENTHESES_OPEN) {
                throw new IllegalArgumentException("Mismatched parentheses detected. Please check the expression");
            } else {
                output.add(t);
            }
        }
        return (Token[]) output.toArray(new Token[output.size()]);
    }
}
