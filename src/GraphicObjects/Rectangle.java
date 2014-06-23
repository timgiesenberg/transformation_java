 // Package spezifizieren
package GraphicObjects;

// Benötigte Libraries importieren
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Rectangle repräsentiert ein Rechteck, also ein Polygon mit vier
 * Ecken.
 * @author Phil Köster // Dominique Berners
 */
public class Rectangle extends GraphicObject {
    
    // Class-Variable: Zähler für Benennung von unbenannten Rechtecken
    private static int numberOfRectangles;
    
    /**
     * Static-Initialisierer, setzt den Zähler für unbenannte Rechtecke auf 1
     */
    static {
        
        numberOfRectangles = 1;
        
    }
    
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
    public Rectangle(String name, Paint c, double x, double y, double w, double h) {
        
        // Super-Constructor für ein Viereck aufrufen
        super(
            Rectangle.generatedName(name), // Name
            new Point2D[] {new Point2D(x,y), new Point2D(x+w,y), new Point2D(x+w,y+h), new Point2D(x,y+h)}, // Punkte des Rechtecks
            new Point2D((x+(w/2)), (y+(h/2))) // Mittelpunkt des Rechtecks
        );
        
        // Wenn Farbe übergeben wurde, diese setzen, sonst default
        if (c != null) this.setFill(c);
        else this.setFill(Color.BLACK);
        
    }
    
    /**
     * Die Class-Methode generatedName() ermittelt den Namen des Rechtecks.
     * @param s Namens-String, der zu überprüfen ist
     * @return Den Parameter-String oder einen generierten Namen, wenn dieser
     * null ist
     */
    private static String generatedName(String s) {
        
        // Namen überprüfen und ggf. abändern
        if (s == null) s = "Rechteck " + numberOfRectangles++;
        return s;
        
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

    /**
     * Die Methode getCopyInstance() erstellt eine Kopie des angesprochenen
     * Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Rectangle getCopyInstance() {
        
        Point2D[] points2D = this.getPoints2D();
        
        // Objekt kopieren
        Rectangle r = new Rectangle(
            this.getName() + "_copy", this.getFill(), 
            points2D[0].getX(), points2D[0].getY(), 
            this.getWidth(), this.getHeight()
        );
        r.setPoints2D(points2D);
        r.setCenter(this.getCenter());
        // ggf. verschieben, zur Deutlichkeit
        r.transform(Transformate.getTranslationMatrix(50, 50));
        // Kopie zurückgeben
        return r;
        
    }
    
    /**
     * Die Methode toString() liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder("Rectangle[");

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
        
        numberOfRectangles = 1;
        
    }
}
