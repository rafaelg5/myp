package myp;

import java.util.NoSuchElementException;
import java.util.ArrayList;

public class StringTokenizer{
    
    private ArrayList<Token> tokens;
    private int index;        

    /**
     * Constructor que recibe una cadena.
     * @param expression la cadena que se va a 'tokenizar'.
     */
    public StringTokenizer(String expression){	
	index = 0;
	tokens = new ArrayList<Token>();
	tokenize(expression);	
    }	 

    /**
     * Calcula el número de veces que el método 
     * {@link #nextToken() getComponentAt} puede ser llamado
     * antes de que genere una excepción.
     *
     * @return el numero de tokens restante de la cadena.
     * 
     */    
    public int countTokens(){
	return tokens.size() - index;
    }
    
    /**
     * Prueba si hay más tokens disponibles de la cadena 'tokenizada'.
     *
     * @return <tt>true</tt> si y solo si hay al menos un token en la cadena
     * después de la posición actual; <tt>falso</tt> en otro caso.
     * 
     */    
    public boolean hasMoreTokens(){     
	return index < tokens.size();
    }

    /**
     * Regresa el siguiente token de este 'tokenizador' de cadenas.
     *
     * @return el siguiente token de este 'tokenizador'.
     * 
     * @throws NoSuchElementException si no hay más tokens.
     * 
     */    
    public Token nextToken(){
	if(!hasMoreTokens())
	    throw new NoSuchElementException("No hay más elementos");
	
	return tokens.get(index++);
    }

    /**
     *
     * Regresa a la primera posición del tokenizador.     
     * 
     */
    public void start(){
	index = 0;
    }
    
    /**
     * Método que convierte una cadena dada en un arreglo de tokens.
     *
     * @param expression es la cadena que queremos tokenizar
     * 
     * @return la cadena tokenizada
     * 
     */    
    private void tokenize(String expression){
	expression = expression.toLowerCase();
	for(int i = 0; i < expression.length(); i++){
	    char c = expression.charAt(i);	    
	    if(c == ' ')
		continue; 	    
	    
	    Token token;	        
	    
	    /*
	    //Caso para expresiones negativas
	    if(c == '-' && i + 1 < expression.length()){
		if(i - 1 > 0 && 
		   tokens.get(i - 1).getType() != Token.TokenType.NUMBER){
		
		    char c2 = expression.charAt(i + 1);
		
		    if(Character.isDigit(c2) || c2 == 'x'){
			token = new Token("-1",Token.TokenType.NUMBER,0,0);
			tokens.add(token);		    
			token = new Token("*",Token.TokenType.OPERATOR,1,3);
			tokens.add(token);
		    }		
		    continue;
		}
	    }*/
		
	    //Agregar un número al ArrayList
	    if(Character.isDigit(c)){		
		String s = expression.substring(i + 1), s2 = "" + c;
		for(int j = 0; j < s.length(); j++){
		    
		    char c2 = s.charAt(j);
		    
		    if(Character.isDigit(c2)){
			s2 += c2;
			i++;
			continue;
		    }
			
		    else if(c2 == '.' && s2.indexOf('.') == -1
			    && Character.isDigit(s.charAt(j+1))){
			s2 += c2;
			i++;
			continue;
		    }
		    break;
		}		
		token = new Token(s2, Token.TokenType.NUMBER);
		tokens.add(token);
		continue;
	    }		
	    
	    //Agregar operadores al ArrayList	    
	    switch(c){
	    case '+':
		token = new Token(""+c, Token.TokenType.OPERATOR,3);
		tokens.add(token);
		continue;
	    case '-':
		token = new Token(""+c, Token.TokenType.OPERATOR,3);
		tokens.add(token);
		continue;
	    case '*':
		token = new Token(""+c, Token.TokenType.OPERATOR,2);
		tokens.add(token);
		continue;
	    case '/':
		token = new Token(""+c, Token.TokenType.OPERATOR,2);
		tokens.add(token);
		continue;
	    case '^':
		token = new Token(""+c, Token.TokenType.OPERATOR,0);
		tokens.add(token);
		continue;
	    case '(':
		token = new Token(""+c, Token.TokenType.LEFT_PARENTHESIS);
		tokens.add(token);
		continue;		
	    case ')':
		token = new Token(""+c, Token.TokenType.RIGHT_PARENTHESIS);
		tokens.add(token);
		continue;		
	    case 'x':
		token = new Token(""+c, Token.TokenType.VARIABLE);
		tokens.add(token);
		continue;				
	    }	    
	
	    //Agregar funciones al ArrayList
	    if(i + 2 < expression.length()){
		switch(c){
		    
		case 'c':
		    String func = expression.substring(i, i + 3);
		    if(func.equals("cos") || func.equals("cot") ||
		       func.equals("csc")){
			token = new Token(func, Token.TokenType.FUNCTION); 
			tokens.add(token);
			i+=2;
			continue;
		    }
		    break;
			
		case 's':
		    func = expression.substring(i, i + 3);
		    if(func.equals("sin") || func.equals("sec") ||
		       func.equals("sqr")){
			token = new Token(func, Token.TokenType.FUNCTION); 
			tokens.add(token);
			i+=2;
			continue;
		    }
		    break;
		    
		case 't':
		    func = expression.substring(i, i + 3);
		    if(func.equals("tan")){
			token = new Token(func, Token.TokenType.FUNCTION); 
			tokens.add(token);
			i+=2;
			continue;
		    }
		    break;
		}
	    }

	    /* Agrega un caracter que no está definido en la gramática,
	     * asignando su tipo a UNKOWN
	     */

	    String aux = expression.substring(i, expression.indexOf(' ', i));   
	    tokens.add(new Token(aux, Token.TokenType.UNKNOWN));
	    i += aux.length();
	}
    }
}
