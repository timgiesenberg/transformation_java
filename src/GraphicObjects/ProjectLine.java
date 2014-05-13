// Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import infoprojektjavafx.Point2D;
import javafx.scene.paint.Color;

/**
 * Die Klasse Line repräsentiert eine Linie, also quasi ein Polygon mit zwei
 * Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class ProjectLine extends GraphicObject {
    
    /**
     * Constructor, der eine neue Linie mit übergebenem Namen und Farbe
     * erstellt
     * @param name Name der neuen Linie
     * @param c Farbe der neuen Linie
     * @param fromX x-Koordinate des ersten Punktes
     * @param fromY y-Koordinate des ersten Punktes
     * @param toX x-Koordinate des zweiten Punktes
     * @param toY y-Koordinate des zweiten Punktes
     */
    public ProjectLine(String name, Color c, double fromX, double fromY, double toX, double toY) {
        
        // Super-Constructor für eine Linie aufrufen
        super(
            name, // Name
            new Point2D[] {new Point2D(fromX, fromY),new Point2D(toX, toY)}, // Punkte der Linie
            new Point2D((fromX + toX) / 2,(fromY + toY) / 2) // Mittelpunkt der Linie
        );

        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
        // Zusätzlich Rahmenfarbe und -breite setzen, damit eine Linie
        // überhaupt sichtbar wird
        if (c != null) this.setStroke(c);
        else this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        
    }
    
    /**
     * Die Methode getLength() liefert die Länge der Linie zurück.
     * @return Länge der Linie
     */
    public double getLength() {
        
        // Punkte holen
        Point2D[] points = this.getPoints2D();
        
        // Vektor zwischen den Punkten
        double[] vector = new double[2];
        vector[0] = points[1].getX() - points[0].getX();
        vector[1] = points[1].getY() - points[0].getY();
        
        // Länge des Vektors berechnen und zurückgeben
        double length = Math.sqrt((vector[0]*vector[0])+(vector[1]*vector[1]));
        return length;
        
    }
    
}
