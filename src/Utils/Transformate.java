package Utils;

/**
 * Die Klasse Transformate stellt Hilfsmethoden zur Transformation von Objekten
 * bereit.
 * @author Das TransPlosion-Team
 */
public final class Transformate {
    
    /**
     * Der private Konstruktor verhindert, dass Objekte der Klasse erzeugt werden.
     */
    private Transformate(){}
    
    /**
     * Liefert eine Translationsmatrix mit den x- und y-Werten der Parameter zurück.
     * @param x x-Wert der Translation
     * @param y y-Wert der Translation
     * @return Translationsmatrix mit den übergebenen Werten für x und y
     */
    public static Matrix getTranslationMatrix(double x, double y){
        
        // neue Matrix erzeugen
    	Matrix m = new Matrix();
    	
        // Matrix modifizieren
        m.mat[0][0] = 1;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;
        m.mat[0][2] = x;
        m.mat[1][2] = y;
        
        // Matrix zurückgeben
        return m;

    }    
    
    /**
     * Liefert eine Translationsmatrix zurück, die ein Objekt in den Ursprung
     * translariert.
     * @param centerOfObject Mittelpunkt des zu verschiebenden Objekts
     * @return Translationsmatrix zur Translation eines Objekts in den Ursprung
     */
    public static Matrix getTranslationToOriginMatrix(Point2D centerOfObject){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = 1;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;
        m.mat[0][2] -= centerOfObject.getX();
        m.mat[1][2] -= centerOfObject.getY();
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine Rotationsmatrix zurück, die ein Objekt um den übergebenen
     * Winkel um den Ursprung dreht.
     * @param rotateAngle Winkel der Rotation
     * @return Rotationsmatrix zur Rotation des Objekts um den Ursprung
     */
    public static Matrix getRotateMatrix(double rotateAngle){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = Math.cos(Math.toRadians(rotateAngle));
        m.mat[0][1] = Math.sin(Math.toRadians(rotateAngle));
        m.mat[1][0] -= Math.sin(Math.toRadians(rotateAngle));
        m.mat[1][1] = Math.cos(Math.toRadians(rotateAngle));
        m.mat[2][2] = 1;
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine Skalierungsmatrix mit dem übergebenen Faktor sowohl in x-
     * als auch in y-Richtung.
     * @param factor Skalierungsfaktor
     * @return Skalierungsmatrix zur Skalierung eines Objekts in x- und y-Richtung
     */
    public static Matrix getScaleMatrix(double factor){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = factor;
        m.mat[1][1] = factor;
        m.mat[2][2] = 1;
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine Skalierungsmatrix mit dem übergebenen Faktor in x-Richtung.
     * @param factor Skalierungsfaktor
     * @return Skalierungsmatrix zur Skalierung eines Objekts in x-Richtung
     */
    public static Matrix getScaleXMatrix(double factor){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = factor;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine Skalierungsmatrix mit dem übergebenen Faktor in y-Richtung.
     * @param factor Skalierungsfaktor
     * @return Skalierungsmatrix zur Skalierung eines Objekts in y-Richtung
     */
    public static Matrix getScaleYMatrix(double factor){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = 1;
        m.mat[1][1] = factor;
        m.mat[2][2] = 1;
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine horizontale Spiegelungsmatrix zurück.
     * @return Horizontale Spiegelungsmatrix
     */
    public static Matrix getFlipHorizontalMatrix(){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = 1;
        m.mat[1][1] = -1;
        m.mat[2][2] = 1;    
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine vertikale Spiegelungsmatrix zurück.
     * @return Vertikale Spiegelungsmatrix
     */
    public static Matrix getFlipVerticalMatrix(){
        
        // Neue Matrix erzeugen
        Matrix m = new Matrix();
        
        // Matrix modifizieren
        m.mat[0][0] = -1;
        m.mat[1][1] = 1;
        m.mat[2][2] = 1;   
        
        // Matrix zurückgeben
        return m;
        
    }
    
    /**
     * Liefert eine neue Matrix, die ein Objekt um den angegebenen Winkel um seinen
     * Mittelpunkt rotiert.
     * @param rotateAngle Grad, um den das Objekt rotiert werden soll
     * @param center Mittelpunkt des Objekts
     * @return 
     */
    public static Matrix getRotateAroundCenterMatrix(double rotateAngle, Point2D center) {
        
        // Matrix zusammenbauen aus: In den Ursprung verschieben, drehen, zurück verschieben
        Matrix translationToOriginMatrix = Transformate.getTranslationToOriginMatrix(center);
        Matrix rotateMatrix = Transformate.getRotateMatrix(rotateAngle);
        Matrix translationMatrix = Transformate.getTranslationMatrix(center.getX(), center.getY());
        
        // Matrix miteinander multiplizieren
        Matrix totalMatrix = Transformate.multiplyMatrices(rotateMatrix, translationToOriginMatrix);
        totalMatrix = Transformate.multiplyMatrices(translationMatrix, totalMatrix);
        
        // Matrix zurückgeben
        return totalMatrix;
        
    }
    
    /**
     * Liefert eine neue Matrix zurück, die das Ergebnis einer Multiplikation
     * zweier Matrizen ist.
     * @param m Erste Matrix, steht links
     * @param n Zweite Matrix, steht rechts
     * @return Ergebnismatrix der Multiplikation der beiden Parametermatrizen
     */
    public static Matrix multiplyMatrices(Matrix m, Matrix n){
        
        // Neue Matrix erzeugen
        Matrix matrix = new Matrix();
        
        // Matrix nach "Zeile mal Spalte" berechnen
        for(int i = 0; i < 3; i++){
            
            for(int j = 0; j < 3; j++){
                
                // Skalarprodukt berechnen
                for(int k = 0; k < 3; k++){
                    
                    matrix.mat[i][j] += m.mat[i][k] * n.mat[k][j];
                    
                }
                
            }
            
        }
        
        // Matrix zurückgeben
        return matrix;
        
    }
    
    /**
     * Transformiert den übergebenen Punkt mit der übergebenen Matrix.
     * @param m Transformationsmatrix
     * @param p Zu transformierender Punkt
     */
    public static void transformatePointWithMatrix(Matrix m, Point2D p){
        
        // Aus Punkt einen Vektor mit homogener Koordinate erstellen
        double[] vector = {p.getX(),p.getY(),1};
        // Ergebnisvektor erstellen
        double[] result = new double[vector.length];
        
        // Ergebnisvektor berechnen
        for(int i = 0; i < vector.length; i++){
            
            result[i] = m.mat[i][0] * vector[0] + m.mat[i][1] * vector[1] + m.mat[i][2] * vector[2];
            
        }
        
        // Punkt-Objekt modifizieren
        p.setX(result[0]/result[2]);
        p.setY(result[1]/result[2]);
        
    }
    
}
