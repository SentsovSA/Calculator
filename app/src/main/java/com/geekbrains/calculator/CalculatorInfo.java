package com.geekbrains.calculator;


public class CalculatorInfo {

    protected static Double operand = null;
    protected static String lastOperation = "=";

    protected static void operation(Double number, String operation) {

        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
                case "%":
                    operand = number/100 * operand;
            }
        }
        MainActivity.resultField.setText(String.valueOf(operand));
        MainActivity.numberField.setText("");
    }
}
