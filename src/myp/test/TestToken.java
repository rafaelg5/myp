package myp.test;

import myp.Token;
import myp.StringTokenizer;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

/**
 * Clase para pruebas unitarias de la clase {@link Token}.
 */
public class TestToken {

    private final String s = "2156.1255 ( ) sgsh x sin cos tan cot sec csc "+ 
	"sqr ^ - * / + -";
    private final String[] sT = {"2156.1255","(",")","asgsh","x",
				"sin","cos","tan","cot","sec","csc","sqr", 
				"^","-", "*","/","+","-"};  
    
    /**
     * Prueba unitaria para {@link Token#getElement}.
     */
    @Test 
    public void testGetElement() {
	
	StringTokenizer st = new StringTokenizer(s);
	int i = 0;
	while(st.hasMoreTokens()){
	    Token t = st.nextToken();
	    Assert.assertTrue(t.getElement().equals(sT[i]));
	    i++;	    
	}
    }

    /**
     * Prueba unitaria para {@link Token#getType}.
     */
    @Test 
    public void testGetType() {
	
	TokenType[] tT = {NUMBER, LEFT_PARENTHESIS, LEFT_PARENTHESIS,
			  UNKNOWN, VARIABLE, FUNCTION, OPERATOR};
	
	Token t;
	for(int i = 0, j = 0, k = 0; i < sT.length; i++){	    
	    if(i < 5){
		t = new Token(sT[i],tT[j]);
		Assert.assertTrue(t.getType() == tT[j]);
		j++;
		continue;
	    }

	    if(i < 12){
		t = new Token(sT[i],tT[j]);
		Assert.assertTrue(t.getType() == tT[j]);
		continue;
	    }
	    
	    t = new Token(sT[i],tT[6], k);
	    Assert.assertTrue(t.getType() == tT[6]);
	    if(i != 14 && i != 16)
		k++;
	}	
    }

    /**
     * Prueba unitaria para {@link Token#getOperatorPrecedence}.
     */
    @Test 
    public void testGetOperatorPrecedence() {
	
	Token t;
	for(int i = 12, j = 0, k = 5; i < sT.length; i++){
	    t = new Token(sT[i],Token.TokenType.OPERATOR, j);
	    Assert.assertTrue(t.getOperatorPrecedence() == k);
	    if(i != 14 && i != 16){
		j++;
		k--;
	    }
	}			 
    }

    /**
     * Prueba unitaria para {@link Token#isOperator}.
     */
    @Test 
    public void TestIsOperator() {
	
	Token t;
	StringTokenizer st = new StringTokenizer(s);

	while(st.hasMoreTokens()){
	    Token t = st.nextToken();
	    
	    if(t.getType() != Token.TokenType.Operator)
		Assert.assertFalse(t.getType() != Token.TokenType.Operator);
	    else		
		Assert.assertTrue(t.getType() == Token.TokenType.Operator);
	}
    }
}
