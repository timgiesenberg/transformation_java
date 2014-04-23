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
public class Matrix {
    
    public int [][] mat = new int[3][3];
    
    public Matrix(){
        mat[0][0] = 1;
        mat[1][1] = 1;
        mat[2][2] = 1;
    }
    
    public void printMatrix(){
        for(int i = 0; i <=2; i++){
            System.out.println(mat[i][0] + " " + mat[i][1] + " " + mat[i][2]);
        }
    }
    
    
    
}
