package GraphicObjects;

import Utils.Matrix;
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Die Klasse Rectangle repräsentiert ein Rechteck, also ein Polygon mit vier
 * Ecken.
 * @author Das TransPlosion-Team
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
     * Ermittelt den Namen des Rechtecks.
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
     * Liefert die Breite des Rechtecks zurück.
     * @return Breite des Rechtecks
     */
    public double getWidth() {
        
        // Punkte holen
        Point2D[] points = this.getPoints2D();
        
        double[] vector = new double[2];
        vector[0] = points[1].getX() - points[0].getX();
        vector[1] = points[1].getY() - points[0].getY();
        
        // Breite berechnen und zurückgeben
        double width = Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1]);
        return width;
    }
    
    /**
     * Transformiert ein Rechteck so, dass die Breite des Rechtecks dem Wert des
     * Parameters entspricht.
     * @param w Neue Breite des Rechtecks
     */
    public void setWidth(double w) {
        
        // Objekt zum Mittelpunkt verschieben (und am Ende wieder zurück verschieben)
        Matrix translationToOriginMatrix = Transformate.getTranslationToOriginMatrix(this.getCenter());
        Matrix translationMatrix = Transformate.getTranslationMatrix(this.getCenter().getX(), this.getCenter().getY());
        this.transform(translationToOriginMatrix);
        
        // Faktor errechnen
        double scaleFactor = w / this.getWidth();
        
        // Gradanzahl berechnen, um die das Objekt gedreht wurde (um zurückzudrehen)
        Double rotated = Math.toDegrees(Math.acos(
            (this.getPoints2D()[1].getX() 
            + this.getPoints2D()[2].getX()) / this.getWidth()
        ));
        if (rotated.isNaN()) rotated = 180.0;
        if (this.getPoints2D()[1].getX() > this.getPoints2D()[2].getX()) rotated = -1 * rotated;
        
        // Matrizen erstellen: Zurückdrehen, in X-Richtung strecken, wieder drehen
        Matrix rotateBackwardsMatrix = Transformate.getRotateMatrix(-rotated);
        Matrix scaleMatrix = Transformate.getScaleXMatrix(scaleFactor);
        Matrix rotateMatrix = Transformate.getRotateMatrix(rotated);
        
        // Matrizen miteinander multiplizieren
        Matrix total = Transformate.multiplyMatrices(scaleMatrix, rotateBackwardsMatrix);
        total = Transformate.multiplyMatrices(rotateMatrix, total);
        total = Transformate.multiplyMatrices(translationMatrix, total);
        
        // Objekt transformieren
        this.transform(total);
        
    }
    
    /**
     * Liefert die Höhe des Rechtecks zurück.
     * @return Höhe des Rechtecks
     */
    public double getHeight() {
        
        // Punkte holen
        Point2D[] points = this.getPoints2D();
        
        double[] vector = new double[2];
        vector[0] = points[3].getX() - points[0].getX();
        vector[1] = points[3].getY() - points[0].getY();
        
        // Höhe berechnen und zurückgeben
        double height = Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1]);
        return height;
        
    }

    /**
     * Transformiert ein Rechteck so, dass die Höhe des Rechtecks dem Wert des
     * Parameters entspricht.
     * @param h Neue Höhe des Rechtecks
     */
    public void setHeight(double h) {
        
        // Objekt zum Mittelpunkt verschieben (und am Ende wieder zurück verschieben)
        Matrix translationToOriginMatrix = Transformate.getTranslationToOriginMatrix(this.getCenter());
        Matrix translationMatrix = Transformate.getTranslationMatrix(this.getCenter().getX(), this.getCenter().getY());
        this.transform(translationToOriginMatrix);
        
        // Faktor errechnen
        double scaleFactor = h / this.getHeight();
        
        // Gradanzahl berechnen, um die das Objekt gedreht wurde (um zurückzudrehen)
        Double rotated = Math.toDegrees(Math.acos(
            (this.getPoints2D()[1].getX() 
            + this.getPoints2D()[2].getX()) / this.getWidth()
        ));
        if (rotated.isNaN()) rotated = 180.0;
        if (this.getPoints2D()[1].getX() > this.getPoints2D()[2].getX()) rotated = -1 * rotated;
        
        // Matrizen erstellen: Zurückdrehen, in Y-Richtung strecken, wieder drehen
        Matrix rotateBackwardsMatrix = Transformate.getRotateMatrix(-rotated);
        Matrix scaleMatrix = Transformate.getScaleYMatrix(scaleFactor);
        Matrix rotateMatrix = Transformate.getRotateMatrix(rotated);
        
        // Matrizen miteinander multiplizieren
        Matrix total = Transformate.multiplyMatrices(scaleMatrix, rotateBackwardsMatrix);
        total = Transformate.multiplyMatrices(rotateMatrix, total);
        total = Transformate.multiplyMatrices(translationMatrix, total);
        
        // Objekt transformieren
        this.transform(total);
        
    }
    
    /**
     * Erstellt eine Kopie des angesprochenen Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    @Override
    public Rectangle getCopyInstance() {
        
        // Punkte holen
        Point2D[] points2D = this.getPoints2D();
        
        // Objekt kopieren
        Rectangle r = new Rectangle(
            this.getName() + "_copy", this.getFill(), 
            points2D[0].getX(), points2D[0].getY(), 
            this.getWidth(), this.getHeight()
        );
        
        // Punkte kopieren und setzen
        Point2D[] points2Dcopy = new Point2D[points2D.length];
        for (int i = 0; i < points2D.length; i++) {
            
            points2Dcopy[i] = new Point2D(points2D[i].getX(), points2D[i].getY());
            
        }
        r.setPoints2D(points2Dcopy);
        r.setCenter(new Point2D(this.getCenter().getX(), this.getCenter().getY()));
        
        // verschieben, zur Deutlichkeit
        r.transform(Transformate.getTranslationMatrix(50, 50));
        
        // Kopie zurückgeben
        return r;
        
    }
    
    /**
     * Liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public String toString() {
        
        // Zusammenfassung zusammenbauen und zurückgeben
        final StringBuilder sb = new StringBuilder("Rectangle[");

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
        
        numberOfRectangles = 1;
        
    }
    
}
