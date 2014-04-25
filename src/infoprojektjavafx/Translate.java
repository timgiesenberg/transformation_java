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
    
    /**
     * Translates a Point to coordinate origin (x=0, y=0)
     * Generates a Translation vector.
     * @param p
     * @return originTranslation
     */
    public static Point2D translateObjectToOrigin(Point2D p){
        
        int originX =  p.x - p.x;
        int originY = p.y - p.y;

        Point2D originTranslation = new Point2D(originX, originY);
            
        return originTranslation;
    }
    
    public static Point2D translateOtherPointsToOrigin(Point2D p, Point2D translate){
        return null;
    }
    
    public static Point2D translate(Point2D p, Point2D translate){
        return p;
    }
    
    public static Point2D rotate(Point2D p, double rotate){
        return p;
    }
}
