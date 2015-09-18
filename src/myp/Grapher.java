package myp;

import java.util.Random;
import java.text.ParseException;

public class Grapher{
    public static void main(String[] args){
	StringTokenizer st = new StringTokenizer("2*x+1");
	Parser p = new Parser(st);
	try{
	    for(String s : p.infixToPostfix())
		System.out.println(s);
	}catch(ParseException pe){}
    }    
}
