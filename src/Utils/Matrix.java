package Utils;

/**
 * Die Klasse Matrix repräsentiert eine 3x3-Matrix zur Benutzung für die 2D-
 * Transformation von Grafikobjekten.
 * @author Das TransPlosion-Team
 */
public class Matrix {
    
    // 2D-Array, das die Zellen der Matrix repräsentiert
    public double [][] mat = new double[3][3];
    
    /**
     * Der Constructor erstellt eine mit Nullen gefüllte Matrix.
     */
    public Matrix(){
        
        for(int i = 0; i < 3; i++){
            
            for(int j = 0; j < 3; j++){
                
                mat[i][j] = 0;   
                    
            }
            
        }
        
    }
    
    /**
     * Gibt die Matrix inkl. Titel auf der Konsole aus.
     * @param s Titel der Matrix
     */
    protected void printMatrix(String s){
        
        System.out.println("Matrix: " + s);
        for(int i = 0; i <=2; i++){
            
            System.out.println(mat[i][0] + " " + mat[i][1] + " " + mat[i][2]);
            
        }
        
    }
    
    /**
     * Gibt die Matrix auf der Konsole aus.
     */
    protected void printMatrix(){
        
        System.out.println("Matrix:");
        for(int i = 0; i <=2; i++){
            
            System.out.println(mat[i][0] + " " + mat[i][1] + " " + mat[i][2]);
            
        }
        
    }
    
}
