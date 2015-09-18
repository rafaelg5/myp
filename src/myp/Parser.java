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
	String[] s = new String[st.countTokens() - countParentheses()];	
	Stack<Token> stack = new Stack<Token>();
	int i = 0, j = 0;

	while(st.hasMoreTokens()){
	    Token t = st.nextToken();
	    if(t.getType() == Token.TokenType.UNKNOWN)
		throw new ParseException("Elemento desconocido", j);	       
		
	    if(t.getType() == Token.TokenType.NUMBER || 
	       t.getType() == Token.TokenType.VARIABLE){		
		s[i++] = t.getElement();
		j++;
		continue;
	    }
	    
	    if(t.getType() == Token.TokenType.FUNCTION){
		stack.push(t);
		j++;
		continue;		
	    }

	    if(t.getType() == Token.TokenType.OPERATOR){
		if(j + st.countTokens() > s.length)
		    throw new ParseException("Elemento desconocido", j);
		
		if(t.getElement().equals("-"))
		    if(st.getToken(j+1).getType() == 
		       Token.TokenType.NUMBER ||
		       st.getToken(j+1).getType() == 
		       Token.TokenType.VARIABLE || 
		       st.getToken(j+1).getType() == 
		       Token.TokenType.FUNCTION ||
		       st.getToken(j+1).getType() == 
		       Token.TokenType.LEFT_PARENTHESIS)
			t.setOperator(1);

		Token top = null;
		if(!stack.isEmpty())
		    top = stack.peek();
		
		if(top != null)
		    if((t.getOperatorAssociativity() == 
		       Token.OperatorAssociativity.LEFT && 
			t.getOperatorPrecedence()<= top.getOperatorPrecedence())
		       ||
		       (t.getOperatorAssociativity() == 
		       Token.OperatorAssociativity.RIGHT && 
			t.getOperatorPrecedence() < top.getOperatorPrecedence())
		       )
			s[i++] = stack.pop().getElement();
			
		stack.push(t);
		j++;
		continue;
	    }
	    
	    if(t.getType() == Token.TokenType.LEFT_PARENTHESIS){
		stack.push(t);
		j++;
		continue;
	    }
	    
	    if(t.getType() == Token.TokenType.RIGHT_PARENTHESIS){
		Token top = stack.peek();
		while(top.getType() != Token.TokenType.LEFT_PARENTHESIS &&
		      top.getType() == Token.TokenType.OPERATOR){
		    top = stack.pop();
		    s[i++] = top.getElement();
		}
		
		if(stack.isEmpty())
		    throw new ParseException("Mismatch parentheses", j);
		
		//tiene que ser paréntesis izquierdo
		stack.pop();
		top = stack.peek();

		if(top.getType() == Token.TokenType.FUNCTION)
		    s[i++] = stack.pop().getElement();
		
		j++;
	    }
	    
	}

	while(!stack.isEmpty()){
	    if(stack.peek().getType() == Token.TokenType.LEFT_PARENTHESIS)
		throw new ParseException("Mismatch parentheses", j);
	    
	    s[i++] = stack.pop().getElement();
	}	
	return s;
    }

    private int countParentheses(){
	st.start();
	int c = 0;
	while(st.hasMoreTokens()){
	    Token token = st.nextToken();
	    if(token.getType() == Token.TokenType.LEFT_PARENTHESIS ||
	       token.getType() == Token.TokenType.RIGHT_PARENTHESIS)
		c++;
	}
	st.start();
	return c;
    }
}
