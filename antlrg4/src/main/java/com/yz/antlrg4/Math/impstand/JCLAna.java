package com.yz.antlrg4.Math.impstand;

import com.yz.antlrg4.Math.MathBaseListener;
import com.yz.antlrg4.Math.MathParser;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

public class JCLAna extends MathBaseListener {
    Stack<Integer> stack = new Stack<>();
    @Override
    public void enterPrintExpr(MathParser.PrintExprContext ctx) {
        super.enterPrintExpr(ctx);
    }

    @Override
    public void enterAssign(MathParser.AssignContext ctx) {
        super.enterAssign(ctx);
        System.out.println("enterAssign" + "\t" + ctx.getText());
    }

    @Override
    public void exitAssign(MathParser.AssignContext ctx) {
        super.exitAssign(ctx);
        System.out.println("exitAssign" + "\t" + ctx.getText());
    }

    @Override
    public void enterBlank(MathParser.BlankContext ctx) {
        super.enterBlank(ctx);
        System.out.println("enterBlank" + "\t" + ctx.getText());
    }

    @Override
    public void exitBlank(MathParser.BlankContext ctx) {
        super.exitBlank(ctx);
        System.out.println("exitBlank" + "\t" + ctx.getText());
    }

    @Override
    public void enterParens(MathParser.ParensContext ctx) {
        super.enterParens(ctx);
        System.out.println("enterParens" + "\t" + ctx.getText());
    }

    @Override
    public void exitParens(MathParser.ParensContext ctx) {
        super.exitParens(ctx);
        System.out.println("exitParens" + "\t" + ctx.getText());
    }

    @Override
    public void enterMulDiv(MathParser.MulDivContext ctx) {
        super.enterMulDiv(ctx);
        System.out.println("enterMulDiv" + "\t" + ctx.getText());
    }

    @Override
    public void exitMulDiv(MathParser.MulDivContext ctx) {
        super.exitMulDiv(ctx);
        System.out.println("exitMulDiv" + "\t" + ctx.getText());
    }

    @Override
    public void enterAddSub(MathParser.AddSubContext ctx) {
        super.enterAddSub(ctx);
        System.out.println("enterAddSub" + "\t" + ctx.getText());
    }

    @Override
    public void exitAddSub(MathParser.AddSubContext ctx) {
        super.exitAddSub(ctx);
        System.out.println("exitAddSub" + "\t" + ctx.getText());
    }

    @Override
    public void enterId(MathParser.IdContext ctx) {
        super.enterId(ctx);
        System.out.println("enterId" + "\t" + ctx.getText());
    }

    @Override
    public void exitId(MathParser.IdContext ctx) {
        super.exitId(ctx);
        System.out.println("exitId" + "\t" + ctx.getText());
    }

    @Override
    public void enterInt(MathParser.IntContext ctx) {
        super.enterInt(ctx);
        stack.add(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public void exitInt(MathParser.IntContext ctx) {
        super.exitInt(ctx);
        System.out.println("exitInt" + "\t" + ctx.getText());
    }



    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
        System.out.println("visitTerminal" + "\t" + node.getText());
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
        System.out.println("visitErrorNode" + "\t" + node.getText());
    }
}
