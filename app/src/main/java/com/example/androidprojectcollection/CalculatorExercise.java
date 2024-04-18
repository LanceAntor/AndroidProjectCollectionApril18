package com.example.androidprojectcollection;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class CalculatorExercise extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv, solutionTv;
    Button button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button0;
    Button buttonDivide, buttonMultiply, buttonAddition, buttonSubtraction;
    Button buttonDot, buttonEquals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonAddition, R.id.button_addition);
        assignID(buttonSubtraction, R.id.button_subtract);
        assignID(buttonMultiply, R.id.button_multiply);
        assignID(buttonDot, R.id.button_dot);
        assignID(buttonEquals, R.id.button_equals);
    }
    void assignID(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    public void onClick(View view) {
        String finalResult;
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        String calculateData = solutionTv.getText().toString();
        if (btnText.equals("=")) {
            finalResult = evaluateExpressionUsingMDAS(calculateData);
            if (!calculateData.isEmpty()) {
                DecimalFormat df = new DecimalFormat("#.#");
                String formattedResult = df.format(Double.parseDouble(finalResult));
                if (!finalResult.equals("err")) {
                    resultTv.setText(finalResult);
                    solutionTv.setText("0");
                    return;
                }
            }
        }
        if (calculateData.equals("0")) {
            calculateData = "";
        }
        if (!calculateData.isEmpty()) {
            finalResult = evaluateExpressionSequentially(calculateData);
        }
        calculateData = calculateData + btnText;
        solutionTv.setText(calculateData);
        finalResult = evaluateExpressionSequentially(calculateData);
        if (!finalResult.equals("err")) {
            resultTv.setText(finalResult);
        }
    }
    public String evaluateExpressionSequentially(String expression) {
        try {
            // Use JavaScript engine to evaluate the expression
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scope = context.initStandardObjects();
            // Initialize result to the first operand
            double result = 0;
            boolean isFirstOperand = true;
            char lastOperator = '+';
            // Iterate through the characters of the expression
            StringBuilder currentNumber = new StringBuilder();
            for (char c : expression.toCharArray()) {
                // Check if the character is an operator
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    // If it's an operator, evaluate the current operand and apply the operation
                    double operand = Double.parseDouble(currentNumber.toString().trim());
                    currentNumber.setLength(0); // Reset the StringBuilder
                    switch (lastOperator) {
                        case '+':
                            result += operand;
                            break;
                        case '-':
                            result -= operand;
                            break;
                        case '*':
                            result *= operand;
                            break;
                        case '/':
                            if (operand == 0) {
                                // Handle division by zero
                                return "err";
                            }
                            result /= operand;
                            break;
                    }
                    lastOperator = c; // Update the last operator
                } else {
                    // If it's a digit or a decimal point, add it to the current number
                    currentNumber.append(c);
                }
            }
            // Evaluate the last operand
            double lastOperand = Double.parseDouble(currentNumber.toString().trim());
            switch (lastOperator) {
                case '+':
                    result += lastOperand;
                    break;
                case '-':
                    result -= lastOperand;
                    break;
                case '*':
                    result *= lastOperand;
                    break;
                case '/':
                    if (lastOperand == 0) {
                        // Handle division by zero
                        return "err";
                    }
                    result /= lastOperand;
                    break;
            }
            // Convert the final result to a string
            DecimalFormat df = new DecimalFormat("#.#");
            String finalResult = df.format(result);
            // If the result ends with ".0", remove it
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            // If an exception occurs during evaluation, return an error message
            return "err";
        }
    }
    public String evaluateExpressionUsingMDAS(String expression) {
        try {
            // Split the expression into individual operations based on operators
            List<String> operations = splitExpression(expression);
            // Evaluate multiplication and division first
            List<String> postfix = infixToPostfix(operations);
            // Evaluate the postfix expression
            Stack<Double> stack = new Stack<>();
            for (String token : postfix) {
                if (token.length() == 1 && "+-*/".indexOf(token.charAt(0)) != -1) {
                    // It's an operator
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    switch (token) {
                        case "+":
                            stack.push(operand1 + operand2);
                            break;
                        case "-":
                            stack.push(operand1 - operand2);
                            break;
                        case "*":
                            stack.push(operand1 * operand2);
                            break;
                        case "/":
                            if (operand2 == 0) {
                                // Handle division by zero
                                return "err";
                            }
                            stack.push(operand1 / operand2);
                            break;
                    }
                } else {
                    stack.push(Double.parseDouble(token));
                }
            }
            // Convert the final result to a string
            double result = stack.pop();
            DecimalFormat df = new DecimalFormat("#.#");
            String finalResult = df.format(result);
            // If the result ends with ".0", remove it
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            // If an exception occurs during evaluation, return an error message
            return "err";
        }
    }
    List<String> splitExpression(String expression) {
        List<String> operations = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if ("+-*/".indexOf(c) != -1) {
                // It's an operator
                if (currentToken.length() > 0) {
                    operations.add(currentToken.toString());
                    currentToken.setLength(0); // Reset the StringBuilder
                }
                operations.add(Character.toString(c)); // Add operator as a separate token
            } else {
                currentToken.append(c);
            }
        }
        // Add the last token if present
        if (currentToken.length() > 0) {
            operations.add(currentToken.toString());
        }
        return operations;
    }
    List<String> infixToPostfix(List<String> expression) {
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String token : expression) {
            if (token.length() == 1 && "+-*/".indexOf(token.charAt(0)) != -1) {
                // It's an operator
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            } else {
                postfix.add(token);
            }
        }
        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }
        return postfix;
    }
    int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }
}