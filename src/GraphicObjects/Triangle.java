package GraphicObjects;

import Utils.Matrix;
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Triangle repräsentiert ein Rechteck, also ein Polygon mit drei
 * Ecken.
 * @author Das TransPlosion-Team
 */
public class Triangle extends GraphicObject {
    
    // Class-Variable: Zähler für Benennung von unbenannten Dreiecken
    private static int numberOfTriangles;
    
    /**
     * Static-Initialisierer, setzt den Zähler für unbenannte Dreiecken auf 1
     */
    static {
        
        numberOfTriangles = 1;
        
    }
    
    /**
     * Constructor, der ein neues Dreieck mit übergebenem Namen und Farbe
     * erstellt
     * @param name Name des neuen Dreiecks
     * @param c Farbe des neuen Dreiecks
     * @param centerX x-Koordinate des Mittelpunkts
     * @param centerY y-Koordinate des Mittelpunkts
     * @param side Seitenlänge des gleichseitigen Dreiecks
     */
    public Triangle (String name, Paint c, double centerX, double centerY, double side) {
        
        // Super-Constructor für ein Dreieck aufrufen
        super(
            Triangle.generatedName(name), // Name
            3, // Anzahl der Ecken
            new Point2D(centerX, centerY), // Mittelpunkt des Dreiecks
            Math.sqrt(side*side/3) // 'Radius' des Dreiecks, errechnet aus der Seitenlänge // Wurzel aus (1/3)*s^2
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
          
    }
    
    /**
     * Ermittelt den Namen des Dreiecks.
     * @param s Namens-String, der zu überprüfen ist
     * @return Den Parameter-String oder einen generierten Namen, wenn dieser
     * null ist
     */
    private static String generatedName(String s) {
        
        // Namen überprüfen und ggf. abändern
        if (s == null) s = "Dreieck " + numberOfTriangles++;
        return s;
        
    }
    
    /**
     * Liefert die Seitenlänge des gleichseitigen Dreiecks zurück.
     * @return Seitenlänge des Dreiecks
     */
    public double getSideLength() {
        
        // Punkte holen
        Point2D[] points = this.getPoints2D();
        
        // Vektor dazwischen berechnen
        double[] vector = new double[2];
        vector[0] = points[1].getX() - points[0].getX();
        vector[1] = points[1].getY() - points[0].getY();
        
        // Länge des Vektors ist der Radius
        double sideLength = Math.sqrt((vector[0]*vector[0])+(vector[1]*vector[1]));
        return sideLength;
        
    }
    
    /**
     * Transformiert ein gleichseitiges Dreieck so, dass die Seitenlänge des 
     * Dreiecks dem Wert des Parameters entspricht.
     * @param s Neuer Radius-Wert des Kreises
     */
    public void setSideLength(double s) {
        
        // Faktor errechnen
        double scaleFactor = s / this.getSideLength();
        
        // Matrizen erstellen und miteinander multiplizieren
        Matrix translationToOriginMatrix = Transformate.getTranslationToOriginMatrix(this.getCenter());
        Matrix scaleMatrix = Transformate.getScaleMatrix(scaleFactor);
        Matrix translationMatrix = Transformate.getTranslationMatrix(this.getCenter().getX(), this.getCenter().getY());
        
        Matrix total = Transformate.multiplyMatrices(scaleMatrix, translationToOriginMatrix);
        total = Transformate.multiplyMatrices(translationMatrix, total);
        
        // Objekt transformieren
        this.transform(total);
        
    }
    
    /**
     * Erstellt eine Kopie des angesprochenen Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Triangle getCopyInstance() {
        
        // Objekt kopieren
        Triangle t = new Triangle(this.getName() + "_copy", this.getFill(), this.getCenter().getX(), this.getCenter().getY(), this.getSideLength());
        
        // Punkte holen, kopieren und setzen
        Point2D[] points2D = this.getPoints2D();
        Point2D[] points2Dcopy = new Point2D[points2D.length];
        for (int i = 0; i < points2D.length; i++) {
            
            points2Dcopy[i] = new Point2D(points2D[i].getX(), points2D[i].getY());
            
        }
        t.setPoints2D(points2Dcopy);
        
        // verschieben, zur Deutlichkeit
        t.transform(Transformate.getTranslationMatrix(50, 50));
        
        // Kopie zurückgeben
        return t;
        
    }
    
    /**
     * Liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public String toString() {
        
        // Zusammenfassung zusammenbauen und zurückgeben
        final StringBuilder sb = new StringBuilder("Triangle[");

        sb.append("points=").append(this.getPoints());
        
        sb.append(", center=[").append(this.getCenter().getX()).append(", ").append(this.getCenter().getY()).append("]");

        sb.append(", fill=").append(this.getFill());
        
        sb.append(", name=").append(this.getName());

        return sb.append("]").toString();
    }
    
    /**
     * Setzt den Objektzähler zur Benennung von Objekten zurück.
     */
    public static void resetCounter() {
        
        numberOfTriangles = 1;
        
    }
    
}
