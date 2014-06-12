// Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Circle repräsentiert einen ungefähren Kreis, also ein Polygon mit
 * sehr vielen Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class Circle extends GraphicObject {
    
    /**
     * Constructor, der einen neuen Kreis mit übergebenem Namen und Farbe
     * erstellt
     * @param name Name des neuen Kreises
     * @param c Füllfarbe des neuen Kreises
     * @param centerX x-Koordinate des Mittelpunkts des Kreises
     * @param centerY y-Koordinate des Mittelpunkts des Kreises
     * @param radius Radius des Kreises
     */
    public Circle(String name, Paint c, double centerX, double centerY, double radius) {
        
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
    
    /**
     * Die Methode getCopyInstance() erstellt eine Kopie des angesprochenen
     * Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Circle getCopyInstance() {
        
        // Objekt kopieren
        Circle c = new Circle(this.getName() + "_copy", this.getFill(), this.getCenter().getX(), this.getCenter().getY(), this.getRadius());
        c.setPoints2D(this.getPoints2D());
        c.setCenter(this.getCenter());
        // ggf. verschieben, zur Deutlichkeit
        c.transform(Transformate.getTranslationMatrix(50, 50));
        // Kopie zurückgeben
        return c;
        
    }
    
}