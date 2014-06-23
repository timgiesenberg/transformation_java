// Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Polygon repräsentiert ein Polygon, also einen Körper mit beliebig
 * vielen Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class Polygon extends GraphicObject {
    
    // Class-Variable: Zähler für Benennung von unbenannten Polygone
    private static int numberOfPolygons;
    
    /**
     * Static-Initialisierer, setzt den Zähler für unbenannte Polygone auf 1
     */
    static {
        
        numberOfPolygons = 1;
        
    }
    
    /**
     * Constructor, der ein neues Polygon mit übergebenem Namen und Farbe
     * sowie der übergebenen Anzahl an Ecken erstellt
     * @param name Name des neuen Polygons
     * @param c Farbe des neuen Polygons
     * @param numberOfAngles Anzahl der Punkte des neuen Polygons
     * @param centerX x-Koordinate des Mittelpunkts des Polygons
     * @param centerY y-Koordinate des Mittelpunkts des Polygons
     */
    public Polygon(String name, Paint c, int numberOfAngles, double centerX, double centerY) {
        
        // Super-Constructor für ein Polygon aufrufen
        super(
            Polygon.generatedName(name), // Name
            numberOfAngles, // Anzahl der Ecken
            new Point2D(centerX, centerY), // Mittelpunkt des Polygons
            50 // Radius des Polygons, DEFAULT
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
    }
    
    /**
     * Die Class-Methode generatedName() ermittelt den Namen des Polygons.
     * @param s Namens-String, der zu überprüfen ist
     * @return Den Parameter-String oder einen generierten Namen, wenn dieser
     * null ist
     */
    private static String generatedName(String s) {
        
        // Namen überprüfen und ggf. abändern
        if (s == null) s = "Polygon " + numberOfPolygons++;
        return s;
        
    }
    
    /**
     * Die Methode getCopyInstance() erstellt eine Kopie des angesprochenen
     * Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Polygon getCopyInstance() {
        
        // Objekt kopieren
        Polygon p = new Polygon(
            this.getName() + "_copy", this.getFill(), 
            this.getNumberOfPoints(), 
            this.getCenter().getX(), this.getCenter().getY()
        );
        p.setPoints2D(this.getPoints2D());
        p.setCenter(this.getCenter());
        // ggf. verschieben, zur Deutlichkeit
        p.transform(Transformate.getTranslationMatrix(50, 50));
        // Kopie zurückgeben
        return p;
        
    }
    
    /**
     * Die Methode toString() liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder("Polygon[");

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
        
        numberOfPolygons = 1;
        
    }
}
