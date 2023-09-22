package com.example.markdemofx;


import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;


public class TestMath {
    public static void main(String[] args) {
        Argument days = new Argument("days = 30");
        Expression min = new Expression("min(days/10,1)", days);
        mXparser.consolePrint(min.getExpressionString() + "----" + min.calculate());
        Expression e = new Expression("2-(32-4)/(23+4/5)-(2-4)*(4+6-98.2)+4");
        mXparser.consolePrintln("Res: " + e.getExpressionString() + " = " + e.calculate());
        Expression abs = new Expression("abs(-1)");
        mXparser.consolePrintln("Res: " + abs.getExpressionString() + " = " + abs.calculate());
        Expression sqrt = new Expression("sqrt(2)");
        mXparser.consolePrintln("Res: " + sqrt.getExpressionString() + " = " + sqrt.calculate());
    }
}
