package myp;

import java.util.Stack;
import java.text.ParseException;

public class Parser{

    StringTokenizer st;
        
    /**
     * Constructor que recibe un objeto de tipo StringTokenizer.
     */
    public Parser(StringTokenizer st){	
	this.st = st;
    }
    
    /**
     * Método que revisa gramaticalmente una expresión tokenizada
     * de forma infija y la transforma a posfija.
     *
     * @return un arreglo con cada uno de los tokens en forma posfija.
     *
     * @throws ParseException si un elemento es de tipo desconocido.
     *
     */
    public String[] infixToPostfix() throws ParseException{
	String[] s = new String[st.countTokens()];
	Stack<Token> stack = new Stack<Token>();
	int i = 0, j = 0;
	while(st.hasMoreTokens()){
	    Token t = st.nextToken();
	    if(t.getType() == Token.TokenType.UNKNOWN)
		throw new ParseException("Elemento desconocido", j);	       
		
	    if(t.getType() == Token.TokenType.NUMBER || 
	       t.getType() == Token.TokenType.VARIABLE){		
		s[i++] = t.getElement();
		continue;
	    }
	    
	    if(t.getType() == Token.TokenType.FUNCTION){
		stack.push(t);
		continue;
	    }

	    if(t.getType() == Token.TokenType.OPERATOR){
		if(t.getElement().equals("-"))
		    
		stack.push(t);
		continue;
	    }
	    j++;
	}	
	return null;
    }    
}
