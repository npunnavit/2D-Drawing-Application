/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_2DdrawApp;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import static java.lang.Math.abs;

/**
 *
 * @author NPunn
 */

//bounded shape class. subclass of shapes and superclass of Oval and Rectangle
public abstract class MyBoundedShape extends Shapes {
    
    private int width, height, x, y;
    
    public MyBoundedShape(){
        super();
        width = height = 0;
    }
    
    public MyBoundedShape(int x1, int x2, int y1, int y2, Paint color, boolean fill, Stroke stroke)
    {
        super(x1, x2, y1, y2, color, stroke);
        setFill(fill);
        width = abs(x1 - x2);
        height = abs(y1 - y2);
        x = (x1 < x2) ? x1 : x2;
        y = (y1 < y2) ? y1 : y2;
    }
    
    @Override
    public abstract void draw (Graphics2D g);

    /**
     * @return the width
     */
    public int getWidth() {
        setWidth();
        return width;
    }

    /**
     */
    public void setWidth() {
        width = abs(getX1() - getX2());
    }

    /**
     * @return the height
     */
    public int getHeight() {
        setHeight();
        return height;
    }

    /**
     */
    public void setHeight() {
        height = abs(getY1() - getY2());
    }

    /**
     * @return the x
     */
    public int getX() {
        setX();
        return x;
    }

    /**
     */
    public void setX() {
        x = (getX1() < getX2()) ? getX1() : getX2();
    }

    /**
     * @return the y
     */
    public int getY() {
        setY();
        return y;
    }

    /**t
     */
    public void setY() {
        y = (getY1() < getY2()) ? getY1(): getY2();
    }
}
