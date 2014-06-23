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
    
    // Class-Variable: Zähler für Benennung von unbenannten Kreisen
    private static int numberOfCircles;
    
    /**
     * Static-Initialisierer, setzt den Zähler für unbenannte Kreise auf 1
     */
    static {
        
        numberOfCircles = 1;
        
    }
    
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
            Circle.generatedName(name), // Name
            3600, // Anzahl der Ecken
            new Point2D(centerX,centerY), // Mittelpunkt 
            radius // Radius
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default: Schwarz
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
    }
    
    /**
     * Die Class-Methode generatedName() ermittelt den Namen des Kreises.
     * @param s Namens-String, der zu überprüfen ist
     * @return Den Parameter-String oder einen generierten Namen, wenn dieser
     * null ist
     */
    private static String generatedName(String s) {
        
        // Namen überprüfen und ggf. abändern
        if (s == null) s = "Kreis " + numberOfCircles++;
        return s;
        
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
    
    /**
     * Die Methode toString() liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder("Circle[");

        sb.append("points=").append(this.getPoints());
        
        sb.append(", center=[").append(this.getCenter().getX()).append(", ").append(this.getCenter().getY()).append("]");

        sb.append(", fill=").append(this.getFill());
        
        sb.append(", name=").append(this.getName());

        return sb.append("]").toString();
    }
    
    /**
     * Die Methode resetCounter() setzt den Objektzähler zur Benennung von
     * Objekten zurück.
     */
    public static void resetCounter() {
        
        numberOfCircles = 1;
        
    }
}