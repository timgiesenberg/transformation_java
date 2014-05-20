/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import com.sun.javafx.geom.Vec2d;

/**
 *
 * @author tim.giesenberg@me.com
 */
public final class Transformate {
    
    /**
     * private Constructor denies object generating
     */
    private Transformate(){       
    }
    
    /**
     * Translates a Point to coordinate origin (x=0, y=0)
     * Generates a Translation vector.
     * @param p
     * @return originTranslation
     */
    public static Point2D translateObjectToOrigin(Point2D p){
        
        double originX =  p.x - p.x;
        double originY = p.y - p.y;

        Point2D originTranslation = new Point2D(originX, originY);
            
        return originTranslation;
    }
    
    public static Point2D translateOtherPointsToOrigin(Point2D p, Point2D translate){
        return null;
    }
    
    /**
     * (1 0 tx)   (x)
     * (0 1 ty) X (y)
     * (0 0  1)   (1)
     * @param p
     * @param translate
     * @return 
     */
    public static Matrix translateToPoint(Point2D p, Point2D newPoint){
        //new Matrix
    	Matrix m= new Matrix();
    	
    	// Vector of translation into Matrix
    	m.mat[0][2] = newPoint.x - p.x;
        m.mat[1][2] = newPoint.y - p.y;
        m.mat[2][2] = 1;
        
        return m;
    }
    public static Matrix getTranslateMatrix(Point2D translate){
        Matrix m = new Matrix();
        m.mat[0][2] = translate.x;
        m.mat[1][2] = translate.y;
        m.mat[2][2] = 1;
        m.printMatrix("translate point inserted");
        return m;
    }
    
    /**
     * (cos -sin 0)   (x)
     * (sin  cos 0) X (y)
     * ( 0    0  1)   (1)
     * @param p
     * @param rotate
     * @return 
     */
    public static Point2D rotateToPoint(Point2D p, double rotate){
        return new Point2D();
    }
    public static Matrix getRotateMatrix(double rotate){
        Matrix m = new Matrix();
        m.mat[0][0] = Math.cos(rotate);
        m.mat[0][1] = Math.sin(rotate);
        m.mat[1][0] -= Math.sin(rotate);
        m.mat[1][1] = Math.cos(rotate);
        m.printMatrix("rotate angle inserted");
        return m;
    }
    
    /**
     * (sx 0 0)   (x)
     * (0 sy 0) X (y)
     * (0  0 1)   (1)
     * @param p
     * @param rotate
     * @return 
     */
    public static Point2D scaleToPoint(Point2D p, double rotate){
        return new Point2D();
    }
    
    /**
     * (sx 0 0)
     * (0 sy 0)
     * (0  0 1) 
     * @param scale
     * @return 
     */
    public static Matrix getScaleMatrix(Point2D scale){
        Matrix m = new Matrix();
        m.mat[0][0] = scale.x;
        m.mat[1][1] = scale.y;
        m.mat[2][2] = 1;
        m.printMatrix("scale factor inserted");
        return m;
    }
    
    /**
     * Zeile mal Spalte
     * @param m
     * @param n
     * @return 
     */
    public static Matrix muliplyMatrices(Matrix m, Matrix n){
        Matrix matrix =  new Matrix();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    matrix.mat[i][j] += m.mat[k][j] * n.mat[i][k];
                }
            }
        }
        return matrix;
    }
    
    /**
     * takes a Matrix and a vector, multiplies them, to get a new vector
     * @param m
     * @param vector
     * @return 
     */
    public static double[] mulitplyMatricesWithVector(Matrix m, double[] vector){
        double [] out = {0,0,0};
        for(int i = 0; i < vector.length; i++){
            for(int j = 0; j < 3; j++){
                out[i] += m.mat[i][j] * vector[j];
               System.out.println("i: " +i + " " +vector[i] + " += " + "" + m.mat[i][j] + " * " + vector[j]);
            }
        }
        return out;
    }
}
