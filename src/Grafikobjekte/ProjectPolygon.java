// Package spezifizieren
package informatikprojekt;

// Benötigte Libraries importieren
import javafx.scene.paint.Color;

/**
 * Die Klasse Polygon repräsentiert ein Polygon, also einen Körper mit beliebig
 * vielen Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class ProjectPolygon extends GraphicObject {
    
    /**
     * Constructor, der ein neues Polygon mit übergebenem Namen und Farbe
     * sowie der übergebenen Anzahl an Ecken erstellt
     * @param name Name des neuen Polygons
     * @param c Farbe des neuen Polygons
     * @param numberOfAngles Anzahl der Punkte des neuen Polygons
     * @param centerX x-Koordinate des Mittelpunkts des Polygons
     * @param centerY y-Koordinate des Mittelpunkts des Polygons
     */
    public ProjectPolygon(String name, Color c, int numberOfAngles, double centerX, double centerY) {
        
        // Super-Constructor für ein Polygon aufrufen
        super(
            name, // Name
            numberOfAngles, // Anzahl der Ecken
            new Point2D(centerX, centerY), // Mittelpunkt des Polygons
            50 // Radius des Polygons, DEFAULT
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
    }
    
}
