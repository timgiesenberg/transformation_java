// Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Triangle repräsentiert ein Rechteck, also ein Polygon mit drei
 * Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class Triangle extends GraphicObject {
    
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
            name, // Name
            3, // Anzahl der Ecken
            new Point2D(centerX, centerY), // Mittelpunkt des Dreiecks
            Math.sqrt(side*side/3) // 'Radius' des Dreiecks, errechnet aus der Seitenlänge // Wurzel aus (1/3)*s^2
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
          
    }
    
    /**
     * Die Methode getSideLength() liefert die Seitenlänge des
     * rechtwinkligen Dreiecks zurück.
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
     * Die Methode getCopyInstance() erstellt eine Kopie des angesprochenen
     * Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Triangle getCopyInstance() {
        
        // Objekt kopieren
        Triangle t = new Triangle(this.getName() + "_copy", this.getFill(), this.getCenter().getX(), this.getCenter().getY(), this.getSideLength());
        t.setPoints2D(this.getPoints2D());
        t.setCenter(this.getCenter());
        // ggf. verschieben, zur Deutlichkeit
        t.transform(Transformate.getTranslationMatrix(50, 50));
        // Kopie zurückgeben
        return t;
        
    }
    
}
