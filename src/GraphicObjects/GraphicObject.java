package GraphicObjects;

import Utils.Matrix;
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.Cursor;

/**
 * Die Klasse GraphicObject ist die Superklasse aller im Programm verfügbaren
 * Grafikobjekte. Alle werden als Polygone implementiert.
 * @author Das TransPlosion-Team
 */
public abstract class GraphicObject extends javafx.scene.shape.Polygon {
    
    // Instanzvariablen: Name, Farbe und Punkte des Objekts
    private String name;
    private final int numberOfAngles;
    private Point2D[] points;
    private Point2D center;
    // Class-Variable: Zähler für Benennung von unbenannten Objekten
    private static int numberOfObjects;
    
    /**
     * Static-Initialisierer, setzt den Zähler für unbenannte Objekte auf 1
     */
    static {
        
        numberOfObjects = 1;
        
    }
    
    /**
     * Constructor, der ein Polygon aus den übergebenen Punkten sowie dem Namen
     * und dem Mittelpunkt erstellt
     * @param _name Name des neuen Objekts
     * @param _points Punkte des neuen Objekts
     * @param _center Mittelpunkt des neuen Objekts
     */
    protected GraphicObject(String _name, Point2D[] _points, Point2D _center) {
        
        // Super-Constructor erstellt ein Polygon (noch ohne Punkte)
        super();
        
        // Wenn Name übergeben wurde, diesen setzen, sonst default
        if (_name != null) this.name = _name;
        else {
            
            // Default: Durchnummerierte Objekte, Zähler ist Class-Variable
            this.name = "Objekt " + GraphicObject.numberOfObjects;
            GraphicObject.numberOfObjects++;
            
        }        
        
        // Wenn meht als ein Punkt vorhanden ist
        if (_points != null && _points.length > 1) {
            
            // Übergebene Angaben setzen
            this.numberOfAngles = _points.length;
            this.points = _points;
            this.center = _center;
            
        }
        // sonst
        else {
            
            // Default-Linie erstellen
            this.numberOfAngles = 2;
            this.points = new Point2D[2];
            this.points[0] = new Point2D(50,50);
            this.points[1] = new Point2D(150,150);
            this.center = new Point2D(100,100);
            
        }
        
        // Punkte in der Klasse javafx.scene.shape.Polygon aktualisieren
        this.updatePoints();
        
        // Cursor über den Objekten anpassen
        this.setCursor(Cursor.HAND);
        
    }
    
    /**
     * Constructor, der den Namen und die Farbe des Objekts setzt
     * @param _name Zu setzender Name
     * @param _numberOfAngles Anzahl der Ecken des neuen Polygons
     * @param _center Mittelpunkt des neuen Objektes
     * @param _radius 'Radius' des neuen Objektes, speziell beim Kreis
     */
    protected GraphicObject(String _name, int _numberOfAngles, Point2D _center, double _radius) {
                
        // Super-Constructor erstellt ein Polygon (noch ohne Punkte)
        super();
        
        // Wenn Name übergeben wurde, diesen setzen, sonst default
        if (_name != null) this.name = _name;
        else {
            
            // Default: Durchnummerierte Objekte, Zähler ist Class-Variable
            this.name = "Object " + GraphicObject.numberOfObjects;
            GraphicObject.numberOfObjects++;
            
        }        
        
        // Wenn die gewollte Anzahl an Punkten groß genug ist
        if (_numberOfAngles > 1) {
            
            // Anzahl der Punkte setzen
            this.numberOfAngles = _numberOfAngles;
            
        }
        // sonst
        else {
            
            // mindestens einen Punkt erstellen
            this.numberOfAngles = 1;
            
        }
        
        // Wenn ein Mittelpunkt übergeben wurde, diesen setzen
        if (_center != null) this.center = _center;
        // sonst default
        else this.center = new Point2D(100, 100);
        
        // Ggf. unsinnigen Radius ersetzen
        if (_radius < 0) _radius = 50;
        
        // Neue Koordinatenarrays erstellen
        this.points = new Point2D[this.numberOfAngles];

        // Winkel, in dem die Punkte um den Mittelpunkt herum angeordnet sind
        double alpha;
        // Maximaler Winkel: 360°
        double maxAlpha = 360;
        // Nummer des aktuell zu bearbeitenden Punktes
        int actualPoint = 0;
        
        // Für alle Punkte
        while (actualPoint < this.numberOfAngles) {

            // Winkel aufaddieren (zum nächsten Punkt)
            alpha = (maxAlpha / this.numberOfAngles) * actualPoint;
            
            // Neuen Punkt erstellen
            // Vom Mittelpunkt des Objekts (100/100) ausgehen
            double actualX = this.center.getX();
            double actualY = this.center.getY();

            // Nächsten Punkt errechnen
            // Vektor (0, -radius) mit der Drehmatrix modifizieren und
            // zum Mittelpunkt dazu rechnen
            actualX += (_radius * Math.sin(Math.toRadians(alpha)));
            actualY += (-_radius * Math.cos(Math.toRadians(alpha)));

            // Neuen Punkt mit den errechneten Koordinaten erstellen
            points[actualPoint] = new Point2D(actualX, actualY);
            
            // Zum nächsten Punkt wechseln
            actualPoint++;
            
        }
        
        // Punkte in der Superklasse javafx.scene.shape.Polygon aktualisieren
        this.updatePoints();
        
        // Cursor über den Objekten zur Hand ändern
        this.setCursor(Cursor.HAND);
        
    }
        
    /**
     * Liefert den Namen des Objekts zurück.
     * @return Name des Objekts
     */
    public String getName() {
        
        // Namen zurückgeben
        return this.name;
        
    }
    
    /**
     * Setzt den Namen des Objekts neu.
     * @param n Zu setzender Name
     */
    public void setName(String n) {
        
        // Namen setzen, wenn er nicht null ist
        if (n != null) this.name = n;
        
    }
    
    /**
     * Liefert die Punkte des Objekts zurück.
     * @return Punkte des Objekts
     */
    public Point2D[] getPoints2D() {
                
        // Array mit den Punkten zurückgeben
        return points;
        
    }
    
    /**
     * Setzt die Punkte des Objekts neu.
     * @param newPoints Die neuen Punkte
     */
    public void setPoints2D(Point2D[] newPoints) {
        
        // Wenn überhaupt neue Punkte und die richtige Anzahl übergeben wurden
        if ((newPoints != null) && (newPoints.length == this.numberOfAngles)) {
            
            // Die neuen Punkte setzen
            this.points = newPoints;
            // Punkte des Polygons aktualisieren
            this.updatePoints();
            
        }
        
    }
    
    /**
     * Liefert den Mittelpunkt des Objekts zurück.
     * @return Mittelpunkt des Objekts
     */
    public Point2D getCenter() {
        
        // den Mittelpunkt zurückgeben
        return this.center;
        
    }
    
    /**
     * Setzt den Mittelpunkt des Objekts neu.
     * @param _center zu setzender Mittelpunkt
     */
    public void setCenter(Point2D _center) {
        
        // Wenn ein Punkt übergeben wurde, diesen setzen
        if (_center != null) this.center = _center;
        
    }
    
    /**
     * Gibt zurück, aus wie vielen Punkten das Objekt besteht.
     * @return Anzahl der Punkte des Objekts
     */
    public int getNumberOfPoints() {
        
        // Anzahl zurückgeben
        return numberOfAngles;        
        
    }
    
    /**
     * Aktualisiert die Punkte des Polygons in der Superklasse.
     * Diese ist javafx.scene.shape.Polygon.
     */
    public final void updatePoints() {
        
        // Temporäres Array zur Übergabe an die Superklasse
        Double[] temp = new Double[this.numberOfAngles * 2];

        // Temporäres Array bestücken
        for (int i = 0; i < this.numberOfAngles; i++) {

            temp[i * 2] = this.points[i].getX();
            temp[i * 2 + 1] = this.points[i].getY();

        }

        // Alte Punkte löschen und neue setzen
        this.getPoints().clear();
        this.getPoints().addAll(temp);
                           
    }
    
    /**
     * Transformiert das Objekt anhand der übergebenen Matrix.
     * @param m Transformationsmatrix
     */
    public void transform(Matrix m) {
        
        // Für alle Punkte des Polygons
        for (int i = 0; i < this.numberOfAngles; i++) {
            
            // Punkt mithilfe der Matrix modifizieren
            Transformate.transformatePointWithMatrix(m, points[i]);
                        
        }
        
        // Gleiches Verfahren für den Mittelpunkt
        Transformate.transformatePointWithMatrix(m, center);
        
        // Punkte in der Superklasse javafx.scene.shape.Polygon aktualisieren
        this.updatePoints();
        
    }
    
    /**
     * Verschiebt das Objekt so, dass die x-Koordinate des Mittelpunkts dem Wert
     * des Parameters entspricht.
     * @param x Neue x-Koordinate des Mittelpunktes
     */
    public void moveToX(double x) {
        
        // Translation in x-Richtung errechnen
        double translation = x - this.center.getX();
        // Matrix erstellen und transformieren
        Matrix matrix = Transformate.getTranslationMatrix(translation, 0);
        this.transform(matrix);
        
    }
    
    /**
     * Verschiebt das Objekt so, dass die y-Koordinate des Mittelpunkts dem Wert
     * des Parameters entspricht.
     * @param y Neue y-Koordinate des Mittelpunktes
     */
    public void moveToY(double y) {
        
        // Translation in y-Richtung errechnen
        double translation = y - this.center.getY();
        // Matrix erstellen und transformieren
        Matrix matrix = Transformate.getTranslationMatrix(0, translation);
        this.transform(matrix);
        
    }
    
    /**
     * Erstellt eine Kopie des angesprochenen Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    public abstract GraphicObject getCopyInstance();
    
    /**
     * Liefert eine Zusammenfassung des Objekts als String.
     * @return Zusammenfassung des Objekts als String
     */
    @Override
    public abstract String toString();
    
    /**
     * Setzt den Objektzähler zur Benennung von Objekten zurück.
     */
    public static void resetCounter() {
        
        numberOfObjects = 1;
        
    }
    
}