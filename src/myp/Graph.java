package myp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;*/

public class Graph extends JFrame {

    public Graph(String title){	
	super(title);	
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
	final JButton grafica = new JButton("Graficar");
	final JButton limpia = new JButton("Limpia");
	final JButton svg = new JButton("SVG");

	final JLabel function = new JLabel("f(x) = ");
	final JLabel width = new JLabel("Ancho:");
	final JLabel height = new JLabel("Alto:");
	final JLabel x1 = new JLabel("x₁:");
	final JLabel x2 = new JLabel("x₂:");
	final JLabel y1 = new JLabel("y₁:");
	final JLabel y2 = new JLabel("y₂:");

	final JTextField functionT = new JTextField(54);
	functionT.addActionListener(new ActionListener() {     
		public void actionPerformed(ActionEvent e) {
		    
		}
	    });
	
	final JTextArea tA = new JTextArea(35,50);
	        
	SpinnerNumberModel widthModel = new SpinnerNumberModel(320,320,1080,10);
	final JSpinner spinnerW = new JSpinner(widthModel);
	SpinnerNumberModel heightModel = new SpinnerNumberModel(240,240,620,10);
	final JSpinner spinnerH = new JSpinner(heightModel);
	SpinnerNumberModel x1Model =new SpinnerNumberModel(-5.0,-80.0,79.0,0.1);
	final JSpinner spinnerX1 = new JSpinner(x1Model);
	SpinnerNumberModel x2Model =new SpinnerNumberModel(5.0,-79.0,80.0,0.1);
	final JSpinner spinnerX2 = new JSpinner(x2Model);
	SpinnerNumberModel y1Model =new SpinnerNumberModel(-5.0,-40.0,39.0,0.1);
	final JSpinner spinnerY1 = new JSpinner(y1Model);
	SpinnerNumberModel y2Model =new SpinnerNumberModel(5.0,-39.0,40.0,0.1);
	final JSpinner spinnerY2 = new JSpinner(y2Model);

	Controller c = new Controller(functionT.getText());;
	grafica.addActionListener(new ActionListener() {     
		public void actionPerformed(ActionEvent e) {		    
		    String msg = c.getMessage();
		    
		    if(msg != null){			
			JOptionPane.showMessageDialog(null, msg);
		    }
		    graph(spinnerX1,spinnerX2,spinnerY1,spinnerY2,c);
		}
	    });
	
	limpia.addActionListener(new ActionListener() {     
		public void actionPerformed(ActionEvent e) {
		    functionT.setText("");
		}
	    });

	spinnerX1.addChangeListener(new ChangeListener() {      		
		public void stateChanged(ChangeEvent e) {
		    
		}
	    });
	
	FlowLayout flow = new FlowLayout();
	flow.setHgap(10);
	flow.setVgap(10);
	setLayout(flow);		
	
	add(function);
	add(functionT);

	add(width);
	add(spinnerW);
	add(height);
	add(spinnerH);
	add(x1);
	add(spinnerX1);
	add(y1);
	add(spinnerY1);
	add(x2);
	add(spinnerX2);
	add(y2);
	add(spinnerY2);

	add(grafica);
	add(limpia);
	add(svg);	
	/*try{
	    SVG();
	}catch(IOException e){}
	*/
	setResizable(true);	
    }    

    public void graph(JSpinner x1, JSpinner x2, JSpinner y1, JSpinner y2, 
		      Controller c){
	
	double xMin = Double.parseDouble(x1.getModel().getValue().toString()), 
	    xMax = Double.parseDouble(x2.getModel().getValue().toString()); 
	int range = (int)((xMax - xMin) * 10);
	double[] x = new double[range+1], 
	    y = new double[range+1];
	int j = 0;
	for(double i = xMin; i <= xMax; i+=0.1, j++){	    
	    x[j] = i;
	    y[j] = c.evaluate(i);
	}

	//paint (x,y) x1.getModel.getValue(), y
    }

    /*
    public void SVG() throws IOException{
	// Get a DOMImplementation.
	DOMImplementation domImpl =
	    GenericDOMImplementation.getDOMImplementation();

	// Create an instance of org.w3c.dom.Document.
	String svgNS = "http://www.w3.org/2000/svg";
	Document document = domImpl.createDocument(svgNS, "svg", null);

	// Create an instance of the SVG Generator.
	SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

	// Ask the test to render into the SVG Graphics2D implementation.
	paint(svgGenerator);

	// Finally, stream out SVG to the standard output using
	// UTF-8 encoding.
	boolean useCSS = true; // we want to use CSS style attributes
	Writer out = new OutputStreamWriter(System.out, "UTF-8");
	svgGenerator.stream(out, useCSS);
    }

    public void paint(Graphics2D g2d) {
	g2d.setPaint(Color.red);
	g2d.fill(new Rectangle(10, 10, 100, 100));
	} */   
}
