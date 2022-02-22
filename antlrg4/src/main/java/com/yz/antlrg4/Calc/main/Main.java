package com.yz.antlrg4.Calc.main;

import com.yz.antlrg4.Calc.CalcLexer;
import com.yz.antlrg4.Calc.CalcParser;
import com.yz.antlrg4.Calc.impstand.JCLAna;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static int exec(String input) {
        //词法分析
        //将输入的字符或者文件数据转换为流
        CodePointCharStream cs = CharStreams.fromString(input);
        // 单个词法分析，加载词法分析器，加载所有的词法数据结构
        CalcLexer lexer = new CalcLexer(cs);
        //整句字符分析，词法符号化
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //语法分析
        CalcParser parser = new CalcParser(tokens);

        // 获取语法分析树
        ParseTree tree = parser.expr();

        //树遍历器 tree-walkers
        ParseTreeWalker walker = new ParseTreeWalker();

        JCLAna listener = new JCLAna();
        walker.walk(listener, tree);
        return listener.result();
    }

    public static void main(String[] args) {
        String input = "3+2";
        System.out.println(exec(input));
    }
}
