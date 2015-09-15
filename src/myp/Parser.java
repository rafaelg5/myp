package myp;

import java.util.Stack;

public class Parser{
    
    private Token[] tokens;        
    
    /*private enum Operator{
	ADD(2), SUBSTRACT(2), MULTIPLY(3), DIVIDE(3), EXP(4);
	final int precedence;
	OperatorAssoc oa;
	Operator(int p, OperatorAssc oa){
	    precedence = p;
	    associativity = oa;
	}
    }
    
    private enum OperatorAssoc{LEFT, RIGHT}*/

    public Parser(StringTokenizer st){	
	tokens = st.getTokens();
    }
    
    public String infixToPostfix(){
	return null;
    }    
}
