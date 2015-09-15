package myp;

public class Token {
    
    public enum TokenType{
	NUMBER, OPERATOR, VARIABLE, FUNCTION, 
	LEFT_PARENTHESIS, RIGHT_PARENTHESIS, UNKNOWN
    }
    
    String element;
    TokenType type;

    public Token(String e, TokenType t){
	element = e;
	type = t;
    }

    public String getElement(){
	return this.element;
    }

    public TokenType getType(){
	return this.type;
    }
}


