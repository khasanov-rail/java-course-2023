package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }

        @Override
        public String toString() {
            return "(-" + expr + ")";
        }
    }

    record Exponent(Expr base, Expr exponent) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), exponent.evaluate());
        }

        @Override
        public String toString() {
            return "(" + base + "^" + exponent + ")";
        }
    }

    record Addition(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }

        @Override
        public String toString() {
            return "(" + left + " + " + right + ")";
        }
    }

    record Multiplication(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() * right.evaluate();
        }

        @Override
        public String toString() {
            return "(" + left + " * " + right + ")";
        }
    }

//    public static void main(String[] args) {
//        var two = new Constant(2);
//        var four = new Constant(4);
//        var negOne = new Negate(new Constant(1));
//        var sumTwoFour = new Addition(two, four);
//        var mult = new Multiplication(sumTwoFour, negOne);
//        var exp = new Exponent(mult, two);
//        var res = new Addition(exp, new Constant(1));
//
//        System.out.println(res + " = " + res.evaluate());
//    }
}
