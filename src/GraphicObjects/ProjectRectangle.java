// Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import infoprojektjavafx.Point2D;
import javafx.scene.paint.Color;

/**
 * Die Klasse Rectangle repräsentiert ein Rechteck, also ein Polygon mit vier
 * Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class ProjectRectangle extends GraphicObject {
    
    /**
     * Constructor, der ein neues Rechteck mit übergebenem Namen und Farbe
     * erstellt
     * @param name Name des neuen Rechtecks
     * @param c Farbe des neuen Rechtecks
     * @param x x-Koordinate der linken, oberen Ecke
     * @param y y-Koordinate der linken, oberen Ecke
     * @param w Breite des Rechtecks
     * @param h Höhe des Rechtecks
     */
    public ProjectRectangle(String name, Color c, double x, double y, double w, double h) {
        
        // Super-Constructor für ein Viereck aufrufen
        super(
            name, // Name
            new Point2D[] {new Point2D(x,y), new Point2D(x+w,y), new Point2D(x+w,y+h), new Point2D(x,y+h)}, // Punkte des Rechtecks
            new Point2D((x+(w/2)), (y+(h/2))) // Mittelpunkt des Rechtecks
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
    }
    
    /**
     * Die Methode getWidth() liefert die Breite des Rechtecks zurück.
     * @return Breite des Rechtecks
     */
    public double getWidth() {
        
        // Punkte holen
        Point2D[] points = this.getPoints2D();
        // Breite berechnen und zurückgeben
        double width = points[1].getX() - points[0].getX();
        return width;
        
    }
    
    /**
     * Die Methode getHeight() liefert die Höhe des Rechtecks zurück.
     * @return Höhe des Rechtecks
     */
    public double getHeight() {
        
        // Punkte holen
        Point2D[] points = this.getPoints2D();
        // Höhe berechnen und zurückgeben
        double height = points[3].getY() - points[0].getY();
        return height;
        
    }
    
}
