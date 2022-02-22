// Generated from E:/ideaDownload/mtools/src/main/java/com/yz/antlr/g4/FujitsuJCLParser\FujitsuJCLParser.g4 by ANTLR 4.9.1
package com.yz.antlrg4.FujitsuJCLParser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link FujitsuJCLParserVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class FujitsuJCLParserBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements FujitsuJCLParserVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitR(FujitsuJCLParserParser.RContext ctx) { return visitChildren(ctx); }
}