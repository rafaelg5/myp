package myp.test;

import myp.Parser;
import myp.StringTokenizer;
import myp.Token;
import org.junit.Assert;
import org.junit.Test;
import java.text.ParseException;

/**
 * Clase para pruebas unitarias de la clase {@link
 * Parser}.
 */
public class TestParser {

    private final String str = 
	"-4*x^3 - 3*x^2 + 2*x -(cos(x)/sqr(3*x - 2)) + tan(x)^2";
    
    private final String[] tokStr = 
    {"-4","*","x","^","3","-","3","*","x","^","2","+",
     "2","*","x","-","(","cos","(","x",")","/","sqr",
     "(","3","*","x","-","2",")",")","+","tan","(",
     "x",")","^","2"};

    private final String[] postfix = 
    {"4","x","3","^","3","x","2","^","2","x","*","x",
     "cos","3","x","*","2","-","sqr","/","-","x","tan",
     "2","^","+","+","*","-","*"};

    private final String badStr = "5 *x 8/. + 34";
    private final String badStr2 = "(3 *4)*x + )";
    private final String[] tokBadStr = {"5","*","x","8","/",".","+","34"};
    private final String[] tokBadStr2 = {"(","3","*","4","*","x","+",")"};
    
    /**
     * Prueba unitaria para {@link Parser#infixToPostfix}.
     */
    @Test 
    public void testInfixToPostfix() {

        StringTokenizer st = new StringTokenizer(str);	
	Parser p = new Parser(st);
	String[] s;
	try{
	    s = p.infixToPostfix();
	    Assert.assertTrue(s.length == postfix.length);
	    for(int i = 0; i < s.length; i++)
		Assert.assertTrue(s[i].equals(postfix[i]));	
	}catch(ParseException pe){}	
	
	StringTokenizer st2;
	Parser p2;
	String[] s2;
	st = new StringTokenizer(badStr);
	st2 = new StringTokenizer(badStr2);
	p = new Parser(st);
	p2 = new Parser(st2);
	
	try{
	    s = p.infixToPostfix();
	    s2 = p2.infixToPostfix();
	}catch(ParseException pe){
	    System.out.println(pe + " en: " + pe.getErrorOffset());
	    return;
	}
	Assert.fail();	
    }
}
