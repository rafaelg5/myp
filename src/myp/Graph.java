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

public class Graph extends JFrame {
    
    private int graphWidth;
    private int graphHeight;
    private ArrayList<Coordinates> coordinates;
    private int domain;
    private int unitX;
    private int codomain;
    private int unitY;

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
	    new SpinnerNumberModel(320, 320, 1080, 10);
        final JSpinner spinnerW = new JSpinner(widthModel);
        SpinnerNumberModel heightModel = 
	    new SpinnerNumberModel(240, 240, 620, 10);
        final JSpinner spinnerH = new JSpinner(heightModel);
        SpinnerNumberModel x1Model = 
	    new SpinnerNumberModel(-5.0, -80.0, 79.0, 0.1);
        final JSpinner spinnerX1 = new JSpinner(x1Model);
        SpinnerNumberModel x2Model = 
	    new SpinnerNumberModel(5.0, -79.0, 80.0, 0.1);
        final JSpinner spinnerX2 = new JSpinner(x2Model);
        SpinnerNumberModel y1Model = 
	    new SpinnerNumberModel(-5.0, -40.0, 39.0, 0.1);
        final JSpinner spinnerY1 = new JSpinner(y1Model);
        SpinnerNumberModel y2Model = 
	    new SpinnerNumberModel(5.0, -39.0, 40.0, 0.1);
        final JSpinner spinnerY2 = new JSpinner(y2Model);
	
	final Controller c = new Controller("cos(x) / x");

	unitX = (int)(Double.parseDouble(x2Model.getValue().toString()) -
		     Double.parseDouble(x1Model.getValue().toString()));

	domain = unitX * 1000;

	unitY = (int)(Double.parseDouble(y2Model.getValue().toString()) -
		     Double.parseDouble(y1Model.getValue().toString()));

	codomain = unitY * 1000;

	getXY(x1Model.getValue().toString(), x2Model.getValue().toString(), 
	      y1Model.getValue().toString(), y2Model.getValue().toString(), c);
	repaint();
	       
	/*
        grafica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Controller c = new Controller(functionT.getText());
                String msg = c.getMessage();

                if (msg != null) {
                    JOptionPane.showMessageDialog(null, msg);
                } else {
                    getXY(spinnerX1, spinnerX2, spinnerY1, spinnerY2, c);
		    repaint();
                }
            }
        });

        limpia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionT.setText("");
            }
        });

        spinnerW.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                final Controller c = new Controller(functionT.getText());
                String msg = c.getMessage();

                if (msg != null) {
                    JOptionPane.showMessageDialog(null, msg);
                } else {
                    getXY(spinnerX1, spinnerX2, spinnerY1, spinnerY2, c);
		    repaint();
                }
            }
        });
	
	spinnerH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                final Controller c = new Controller(functionT.getText());
                String msg = c.getMessage();

                if (msg != null) {
                    JOptionPane.showMessageDialog(null, msg);
                } else {
                    getXY(spinnerX1, spinnerX2, spinnerY1, spinnerY2, c);
		    repaint();
                }
            }
	    });
	*/
	getContentPane().setPreferredSize(new Dimension(660, 620));
	/*
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
        add(svg);*/
	
	pack();
        setResizable(false);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setVisible(true);
	
    }

    @Override 
    public void paint(Graphics g){

	Graphics2D g2d = (Graphics2D)g ;
	
	double gW = graphWidth / 2 + 10;
	double gH = graphHeight / 2 + 40;
	double factorX = graphWidth / unitX;
	double factorY = graphHeight / unitY;
	
	drawPlane(g2d, (int)gW, (int)gH);

	double x, y;
	Random rand = new Random();

	float red = rand.nextFloat(), 
	    green = rand.nextFloat(), blue = rand.nextFloat();
                
        g2d.setColor(new Color(red, green, blue));	
	g2d.setStroke(new BasicStroke(2));

	for(int i = 0; i < coordinates.size(); i++){
	    Coordinates c = coordinates.get(i);	    

	    if(Double.isNaN(c.getY()))
		continue;	    
	    
	    x = c.getX();
	    y = c.getY();	    
	      
	    Shape l = 
		new Line2D.Double((factorX * x) + gW, (factorY * -y) + gH, 
				  (factorX * x) + gW, (factorY * -y) + gH);
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

    private void getXY(String x1, String x2, String y1, String y2, 
		       Controller c) {

        double xMin = Double.parseDouble(x1), xMax = Double.parseDouble(x2),
	    yMin = Double.parseDouble(y1), yMax = Double.parseDouble(y2);

        int noOfPoints = (int) ((xMax - xMin) * graphWidth + 1);        	
	
	
        for (; xMin <= xMax; xMin += 0.01) {                     
	    double y = c.evaluate(xMin);	    
	    if(y < yMin || y > yMax)
		continue;

	    coordinates.add(new Coordinates(xMin, y));
        }	
    }   
}
