package Utils;

/**
 * Die Klasse Point2D repräsentiert einen 2D-Punkt mit x- und y-Koordinate zur
 * Verwendung für die Grafikobjekte und die 2D-Transformationen des Programms.
 * @author Das TransPlosion-Team
 */
public class Point2D {
    
    private double x, y;
    
    /**
     * Der Konstruktor erstellt einen neuen Punkt bei x = 0 und y = 0.
     */
    public Point2D() {
        
        this.x = 0;
        this.y = 0;
        
    }
    
    /**
     * Der Konstruktor erstellt einen neuen Punkt bei den übergebenen Koordinaten.
     * @param newX x-Koordinate des neuen Punktes
     * @param newY y-Koordinate des neuen Punktes
     */
    public Point2D(double newX, double newY) {
        
        this.x = newX;
        this.y = newY;
        
    }
    
    /**
     * Setzt die x-Koordinate des Punktes neu.
     * @param newX Neue x-Koordinate des Punktes
     */
    public void setX (double newX) {
        
        this.x = newX;
        
    }
    
    /**
     * Liefert die x-Koordinate des Punktes zurück.
     * @return x-Koordinate des Punktes
     */
    public double getX() {
        
        return this.x;
        
    }
    
    /**
     * Setzt die y-Koordinate des Punktes neu.
     * @param newY Neue y-Koordinate des Punktes
     */
    public void setY (double newY) {
        
        this.y = newY;
        
    }
    
    /**
     * Liefert die y-Koordinate des Punktes zurück.
     * @return y-Koordinate des Punktes
     */
    public double getY() {
        
        return this.y;
        
    }
    
}
