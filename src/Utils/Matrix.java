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
public class Matrix {
    
    //2d array to emulate a matrix
    public double [][] mat = new double[3][3];
    
    /**
     * Generiert eine leereMatrix
     */
    public Matrix(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                    mat[i][j] = 0;                    
            }
        }
    }
    
/*    Nicht notwendig?
     
      /**
       * generates a matrix with now (???)
       * @param k
       *
      public Matrix(int k){
          for(int i = 0; i < 3; i++){
              for(int j = 0; j < 3; j++){
                      mat[i][j] = k;
                      k++;
              }
          }
      }
      
      /**
       * generates a matrix with given points
       * @param x
       * @param y 
       *
      public Matrix(int x, int y){
          mat[0][2] = x;
          mat[1][2] = y;
          mat[2][2] = 1;
      }
*/    
    
    /**
     * prints out the matrix in a useful style
     */
    protected void printMatrix(String s){
        System.out.println("Matrix: " + s);
        for(int i = 0; i <=2; i++){
            System.out.println(mat[i][0] + " " + mat[i][1] + " " + mat[i][2]);
        }
    }
    protected void printMatrix(){
        System.out.println("Matrix:");
        for(int i = 0; i <=2; i++){
            System.out.println(mat[i][0] + " " + mat[i][1] + " " + mat[i][2]);
        }
    }
    
    
    
}
