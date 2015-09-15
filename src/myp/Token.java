package myp;

public class Token {
    
    private final int LEFT_RIGHT = 1;
    private final int RIGHT_LEFT = 2;

    public enum TokenType{
	NUMBER, OPERATOR, VARIABLE, FUNCTION, 
	LEFT_PARENTHESIS, RIGHT_PARENTHESIS, UNKNOWN        
    }

    String element;
    TokenType type;
    int opAssoc;
    int opPrec;

    public Token(String e, TokenType t, int opAssoc, int opPrec){
	if(t == TokenType.OPERATOR){
	    if(opAssoc < 1 && opAssoc > 2)
		throw new IllegalArgumentException("Argumento incorrecto");

	    if(opPrec < 2 && opPrec > 4)
		throw new IllegalArgumentException("Argumento incorrecto");	
	}
	
	element = e;
	type = t;
	this.opAssoc = opAssoc;
	this.opPrec = opPrec;
    }   

    public String getElement(){
	return this.element;
    }

    public TokenType getType(){
	return this.type;
    }
}


