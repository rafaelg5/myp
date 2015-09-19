package myp;

import java.text.ParseException;
import java.util.Stack;

public class Controller{
    
    private ParseException pe;
    private Token[] postfix;
    
    public Controller(String s){
	StringTokenizer st = new StringTokenizer(s);
	Parser p = new Parser(st);	
	int i = 0;
	try{	    
	    postfix = new Token[p.infixToPostfix().length];
	    for(Token token : p.infixToPostfix())
		postfix[i] = token;
		    
	}catch(ParseException pe){
	    this.pe = pe;
	}
    }

    public String getMessage(){
	if(pe != null)
	    return pe.getMessage() + " \nAt:" + pe.getErrorOffset();
	return null;
    }

    public double evaluate(double i){
	Stack<Token> stack = new Stack<Token>();
	for(Token s : postfix){
	    Token.TokenType type = s.getType();
	    if(type == Token.TokenType.OPERATOR){
		Token t1 = null, t2 = null;
		if(s.getOperatorPrecedence() != 4){
		    t2 = stack.pop();
		    t1 = stack.pop();
		    stack.push(operate(s.getElement(),
				       t1.getElement(),t2.getElement(),i));
		    continue;
		}
		t1 = stack.pop();
		stack.push(operate("--",t1.getElement(), "-1.0", i));
		continue;
	    }
		    
	    if(type == Token.TokenType.NUMBER)
		stack.push(s);		

	    if(type == Token.TokenType.FUNCTION){		
		Token t1 = stack.pop();
		stack.push(operate(s.getElement(),t1.getElement(), "", i));
		continue;
	    }	    
	}
	if(!stack.isEmpty())
	    return Double.parseDouble(stack.pop().getElement());
	return 0.0;
    }

    private Token operate(String operand, String o1, String o2, double x){
	double x1 = o1.equals("x") ? x : Double.parseDouble(o1),
	    x2 = o2.equals("x") ? x : Double.parseDouble(o2);
	
	double res = 0.0;
	switch(operand){
	case "+":
	    res = x1 + x2;
	    break;
	case "--":
	case "*":
	    res = x1 * x2;
	    break;
	case "/":
	    res = x1 / x2;
	    break;
	case "^":
	    res = Math.pow(x1,x2);
	    break;
	case "cos":
	    res = Math.cos(x1);
	    break;
	case "sin":
	    res = Math.sin(x1);
	    break;
	case "tan":
	    res = Math.tan(x1);
	    break;
	case "sec":
	    res = 1/Math.cos(x1);
	    break;
	case "csc":
	    res = 1/Math.sin(x1);
	    break;
	case "cot":
	    res = 1/Math.tan(x1);
	    break;
	case "sqr":
	    res = Math.sqrt(x1);
	    break;
	}
	return new Token(""+res,Token.TokenType.NUMBER);
    }    
}
