package GraphicObjects;

import Utils.Matrix;
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Line repräsentiert eine Linie, also quasi ein Polygon mit zwei
 * Ecken.
 * @author Das TransPlosion-Team
 */
public class Line extends GraphicObject {
    
    // Class-Variable: Zähler für Benennung von unbenannten Linien
    private static int numberOfLines;
    
    /**
     * Static-Initialisierer, setzt den Zähler für unbenannte Linien auf 1
     */
    static {
        
        numberOfLines = 1;
        
    }
    
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
            Line.generatedName(name), // Name
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
     * Ermittelt den Namen der Linie.
     * @param s Namens-String, der zu überprüfen ist
     * @return Den Parameter-String oder einen generierten Namen, wenn dieser
     * null ist
     */
    private static String generatedName(String s) {
        
        // Namen überprüfen und ggf. abändern
        if (s == null) s = "Linie " + numberOfLines++;
        return s;
        
    }
    
    /**
     * Liefert die Länge der Linie zurück.
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
     * Transformiert eine Linie so, dass sie so lang wie der Wert des Parameters 
     * ist.
     * @param l Neuer Radius-Wert des Kreises
     */
    public void setLength(double l) {
        
        // Faktor errechnen
        double scaleFactor = l / this.getLength();
        
        // Matrizen erstellen und miteinander multiplizieren
        Matrix translationToOriginMatrix = Transformate.getTranslationToOriginMatrix(this.getCenter());
        Matrix scaleMatrix = Transformate.getScaleMatrix(scaleFactor);
        Matrix translationMatrix = Transformate.getTranslationMatrix(this.getCenter().getX(), this.getCenter().getY());
        
        Matrix total = Transformate.multiplyMatrices(scaleMatrix, translationToOriginMatrix);
        total = Transformate.multiplyMatrices(translationMatrix, total);
        
        // Objekt transformieren
        this.transform(total);
        
    }
    
    /**
     * Erstellt eine Kopie des angesprochenen Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Line getCopyInstance() {
        
        // Punkte holen
        Point2D[] points2D = this.getPoints2D();
        
        // Objekt kopieren
        Line l = new Line(
            this.getName() + "_copy", this.getFill(), 
            points2D[0].getX(), points2D[0].getY(), 
            points2D[1].getX(), points2D[1].getY()
        );
        
        // verschieben, zur Deutlichkeit
        l.transform(Transformate.getTranslationMatrix(50, 50));
        
        // Kopie zurückgeben
        return l;
        
    }
    
    /**
     * Liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public String toString() {
        
        // Zusammenfassung zusammenbauen und zurückgeben
        final StringBuilder sb = new StringBuilder("Line[");

        sb.append("points=").append(this.getPoints());
        
        sb.append(", center=[").append(this.getCenter().getX()).append(", ").append(this.getCenter().getY()).append("]");

        sb.append(", fill=").append(this.getFill());
        
        sb.append(", name=").append(this.getName());

        return sb.append("]").toString();
    }
    
    /**
     * Setzt den Objektzähler zur Benennung von Objekten zurück.
     */
    public static void resetCounter() {
        
        numberOfLines = 1;
        
    }
    
}
