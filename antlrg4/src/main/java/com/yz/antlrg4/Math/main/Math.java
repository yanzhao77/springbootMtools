package com.yz.antlrg4.Math.main;


import com.yz.antlrg4.Math.MathLexer;
import com.yz.antlrg4.Math.MathParser;
import com.yz.antlrg4.Math.impstand.JCLAna;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Math {
    public void execMath() {
        CharStream input = CharStreams.fromString("12*2+12\r\n");
        MathLexer lexer = new MathLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        MathParser parser = new MathParser(tokenStream);
        ParseTree tree = parser.prog();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        JCLAna jclAna = new JCLAna();
        parseTreeWalker.walk(jclAna, tree);
        

    }

    public static void main(String[] args) {
        Math math = new Math();
        math.execMath();
    }
}
