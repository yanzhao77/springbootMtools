// Generated from E:/ideaDownload/mtools/src/main/java/com/yz/antlr/g4/FujitsuJCLParser\FujitsuJCLParser.g4 by ANTLR 4.9.1
package com.yz.antlrg4.FujitsuJCLParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FujitsuJCLParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FujitsuJCLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FujitsuJCLParserParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(FujitsuJCLParserParser.RContext ctx);
}