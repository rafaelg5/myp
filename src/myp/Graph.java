package myp;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.geom.Line2D;

public class Graph extends JFrame implements ChangeListener{
    
    private int graphWidth;
    private int graphHeight;
    private int unitX;
    private int unitY;
    
    private ArrayList<Coordinates> coordinates;   

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    private Controller c;

    private JSpinner spinnerW;
    private JSpinner spinnerH;
    private JSpinner spinnerX1;
    private JSpinner spinnerX2;
    private JSpinner spinnerY1;
    private JSpinner spinnerY2;

    private class Coordinates {
	
	private double x;
	private double y;

	public Coordinates(double x, double y){
	    this.x = x;
	    this.y = y;
	}

	public double getX(){
	    return this.x;
	}

	public double getY(){
	    return this.y;
	}
    }
    
    public Graph(String title) {
        super(title);       
	
	graphWidth = 640;
	graphHeight = 500;
	coordinates = new ArrayList<Coordinates>();

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

        final JTextArea tA = new JTextArea(35, 50);

        SpinnerNumberModel widthModel = 
	    new SpinnerNumberModel(640, 320, 1366, 10);
	spinnerW = new JSpinner(widthModel);
        SpinnerNumberModel heightModel = 
	    new SpinnerNumberModel(500, 240, 768, 10);
        spinnerH = new JSpinner(heightModel);
        SpinnerNumberModel x1Model = 
	    new SpinnerNumberModel(-10.0, -80.0, 79.0, 0.1);
        spinnerX1 = new JSpinner(x1Model);
        SpinnerNumberModel x2Model = 
	    new SpinnerNumberModel(10.0, -79.0, 80.0, 0.1);
        spinnerX2 = new JSpinner(x2Model);
        SpinnerNumberModel y1Model = 
	    new SpinnerNumberModel(-10.0, -40.0, 39.0, 0.1);
        spinnerY1 = new JSpinner(y1Model);
        SpinnerNumberModel y2Model = 
	    new SpinnerNumberModel(10.0, -39.0, 40.0, 0.1);
	spinnerY2 = new JSpinner(y2Model);
	
	//c = new Controller("3*sqr(((x-4)^2/16)-1)-1");

	unitX = (int)(Double.parseDouble(x2Model.getValue().toString()) -
		     Double.parseDouble(x1Model.getValue().toString()));

	unitY = (int)(Double.parseDouble(y2Model.getValue().toString()) -
		     Double.parseDouble(y1Model.getValue().toString()));

	String auxX1 = x1Model.getValue().toString(), 
	    auxY1 = y1Model.getValue().toString(),
	    auxX2 = x2Model.getValue().toString(), 
	    auxY2 = y2Model.getValue().toString();

	minX = Double.parseDouble(auxX1);
	minY = Double.parseDouble(auxY1);
	maxX = Double.parseDouble(auxX2);
	maxY = Double.parseDouble(auxY2);
	
        grafica.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    c = new Controller(functionT.getText());
		    String msg = c.getMessage();
		    
		    if (msg != null) {
			JOptionPane.showMessageDialog(null, msg);
		    } else {
			setMinMaxValues();
			getXY(minX, maxX, minY, maxY);
			update(getGraphics());
		    }
		}
	    });
	
	
        limpia.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    functionT.setText("");
		    c = new Controller(functionT.getText());
		    coordinates = new ArrayList<Coordinates>();
		    spinnerX1.setValue((Object)(-10));
		    spinnerX2.setValue((Object)(10));
		    spinnerY1.setValue((Object)(-10));
		    spinnerY2.setValue((Object)(10));
		    setMinMaxValues();
		    getXY(minX, maxX, minY, maxY);
		    update(getGraphics());
		}
	    });

        spinnerX1.addChangeListener(this);
	spinnerX2.addChangeListener(this);
	spinnerY1.addChangeListener(this);
	spinnerY2.addChangeListener(this);
	
	spinnerW.addChangeListener(new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
		    
		    int valueW = 
			Integer.parseInt(widthModel.getValue().toString()),
			valueH = 
			Integer.parseInt(heightModel.getValue().toString());
			
		    getContentPane().setPreferredSize(new Dimension(valueW,
								    valueH));
		    setGraphWidth(valueW);
		    if(c == null)
			return;

		    if(c.getMessage() != null)
			return;
		    
		    getXY(minX, maxX, minY, maxY);
		    update(getGraphics());
		    
		}
	    });
      
	spinnerH.addChangeListener(new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {

		    int valueW = 
			Integer.parseInt(heightModel.getValue().toString()),
			valueH = 
			Integer.parseInt(heightModel.getValue().toString());
			
		    getContentPane().setPreferredSize(new Dimension(valueW,
								    valueH));
		    setGraphHeight(valueH);
		    if(c == null)
			return;

		    if(c.getMessage() != null)
			return;
		    
		    getXY(minX, maxX, minY, maxY);
		    update(getGraphics());

		}
	    });

	getContentPane().setPreferredSize(new Dimension(660, 620));	

		FlowLayout f = new FlowLayout();
	setLayout(f);
	
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
	
	pack();
	
        setResizable(false);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setVisible(true);
	
    }

    @Override 
    public void paint(Graphics g){

	Graphics2D g2d = (Graphics2D)g ;
	
	
	double factorX = graphWidth / unitX;
	double factorY = graphHeight / unitY;
	
	double gW = (Math.abs(minX) / (maxX - minX)) * graphWidth + 10;
	double gH = graphHeight -
	    ((Math.abs(minY) / (maxY - minY)) * graphHeight - 40);

	drawPlane(g2d, (int)gW, (int)gH);

	double x, y;
	Random rand = new Random();

	float red = rand.nextFloat(), 
	    green = rand.nextFloat(), blue = rand.nextFloat();
                
        g2d.setColor(new Color(red, green, blue));	
	g2d.setStroke(new BasicStroke(2));

	double x1, y1, x2, y2;
	for(int i = 1; i < coordinates.size(); i++){
	    Coordinates c1 = coordinates.get(i - 1);	    
	    Coordinates c2 = coordinates.get(i);
	    if(Double.isNaN(c1.getY()) || Double.isNaN(c2.getY()))
		continue;	    
	    
	    x1 = c1.getX();
	    y1 = c1.getY();	    
	    x2 = c2.getX();
	    y2 = c2.getY();	    

	    if(Math.abs(x2 - x1) >= 0.02)
		continue;
	    
	    Shape l = 
		new Line2D.Double((factorX * x1) + gW, 
				  (factorY * -y1) + gH, 
				  (factorX * x2) + gW, 
				  (factorY * -y2) + gH);
	    g2d.draw(l);
	}
    }

    private void drawPlane(Graphics2D g2d, int o1, int o2){
	g2d.setColor(Color.WHITE);
	g2d.fillRect(10, 40, graphWidth - 1, graphHeight - 1);	
	g2d.setColor(Color.BLACK);	
	g2d.drawLine(10, o2, graphWidth + 9, o2);
	g2d.drawLine(o1, 40 , o1, graphHeight + 39);		
    }

    private void getXY(double x1, double x2, double y1, double y2) {
        	
	if(c.getMessage() != null)
	    return;

        for (; x1 <= x2; x1 += 0.01) {                     
	    double y = c.evaluate(x1);	    
	    if(y < y1 || y > y2)
		continue;    

	    coordinates.add(new Coordinates(x1, y));
        }	
    }   

    @Override    
    public void stateChanged(ChangeEvent e) {	        
	
	double s1 = 
	    Double.parseDouble(spinnerX1.getModel().getValue().toString()),
	    s2 = 
	    Double.parseDouble(spinnerX2.getModel().getValue().toString()),
	    s3 = 
	    Double.parseDouble(spinnerY1.getModel().getValue().toString()),
	    s4 = 
	    Double.parseDouble(spinnerY2.getModel().getValue().toString());

	if(s1 > s2 || s1 == s2){
	    JOptionPane.showMessageDialog(null, "Verificar los valores de X");
	    return;
	}

	if(s3 > s4 || s3 == s4){
	    JOptionPane.showMessageDialog(null, "Verificar los valores de Y");
	    return;
	}

	setMinMaxValues();
	if(c == null)
	    return;

	if(c.getMessage() != null)
	    return;

	getXY(minX, maxX, minY, maxY);
	update(getGraphics());
    }

    private void setMinMaxValues(){

	minX = Double.parseDouble(spinnerX1.getModel().getValue().toString());
	maxX = Double.parseDouble(spinnerX2.getModel().getValue().toString());
	minY = Double.parseDouble(spinnerY1.getModel().getValue().toString());
	maxY = Double.parseDouble(spinnerY2.getModel().getValue().toString()); 
	unitX = (int)(maxX - minX);
	unitY = (int)(maxY - minY);
    }

    private void setGraphWidth(int value){
	this.graphWidth = value - 20;	
    }

    private void setGraphHeight(int value){
	this.graphHeight = value - 120;
    }
}
