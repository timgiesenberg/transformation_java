// Package angeben
package GraphicObjects;

import Utils.Matrix;
import Utils.Point2D;
import Utils.Transformate;
import javafx.scene.Cursor;
/*
 * import infoprojektjavApprt infoprojektjavafx.TransformaApplasse GraphicObject fasst alle zur Verfügung stehenden Grafiken zusammen.
 * Alle werden als Polygone implementiert.
 * @author Phil Köster // Dominique Berners
 */
public abstract class GraphicObject extends javafx.scene.shape.Polygon {
    
    // Instanzvariablen: Name und Farbe und Punkte des Objekts
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
        
        this.updatePoints();
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
        else this.center = new Point2D(100,100);
        
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
        
        // Punkte aktualisieren
        this.updatePoints();
        this.setCursor(Cursor.HAND);
    }
        
    /**
     * Die Methode getName liefert den Namen des Objekts zurück.
     * @return Name des Objekts
     */
    public String getName() {
        
        // Namen zurückgeben
        return this.name;
        
    }
    
    /**
     * Die Methode setName setzt den Namen des Objekts neu.
     * @param n Zu setzender Name
     */
    public void setName(String n) {
        
        // Namen setzen, wenn er nicht null ist
        if (n != null) this.name = n;
        
    }
    
    /**
     * Die Methode getPoints2D() liefert die Koordinaten des Objekts
     * zurück.
     * @return Koordinaten des Objekts
     */
    public Point2D[] getPoints2D() {
                
        // Array mit den Koordinaten zurückgeben
        return points;
        
    }
    
    /**
     * Die Methode setPoints2D() setzt die Koordinaten des Objekts neu.
     * @param newPoints Die neuen Koordinaten
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
     * Die Methode getCenter liefert den Mittelpunkt des Objekts zurück.
     * @return Mittelpunkt des Objekts
     */
    public Point2D getCenter() {
        
        // den Mittelpunkt zurückgeben
        return this.center;
        
    }
    
    /**
     * Die Methode setCenter setzt den Mittelpunkt des Objekts neu.
     * @param _center zu setzender Mittelpunkt
     */
    public void setCenter(Point2D _center) {
        
        // Wenn ein Punkt übergeben wurde, diesen setzen
        if (_center != null) this.center = _center;
        
    }
    
    /**
     * Die Methode getNumberOfPoints gibt zurück, aus wie vielen Punkten das
     * Objekt besteht.
     * @return Anzahl der Punkte des Objekts
     */
    public int getNumberOfPoints() {
        
        // Anzahl zurückgeben
        return numberOfAngles;        
        
    }
    
    /**
     * Die Methode updatePoints() aktualisiert die Punkte des Polygons.
     */
    public void updatePoints() {
        
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
     * Die Methode transform() transformiert das Objekt anhand der übergebenen
     * Matrix.
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
     * Die Methode getCopyInstance() erstellt eine Kopie des angesprochenen
     * Objekts und liefert es zurück.
     * @return Kopie des angesprochenen Objekts
     */
    public abstract GraphicObject getCopyInstance();
    
    /**
     * Subklassenobjekte müssen zur Speicherung die toString-Methode 
     * redefinieren.
     * @return Zusammenfassung des GraphicObjects, optimiert zur Speicherung
     */
    @Override
    public abstract String toString();
    
    /**
     * Die Methode resetCounter() setzt den Objektzähler zur Benennung von
     * Objekten zurück.
     */
    public static void resetCounter() {
        
        numberOfObjects = 1;
        
    }
    
}