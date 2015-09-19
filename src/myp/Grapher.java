package myp;

import javax.swing.SwingUtilities;

public class Grapher{
    public static void main(String[] args){

	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run(){
		    final Graph graph = new Graph("Graficador");
		    graph.setSize(660,620);
		    graph.setVisible(true);
		}
	    });	
    }    
}
