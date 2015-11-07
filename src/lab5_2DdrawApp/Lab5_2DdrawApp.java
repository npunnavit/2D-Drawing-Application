/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab5_2DdrawApp;

import javax.swing.JFrame;

/**
 *
 * @author pva5115
 */
public class Lab5_2DdrawApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DrawApp app = new DrawApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(700,600);
        app.setVisible(true);
    }
    
}
