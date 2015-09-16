package myp;

public class Token {
    
    /*
     * Clase Privada Operador para Token.
     */
    private class Operator{
	
	public int precedence;
	public OperatorArity arity;
	public OperatorAssociativity associativity;

	/*
	 * Constructor de un operador de token.
	 */
	public Operator(int p, OperatorArity a, 
			OperatorAssociativity as){
	    precedence = p;
	    arity = a;
	    associativity = as;
	}
    }    

    private final Operator OPERATORS[] = {    
	/* ^ */
	new Operator(5, OperatorArity.BINARY, OperatorAssociativity.RIGHT),	
	/* - */
	new Operator(4, OperatorArity.UNARY, OperatorAssociativity.RIGHT),
	/* mult  y / */
	new Operator(3, OperatorArity.BINARY, OperatorAssociativity.LEFT),   
	/* +  y - */
	new Operator(2, OperatorArity.BINARY, OperatorAssociativity.LEFT)
    };
    
    public enum OperatorArity{UNARY, BINARY}

    public enum OperatorAssociativity{LEFT, RIGHT}

    public enum TokenType{
	NUMBER, OPERATOR, VARIABLE, FUNCTION, 
	LEFT_PARENTHESIS, RIGHT_PARENTHESIS, UNKNOWN        
    }

    private String element;
    private TokenType type;    
    private Operator operator;

    /**
     * Constructor que recibe una cadena y un tipo de token.
     * @param e el elemento del token.
     * @param t el tipo de token.
     */
    public Token(String e, TokenType t){
        
    }
    
    /**
     * Constructor que recibe una cadena, el tipo de token y 
     * el tipo de operador.
     * @param e el elemento del token.
     * @param t el tipo de token.
     * @param i el tipo de operador que es el token.
     */
    public Token(String e, TokenType t, int i){
	
    }

    /**
     * Regresa el elemento del token.
     * @return el elemento.
     */
    public String getElement(){
	return null;
    }

    /**
     * Regresa el tipo del token.
     * @return el tipo del token.
     */
    public TokenType getType(){
	return null;
    }

    /**
     * Checa si un token es operador.
     * @return <tt>true</tt> si es operador, <tt>false</tt> en otro caso.
     */
    public boolean isOperator(){
	return false;
    }    

    /**
     * Regresa la precedencia del operador.
     * @return la precedencia del operador.
     */
    public int getOperatorPrecedence(){
	return -1;
    }

    
    /**
     * Regresa la aridad del operador.
     * @return la aridad.
     */
    public OperatorArity getOperatorArity(){
	return null;
    }

    
    /**
     * Regresa la asociatividad del operador.
     * @return la asociatividad.
     */
    public OperatorAssociativity getOperatorAssociativity(){
	return null;
    }
}

