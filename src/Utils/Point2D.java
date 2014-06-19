/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 * 
 * @author tim.giesenberg@me.com
 */
public class Point2D {
    
    private double x, y;
    
    public Point2D() {
        
        this.x = 0;
        this.y = 0;
        
    }
    
    public Point2D(double newX, double newY) {
        
        this.x = newX;
        this.y = newY;
        
    }
    
    public void setX (double newX) {
        
        this.x = newX;
        
    }
    
    public double getX() {
        
        return this.x;
        
    }
    
    public void setY (double newY) {
        
        this.y = newY;
        
    }
    
    public double getY() {
        
        return this.y;
        
    }
    
}
