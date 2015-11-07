/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_2DdrawApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author NPunn
 */
public class DrawPanel extends JPanel {

    private Shapes[] shapes; 
    private int shapeCount = 0;
    private int shapeType;
    private Shapes currentShape;
    private Paint currentColor;
    private boolean fillShape;
    private final JLabel statusLabel;
    private Stroke stroke;

    
    

    
    public DrawPanel(JLabel label)
    {
        statusLabel = label;
        shapes = new Shapes[100];
        shapeCount = 0;
        shapeType = 1;
        currentShape = null;
        currentColor = Color.BLACK;
        setBackground(Color.WHITE);
        stroke = new BasicStroke();
        
        MouseHandler mHandler = new MouseHandler();
        addMouseListener(mHandler);
        addMouseMotionListener(mHandler);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 1; i <= shapeCount; i++) {
            if (shapes[i] != null) {
                shapes[i].draw((Graphics2D)g);
            }
        }

    }

    public void setDrawPanel(int shapeType, Paint color, boolean fill, Stroke stroke)
    {
        this.shapeType = shapeType;
        this.currentColor = color;
        this.fillShape = fill;
        this.stroke = stroke;
    }
    
    public void clearLastShape()
    {
        if(shapeCount >0){
            shapeCount--;
        }
        
        repaint();
    }
    
    public void clearDrawing()
    {
        shapeCount = 0;
        repaint();
    }
    
    public void redoLastShape()
    {
        if(shapes[shapeCount+1] != null ){
            shapeCount++;
        }
        repaint();
            
    }
    
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent event)
        {
            switch(shapeType)
            {
                case 1: currentShape = new MyLine(event.getX(), 0, event.getY(), 0, currentColor, stroke); break;
                case 2: currentShape = new MyOval(event.getX(), 0, event.getY(), 0, currentColor, fillShape, stroke); break;
                case 3: currentShape = new MyRectangle(event.getX(), 0, event.getY(), 0, currentColor, fillShape, stroke); break;
            }
            
            shapeCount++;
        }
       
        @Override
        public void mouseReleased(MouseEvent event)
        {
            currentShape.setX2(event.getX());
            currentShape.setY2(event.getY());
            shapes[shapeCount] = currentShape;
            currentShape = null;
            repaint();
        }
        
        @Override
        public void mouseMoved(MouseEvent event)
        {
            statusLabel.setText(String.format("(%s, %s)", event.getX(), event.getY()));
        }
        
        @Override
        public void mouseDragged(MouseEvent event)
        {
            currentShape.setX2(event.getX());
            currentShape.setY2(event.getY());
            
            shapes[shapeCount] = currentShape;
            repaint();
            
            statusLabel.setText(String.format("(%s, %s)", event.getX(), event.getY()));
        }
    }
}
