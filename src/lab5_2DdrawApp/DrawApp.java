/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab5_2DdrawApp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pva5115
 */

public class DrawApp extends JFrame {
    
    //Panels
    private final JPanel buttonPanel;
    private final JPanel buttonPanel1, buttonPanel2;
    private final DrawPanel panel;
    
    //GUI Components
    private final JButton undoButton;
    private final JButton clearButton;
    private final JButton redoButton;
    private final JLabel shapeLabel;
    private final JComboBox<String> shapeCombo;
    private final JCheckBox filledCheck;
    private final JCheckBox gradientCheck;
    private final JButton firstColorButton, secondColorButton;
    private final JLabel widthLabel;
    private final JTextField widthText;
    private final JLabel lengthLabel;
    private final JTextField lengthText;
    private final JCheckBox dashCheck;
    private final JLabel statusLabel;
    private final JButton browseButton;
    
    //variables
    private int shapeType;
    private Color color1, color2;
    private Paint paint;
    private Stroke stroke;
    private float width;
    private float[] dashes;
    
    public DrawApp(){
        
        super("2D Drawing Application");
        
        //initialise the variable
        shapeType = 1;
        color1 = Color.BLACK;
        color2 = Color.BLACK;
        stroke = new BasicStroke();
        width = 1;
        dashes = new float[1];
        dashes[0] = 1;
        
        setLayout(new BorderLayout());
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        
        buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout());
        
        buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout());
        
        undoButton = new JButton("Undo");
        buttonPanel1.add(undoButton);
        undoHandler uHandler = new undoHandler();
        undoButton.addActionListener(uHandler);
        
        redoButton = new JButton("Redo");
        buttonPanel1.add(redoButton);
        redoHandler reHandler = new redoHandler();
        redoButton.addActionListener(reHandler);
       
        clearButton = new JButton("Clear");
        buttonPanel1.add(clearButton);
        clearHandler clearhandler = new clearHandler();
        clearButton.addActionListener(clearhandler);
        
        shapeLabel = new JLabel("Shape:");
        buttonPanel1.add(shapeLabel);
        
        shapeCombo = new JComboBox();
        shapeCombo.addItem("Line");
        shapeCombo.addItem("Oval");
        shapeCombo.addItem("Rectangle");
        buttonPanel1.add(shapeCombo);
        ButtonHandler bHandler = new ButtonHandler();
        shapeCombo.addActionListener(bHandler);
        
        filledCheck = new JCheckBox("Filled");
        buttonPanel1.add(filledCheck);
        
        gradientCheck = new JCheckBox("Use Gradient");
        buttonPanel2.add(gradientCheck);
        
        firstColorButton = new JButton("1st Color...");
        buttonPanel2.add(firstColorButton);
        Color1Handler c1Handler = new Color1Handler();
        firstColorButton.addActionListener(c1Handler);
        
        secondColorButton = new JButton("2nd Color...");
        buttonPanel2.add(secondColorButton);
        Color2Handler c2Handler = new Color2Handler();
        secondColorButton.addActionListener(c2Handler);
        
        widthLabel = new JLabel("Line Width:");
        buttonPanel2.add(widthLabel);
        
        widthText = new JTextField(2);
        buttonPanel2.add(widthText);
        
        lengthLabel = new JLabel("Dash Length:");
        buttonPanel2.add(lengthLabel);
        
        lengthText = new JTextField(2);
        buttonPanel2.add(lengthText);
        
        dashCheck = new JCheckBox("Dashed");
        buttonPanel2.add(dashCheck);
        
        browseButton = new JButton("Browse");
        buttonPanel1.add(browseButton);
        
        buttonPanel.add(buttonPanel1);
        buttonPanel.add(buttonPanel2);
        add(buttonPanel, BorderLayout.NORTH);

        
        statusLabel = new JLabel("status");
        add(statusLabel, BorderLayout.SOUTH);
        
        
        panel = new DrawPanel(statusLabel);
        add(panel, BorderLayout.CENTER);
        
        MouseHandler mHandler = new MouseHandler();
        panel.addMouseListener(mHandler);
        
    }
    
    //handler for shapes combo box
    private class ButtonHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            switch ((String)shapeCombo.getSelectedItem()) {
                case "Oval":
                    shapeType = 2;
                    break;
                case "Rectangle":
                    shapeType = 3;
                    break;
                default:
                    shapeType = 1;
                    break;
            }
        }
    }
    
    //handler for choosing first color
    private class Color1Handler implements ActionListener
    {
        @Override 
        public void actionPerformed(ActionEvent e)
        {
            color1 = JColorChooser.showDialog(DrawApp.this, "Choose a Color", color1);
            
            if(color1 == null)
            {
                color1 = Color.BLACK;
            }
        }
    }
    
    //handler for choosing second color
    private class Color2Handler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            color2 = JColorChooser.showDialog(DrawApp.this, "Choose a Color", color2);
            
            if(color2 == null)
            {
                color2 = Color.BLACK;
            }
        }
    }
    
    //handler for undo drawing
    private class undoHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            panel.clearLastShape();
        }
    }
    
    //handler for clearing all drawing
    private class clearHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            panel.clearDrawing();
        }
    }
    
    private class redoHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            panel.redoLastShape();
        }
    }
    
    
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseEntered(MouseEvent e)
        {
            if(gradientCheck.isSelected())
            {
                paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
            }
            else
            {
                paint = color1;
            }
             
           if(dashCheck.isSelected())
            {
                stroke = new BasicStroke(getWidthStroke(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, getDashes(), 0);
            }
            else {
                stroke = new BasicStroke(getWidthStroke(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            }
            
            panel.setDrawPanel(shapeType, paint, filledCheck.isSelected(), stroke);
        }
    }
    
    //get size of stroke width from width text field
    public float getWidthStroke()
    {
        try {
            width = Float.parseFloat(widthText.getText());
        } catch (NumberFormatException ex) {
            width = 1;
        }
        return width;
    }
    
    //get dash length from length text field
    public float[] getDashes()
    {
        try {
            dashes[0] = Float.parseFloat(lengthText.getText());
        } catch (NumberFormatException ex) {
            dashes[0] = 1;
        }
        
        return dashes;
    }
}
