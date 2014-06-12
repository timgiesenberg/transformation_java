// Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Line repräsentiert eine Linie, also quasi ein Polygon mit zwei
 * Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class Line extends GraphicObject {
    
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
    public Line(String name, Paint c, double fromX, double fromY, double toX, double toY) {
        
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
        this.setStrokeWidth(3);
        
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
    
    /**
     * Die Methode getCopyInstance() erstellt eine Kopie des angesprochenen
     * Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Line getCopyInstance() {
        
        Point2D[] points2D = this.getPoints2D();
        
        // Objekt kopieren
        Line l = new Line(
            this.getName() + "_copy", this.getFill(), 
            points2D[0].getX(), points2D[0].getY(), 
            points2D[1].getX(), points2D[1].getY()
        );
        // ggf. verschieben, zur Deutlichkeit
        l.transform(Transformate.getTranslationMatrix(50, 50));
        // Kopie zurückgeben
        return l;
        
    }
    
}
