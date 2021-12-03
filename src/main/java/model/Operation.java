package model;

public class Operation {

    private double value1 = Double.NaN;

    private double value2 = Double.NaN;

    private  String op = "";

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public double getValue1() {
        return value1;
    }

    public String getValue1String() {
        String s = String.valueOf(value1);
        if(s.endsWith(".0")){
            s = s.substring(0,s.length()-2);
        }
        return s;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double calculate() {
        double answer = Double.NaN;
        switch (op) {
            case "+":
                answer = value1 + value2;
                break;
            case "-":
                answer = value1 - value2;
                break;
            case "*":
                answer = value1 * value2;
                break;
            case "/":
                if (value2 != 0) {
                    answer = value1 / value2;
                } else {
                    answer = Double.NaN;
                }
                break;
            case "√":
                answer = Math.sqrt(value1);
                break;
            case "n":
                answer = Math.pow(value1, value2);
                break;
            case "=":
                answer = value1;
                break;
        }
        return answer;
    }

}
