// Package spezifizieren
package informatikprojekt;

// Benötigte Libraries importieren
import javafx.scene.paint.Color;

/**
 * Die Klasse Circle repräsentiert einen ungefähren Kreis, also ein Polygon mit
 * sehr vielen Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class ProjectCircle extends GraphicObject {
    
    /**
     * Constructor, der einen neuen Kreis mit übergebenem Namen und Farbe
     * erstellt
     * @param name Name des neuen Kreises
     * @param c Füllfarbe des neuen Kreises
     * @param centerX x-Koordinate des Mittelpunkts des Kreises
     * @param centerY y-Koordinate des Mittelpunkts des Kreises
     * @param radius Radius des Kreises
     */
    public ProjectCircle(String name, Color c, double centerX, double centerY, double radius) {
        
        // Super-Constructor für einen Kreis aufrufen
        super(
            name, // Name
            3600, // Anzahl der Ecken
            new Point2D(centerX,centerY), // Mittelpunkt 
            radius // Radius
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default: Schwarz
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
    }
    
    /**
     * Die Funktion getRadius liefert den Radius des Kreises.
     * @return Radius des Kreises
     */
    public double getRadius() {
        
        // Zwei Punkte holen
        Point2D center = this.getCenter();
        Point2D anyPoint = this.getPoints2D()[0];
        // Vektor dazwischen berechnen
        double[] vector = new double[2];
        vector[0] = anyPoint.getX() - center.getX();
        vector[1] = anyPoint.getY() - center.getY();
        // Länge des Vektors ist der Radius
        double radiusLength = Math.sqrt((vector[0]*vector[0])+(vector[1]*vector[1]));
        return radiusLength;
        
    }
    
}