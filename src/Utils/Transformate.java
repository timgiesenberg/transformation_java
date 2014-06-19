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
public final class Transformate {
    
    /**
     * private Constructor denies object generating
     */
    private Transformate(){       
    }
    
    /**
     * Generates a matrix that you can use to translate an object to the origin
     * @param p
     * @return originTranslation
     */
    public static Matrix getTranslationToOriginMatrix(Point2D centerOfObject){
        
        Matrix m = new Matrix();
        m.mat[0][0] = 1;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;
        m.mat[0][2] -= centerOfObject.getX();
        m.mat[1][2] -= centerOfObject.getY();
        return m;
    }
    
    /**
     * (1 0 tx)   (x)
     * (0 1 ty) X (y)
     * (0 0  1)   (1)
     * 
     * @param vector of translation (TranslationBar)
     * @return 
     */
    public static Matrix getTranslationMatrix(double x, double y){
        //new Matrix
    	  Matrix m= new Matrix();
    	
        m.mat[0][0] = 1;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;
        
    	// Vector of translation into Matrix
    	  m.mat[0][2] = x;
        m.mat[1][2] = y;
       
        
        
        return m;
    }
    
    /**
    public static Matrix getTranslateMatrix(Point2D translate){
        Matrix m = new Matrix();
        m.mat[0][2] = translate.x;
        m.mat[1][2] = translate.y;
        m.mat[2][2] = 1;
        m.printMatrix("translate point inserted");
        return m;
    } */
    
    /**
     * (cos -sin 0)   (x)
     * (sin  cos 0) X (y)
     * ( 0    0  1)   (1)
     * @param p
     * @param rotate
     * @return 
     */
     
     
    /** 
    public static Point2D rotateToPoint(Point2D p, double rotate){
        return new Point2D();
    }*/
    
    
    public static Matrix getRotateMatrix(double rotateAngle){
        Matrix m = new Matrix();
        m.mat[0][0] = Math.cos(Math.toRadians(rotateAngle));
        m.mat[0][1] = Math.sin(Math.toRadians(rotateAngle));
        m.mat[1][0] -= Math.sin(Math.toRadians(rotateAngle));
        m.mat[1][1] = Math.cos(Math.toRadians(rotateAngle));
        m.mat[2][2] = 1;
       // m.printMatrix("rotate angle inserted");
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
  /**  public static Point2D scaleToPoint(Point2D p, double rotate){
        return new Point2D();
    }*/
    
    /**
     * (sx 0 0)
     * (0 sy 0)
     * (0  0 1) 
     * @param percent
     * @return 
     */
    public static Matrix getScaleMatrix(double percent){
        Matrix m = new Matrix();
        m.mat[0][0] = percent/100;
        m.mat[1][1] = percent/100;
        m.mat[2][2] = 1;
       
        //m.printMatrix("scale factor inserted");
        return m;
    }
    
    /**
     * (1  0 0)
     * (0 -1 0)
     * (0  0 1) 
     * @return 
     */
    public static Matrix getFlipHorizontalMatrix(){
        
        Matrix m = new Matrix();
        m.mat[0][0] = 1;
        m.mat[1][1] = -1;
        m.mat[2][2] = 1;    
        return m;
        
    }
    
    /**
     * (-1 0 0)
     * (0  1 0)
     * (0  0 1) 
     * @return 
     */
    public static Matrix getFlipVerticalMatrix(){
        
        Matrix m = new Matrix();
        m.mat[0][0] = -1;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;        
        return m;
        
    }
    
    public static Matrix getRotateAroundCenterMatrix(double rotateAngle, Point2D center) {
        
        Matrix translationToOriginMatrix = Transformate.getTranslationToOriginMatrix(center);
        Matrix rotateMatrix = Transformate.getRotateMatrix(rotateAngle);
        Matrix translationMatrix = Transformate.getTranslationMatrix(center.getX(), center.getY());
        
        Matrix totalMatrix = Transformate.multiplyMatrices(rotateMatrix, translationToOriginMatrix);
        totalMatrix = Transformate.multiplyMatrices(translationMatrix, totalMatrix);
        
        return totalMatrix;
        
    }
    
    /**
     * Zeile mal Spalte
     * @param m
     * @param n
     * @return 
     */   
    public static Matrix multiplyMatrices(Matrix m, Matrix n){
        Matrix matrix = new Matrix();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    matrix.mat[i][j] += m.mat[i][k] * n.mat[k][j];
                }
            }
        }
        return matrix;
    }
    
    /**
     * takes a Matrix and a point, multiplies them (Matrix*vector), to transformate the point
     * @param m
     * @param p
     */
    public static void transformatePointWithMatrix(Matrix m, Point2D p){
        double[] vector = {p.getX(),p.getY(),1};
        double[] result = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            result[i] = m.mat[i][0] * vector[0] + m.mat[i][1] * vector[1] + m.mat[i][2] * vector[2];
        }
        p.setX(result[0]/result[2]);
        p.setY(result[1]/result[2]);
    }
}
