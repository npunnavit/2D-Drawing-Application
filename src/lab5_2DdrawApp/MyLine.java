/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_2DdrawApp;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

/**
 *
 * @author NPunn
 */
public class MyLine extends Shapes {
    
    //default constructor calling the super class constructor
    public MyLine(){
        super();
    }
    
    //constructor with coordinates, color, and stroke
    public MyLine(int x1, int x2, int y1, int y2, Paint color, Stroke stroke){
        super(x1, x2, y1, y2, color, stroke);
    }
    
    //override the draw method 
    @Override
    public void draw(Graphics2D g)
    {
        g.setPaint(getColor());
        g.setStroke(getStroke());
        g.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }
}