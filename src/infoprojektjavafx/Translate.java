/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoprojektjavafx;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class Translate {
    
    public static Point2D translateToOrigin(Point2D p, Point2D translate){
        
            System.out.println(p.x + " " + p.y);
            int a =  p.x - translate.x;
            int b = p.y - translate.y;
            
            System.out.println(a + " " + b);
            
        return null;
    }
}
