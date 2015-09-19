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
    public Token[] infixToPostfix() throws ParseException{
	int countParentheses = countParentheses(), 
	    length = st.countTokens() - countParentheses;	    
	    
	Token[] sT = new Token[length];	
	String error = "Verificar la función", 
	    error2 = "Paréntesis no coinciden";
	Stack<Token> stack = new Stack<Token>();
	int i = 0, j = 0;

	while(st.hasMoreTokens()){
	    Token t = st.nextToken();
	    
	    if(t.getType() == Token.TokenType.UNKNOWN)
		throw new ParseException(error, j);	       
		
	    if(t.getType() == Token.TokenType.NUMBER || 
	       t.getType() == Token.TokenType.VARIABLE){	        
		
		if((j - 1 >= 0 && st.getToken(j-1).getType() != 
		    Token.TokenType.OPERATOR &&
		    st.getToken(j-1).getType()!= 
		    Token.TokenType.LEFT_PARENTHESIS) 
		   ||
		   (j+ 1 < length && st.getToken(j+1).getType() != 
		    Token.TokenType.OPERATOR &&
		    st.getToken(j+1).getType()!= 
		    Token.TokenType.RIGHT_PARENTHESIS))
		    throw new ParseException(error, j);
		        		
		sT[i++] = t;
		j++;
		continue;
	    }  
	    
	    if(t.getType() == Token.TokenType.FUNCTION){
		
		if(j + 1 >= (length + countParentheses))
		    throw new ParseException(error2, j);
		
		if(st.getToken(j+1).getType() != 
		   Token.TokenType.LEFT_PARENTHESIS)
		    throw new ParseException(error2, j);
		
		stack.push(t);
		j++;
		continue;		
	    }

	    if(t.getType() == Token.TokenType.OPERATOR){		
		
		if((j - 1 < 0 && !t.getElement().equals("-")) ||
		   (j + 1 >= (length + countParentheses) && 
		    !t.getElement().equals("-")))
		    throw new ParseException(error, j);				
		
		if(j + 1 < length){
		    if(t.getElement().equals("-")){				
			Token.TokenType ttM = st.getToken(j+1).getType();
			
			boolean unaryRightIsValid = 
			    ttM == Token.TokenType.NUMBER ||
			    ttM == Token.TokenType.VARIABLE || 
			    ttM == Token.TokenType.FUNCTION ||
			    ttM == Token.TokenType.LEFT_PARENTHESIS,
			    rightIsValid = false, leftIsValid = false;
			
			if(j - 1 >= 0){
			    
			    Token.TokenType ttM2 = st.getToken(j-1).getType();
			    
			    rightIsValid = ttM == Token.TokenType.NUMBER ||
				ttM == Token.TokenType.VARIABLE ||
				ttM == Token.TokenType.LEFT_PARENTHESIS;
			    
			    leftIsValid = ttM2 == Token.TokenType.NUMBER ||
				ttM2 == Token.TokenType.VARIABLE ||
				ttM2 == Token.TokenType.RIGHT_PARENTHESIS;    
			}

			boolean isBinary = rightIsValid && leftIsValid;	

			if(!isBinary && unaryRightIsValid)	    
			    t.setOperator(1);	    			
		    }
		    
		}else if(j + 1 >= (length + countParentheses)
			 && t.getElement().equals("-"))
		    throw new ParseException(error, j);
		
		if(j - 1 >= 0 && j + 1 < (length + countParentheses) && 
		   t.getOperatorPrecedence() != 4 && 
		   !st.getToken(j+1).getElement().equals("-")){
		    Token prev = st.getToken(j-1), next = st.getToken(j+1);
		    
		    if(prev.getType() == Token.TokenType.LEFT_PARENTHESIS ||
		       prev.getType() == Token.TokenType.FUNCTION || 
		       prev.getType() == Token.TokenType.OPERATOR)
			throw new ParseException(error, j);
		    
		    if(next.getType() == Token.TokenType.RIGHT_PARENTHESIS ||
		       next.getType() == Token.TokenType.OPERATOR)
			throw new ParseException(error, j);		    
		}		    
	        
		Token top = null;
		if(!stack.isEmpty())
		    top = stack.peek();		
		
		while(top != null && top.getType()==Token.TokenType.OPERATOR &&
		      ((t.getOperatorAssociativity() == 
		       Token.OperatorAssociativity.LEFT && 
		       t.getOperatorPrecedence()<= top.getOperatorPrecedence())
		      ||
		      (t.getOperatorAssociativity() == 
		       Token.OperatorAssociativity.RIGHT && 
		       t.getOperatorPrecedence()<top.getOperatorPrecedence()))){
		    
		    sT[i++] = stack.pop();
		    if(!stack.isEmpty())
			top = stack.peek();
		    else
			top = null;
		}
			
		stack.push(t);
		j++;		
		continue;
	    }
	    
	    if(t.getType() == Token.TokenType.LEFT_PARENTHESIS){
		
		if(j + 1 >= (length + countParentheses))
		    throw new ParseException(error2, j);
		
		if(st.getToken(j+1).getType() != 
		   Token.TokenType.LEFT_PARENTHESIS &&
		   st.getToken(j+1).getType()!= 
		   Token.TokenType.VARIABLE &&
		   st.getToken(j+1).getType() != 
		   Token.TokenType.NUMBER &&
		   st.getToken(j+1).getType() != 
		   Token.TokenType.FUNCTION)
		    throw new ParseException(error2, j);
		    
		stack.push(t);
		j++;
		continue;
	    }
	    
	    if(t.getType() == Token.TokenType.RIGHT_PARENTHESIS){		
		
		if(j == 0)		    
		    throw new ParseException(error2, j);
		
		if(st.getToken(j-1).getType() != 
		   Token.TokenType.RIGHT_PARENTHESIS &&
		   st.getToken(j-1).getType()!= 
		   Token.TokenType.VARIABLE &&
		   st.getToken(j-1).getType() != 
		   Token.TokenType.NUMBER)		    
		    throw new ParseException(error2, j);
		    
		Token top = null;
		if(!stack.isEmpty())
		    top = stack.peek();
		
		while(top != null && 
		      top.getType() != Token.TokenType.LEFT_PARENTHESIS &&
		      top.getType() == Token.TokenType.OPERATOR){
		    
		    sT[i++] = stack.pop();
		    if(!stack.isEmpty())
			top = stack.peek();
		    else
			top = null;
		}		
		
		if(stack.isEmpty())
		    throw new ParseException(error2, j);
		
		//tiene que ser paréntesis izquierdo
		top = stack.pop();
		if(top.getType() != Token.TokenType.LEFT_PARENTHESIS)
		    throw new ParseException(error2, j);

		if(!stack.isEmpty())
		    top = stack.peek();
		    
		if(top.getType() == Token.TokenType.FUNCTION)
		    sT[i++] = stack.pop();
		
		j++;
	    }	    
	}

	while(!stack.isEmpty()){
	    if(stack.peek().getType() == Token.TokenType.LEFT_PARENTHESIS)
		throw new ParseException(error2, j);
	    
	    sT[i++] = stack.pop();
	    
	}	
	return sT;
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
