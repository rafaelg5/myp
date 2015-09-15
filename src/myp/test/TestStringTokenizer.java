package myp.test;

import myp.StringTokenizer;
import myp.Token;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link
 * StringTokenizer}.
 */
public class TestStringTokenizer {

    private final String str = 
	"4*x^3 - 3*x^2 + 2*x -(cos(x)/sqr(3*x - 2)) + tan(x)^2";
    
    private final String[] tokStr = 
    {"4","*","x","^","3","-","3","*","x","^","2","+",
     "2","*","x","-","(","cos","(","x",")","/","sqr",
     "(","3","*","x","-","2",")",")","+","tan","(",
     "x",")","^","2"};

    private final String badStr = "5 *x 8/. + 34";
    
    private final String[] tokBadStr = {"5","*","x","8","/",".","+","34"};
    
    /**
     * Prueba unitaria para el constructor 
     * {@link StringTokenizer#StringTokenizer}.     * 
     */
    @Test 
    public void testStringTokenizer() {

        StringTokenizer st = new StringTokenizer(str);	
	int length = 0;
	while(st.hasMoreTokens()){	    
	    Token t = st.nextToken();	    
	    length++;
	}
	Assert.assertTrue(tokStr.length == length);
        
	st.start();
	int i = 0;
	while(st.hasMoreTokens()){
	    Token token = st.nextToken();
	    Assert.assertTrue(token.getElement().equals(tokStr[i]));
	    i++;
	}
		
	st.start();
	while(st.hasMoreTokens()){
	    Token token = st.nextToken();
	    Assert.assertFalse(token.getType() == Token.TokenType.UNKNOWN);
	}


	st = new StringTokenizer(badStr);
	length = 0;	
	while(st.hasMoreTokens()){
	    st.nextToken();
	    length++;
	}

	Assert.assertTrue(tokBadStr.length == length);
	
	st.start();
	i = 0;	
	while(st.hasMoreTokens()){
	    Token token = st.nextToken();
	    Assert.assertTrue(token.getElement().equals(tokBadStr[i]));
	    i++;
	}
	
	
	st.start();
	boolean isUnknown = false;
	while(st.hasMoreTokens()){
	    Token token = st.nextToken();
	    if(token.getType() == Token.TokenType.UNKNOWN){
		isUnknown = true;
		break;
	    }
	}	
	
	Assert.assertTrue(isUnknown);
    }

    /**
     * Prueba unitaria para {@link StringTokenizer#countTokens}.
     */
    @Test 
    public void testCountTokens() {
        StringTokenizer st = new StringTokenizer(str);	
	int count = tokStr.length;        
	
	while(st.hasMoreTokens()){
	    Assert.assertTrue(st.countTokens() == count--);
	    st.nextToken();
	}
    }

    /**
     * Prueba unitaria para {@link StringTokenizer#hasMoreTokens}.
     */
    @Test 
    public void testHasMoreTokens() {
        StringTokenizer st = new StringTokenizer(str);
	
	while(st.hasMoreTokens()){
	    Assert.assertTrue(st.hasMoreTokens());
	    st.nextToken();
	}
	Assert.assertFalse(st.hasMoreTokens());
    }

    /**
     * Prueba unitaria para {@link StringTokenizer#nextToken}.
     */
    @Test 
    public void testNextToken() {
	
        StringTokenizer st = new StringTokenizer(str);
	
	int i = 0;
	while(st.hasMoreTokens()){	    	    
	    Token token = st.nextToken();	    
	    Assert.assertTrue(token.getElement().equals(tokStr[i]));
	    i++;
	}
    }
}
