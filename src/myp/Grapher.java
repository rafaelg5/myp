package myp;

import javax.swing.SwingUtilities;

public class Grapher{
    public static void main(String[] args){

	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run(){
		    new Graph("Graficador");
		}
	    });	
    }    
}
