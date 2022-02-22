package com.yz.antlrg4.Calc.impstand;


import com.yz.antlrg4.Calc.CalcBaseListener;
import com.yz.antlrg4.Calc.CalcParser;

import java.util.Stack;

public class JCLAna extends CalcBaseListener {
    Stack<Integer> stack = new Stack<>();

    @Override
    public void exitInt(CalcParser.IntContext ctx) {
        stack.add(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public void exitAdd(CalcParser.AddContext ctx) {
        int r = stack.pop();
        int l = stack.pop();
        stack.add(l + r);
    }

    @Override
    public void exitSub(CalcParser.SubContext ctx) {
        int r = stack.pop();
        int l = stack.pop();
        stack.add(l - r);
    }

    public int result() {
        return stack.pop();
    }

}
