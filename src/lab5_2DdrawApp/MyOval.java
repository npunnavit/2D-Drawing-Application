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
public class MyOval extends MyBoundedShape{
    
    //default construct for oval
    public MyOval(){
        super();
    }
    
    
    public MyOval(int x1, int x2, int y1, int y2, Paint color, boolean fill, Stroke stroke)
    {
        super(x1, x2, y1, y2, color, fill, stroke);
    }
    
    //override the draw method
    @Override
    public void draw(Graphics2D g)
    {
        g.setPaint(getColor());
        g.setStroke(getStroke());
        
        if(isFill()){
            g.fillOval(getX(), getY(), getWidth(), getHeight());
        }
        else {
            g.drawOval(getX(), getY(), getWidth(), getHeight());
        }
    }
}
