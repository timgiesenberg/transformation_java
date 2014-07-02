package IO;

import GraphicObjects.Circle;
import GraphicObjects.GraphicObject;
import GraphicObjects.Line;
import GraphicObjects.Polygon;
import GraphicObjects.Rectangle;
import GraphicObjects.Triangle;
import Utils.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Ein Objekt der Klasse GraphicObjectReader liest aus einer passenden Textdatei
 * GraphicObjects aus.
 * @author Das TransPlosion-Team
 */
public class GraphicObjectReader {
    
    // IV: Nächste Station im Input-Stream
    private final BufferedReader in;
    
    /**
     * Der Konstruktor erstellt einen neuen GraphicObjectReader.
     * @param filename Dateipfad, aus dem gelesen werden soll
     * @throws FileNotFoundException Fehler, wenn Datei nicht vorhanden
     */
    public GraphicObjectReader(String filename) throws FileNotFoundException {
        
        // Input-Stream anlegen
        this.in = new BufferedReader(new FileReader(filename));
        
    }
    
    /**
     * Liest ein GraphicObject aus dem Input-Stream aus und
     * gibt es zurück.
     * @return Das nächste GraphicObject
     * @throws IOException Fehler beim Lesen, meistens: txt-File nicht im
     * richtigen Format
     */
    public GraphicObject read() throws IOException {
        
        // Zeile auslesen
        String objectText = this.in.readLine();
        // Wenn File zu Ende, null zurückgeben
        if (objectText == null) return null;
        
        
        // ----------- Teil 1: Auslesen des GraphicObject-Subtyps -----------
        String graphicObjectType;
        if (objectText.startsWith("Circle")) graphicObjectType = "Circle";
        else if (objectText.startsWith("Line")) graphicObjectType = "Line";
        else if (objectText.startsWith("Polygon")) graphicObjectType = "Polygon";
        else if (objectText.startsWith("Rectangle")) graphicObjectType = "Rectangle";
        else if (objectText.startsWith("Triangle")) graphicObjectType = "Triangle";
        else throw new IOException();
        
        // String kürzen
        int cut = objectText.indexOf("[points=[");
        if (cut == -1) throw new IOException();
        else cut += 9;
        objectText = objectText.substring(cut);
        
        
        // ----------- Teil 2: Punkte auslesen -----------
        String pointString;
        cut = objectText.indexOf("], center=[");
        if (cut == -1) throw new IOException();
        else {
            // Punktetext in einem eigenen String speichern
            pointString = objectText.substring(0, cut);
            System.out.println(pointString);
            cut += 11;
        }
        // String kürzen
        objectText = objectText.substring(cut);
        
        // Variablen anlegen: Punkte-Liste, Array für den aktuellen Punkt,
        // Index für dieses Array, leerer String zum Speichern der Koordinaten
        ArrayList<Point2D> points = new ArrayList<>();
        double[] newPoint = new double[2];
        int newPointIndex = 0;
        String pointValue = "";
        
        // den gesamten Punkte-String abarbeiten
        for (int i = 0; i < pointString.length(); i++) {
            
            // Nächstes Zeichen auslesen
            char c = pointString.charAt(i);
            
            // Wenn Zeichen noch zur Zahl gehört, an Koordinatenstring anhängen
            if ((c >= '0' && c <= '9') || c == '.' || c == '-') pointValue += c;
            // Wenn Koordinaten vorbei (,), diese verarbeiten
            else if (c == ',') {
                
                // Koordinate ins Array des aktuellen Punktes schreiben
                try {
                    newPoint[newPointIndex] = Double.parseDouble(pointValue);
                } catch (NumberFormatException nfe) {
                    throw new IOException();
                }
                // Koordinatenstring zurücksetzen
                pointValue = "";
                // Index wechseln
                if (newPointIndex == 0) newPointIndex = 1;
                else if (newPointIndex == 1) {
                    
                    newPointIndex = 0;
                    
                    // Wenn aktueller Punkt vollständig ist, diesen abspeichern
                    points.add(new Point2D(newPoint[0], newPoint[1]));
                    // Array zurücksetzen
                    newPoint = new double[2];
                    
                }
                else throw new IOException();
                
            }
            // Leerzeichen ignorieren
            else if (c == ' ') {}
            else throw new IOException();
            
        }
        
        // Auch letzten Punkt noch abspeichern
        if (newPointIndex == 1) {
            try {
                newPoint[newPointIndex] = Double.parseDouble(pointValue);
            } catch (NumberFormatException nfe) {
                throw new IOException();
            }
            points.add(new Point2D(newPoint[0], newPoint[1]));
        }
        else throw new IOException();
        
        // Punkte in ein Point2D-Array kopieren
        Point2D[] pointArray = new Point2D[points.size()];
        points.toArray(pointArray);
        
        
        // ----------- Teil 3: Mittelpunkt auslesen -----------
        String centerText;
        
        // Mittelpunkttext in eigenem String speichern
        cut = objectText.indexOf("], fill=");
        if (cut == -1) throw new IOException();
        else {
            centerText = objectText.substring(0, cut);
            System.out.println(centerText);
            cut += 8;
        }
        // String kürzen
        objectText = objectText.substring(cut);
        
        // Variablen zum Speichern des Mittelpunkts: wie oben
        newPoint = new double[2];
        newPointIndex = 0;
        pointValue = "";
        
        // den gesamten Mittelpunkttext abarbeiten
        for (int i = 0; i < centerText.length(); i++) {
            
            // Zeichen auslesen
            char c = centerText.charAt(i);
            
            // Zeichen verarbeiten
            if ((c >= '0' && c <= '9') || c == '.' || c == '-') pointValue += c;
            // Wenn erste Zahl vorbei, Zahl ins Array schreiben, Index wechseln
            else if (c == ',') {
                
                try {
                    newPoint[newPointIndex] = Double.parseDouble(pointValue);
                } catch (NumberFormatException nfe) {
                    throw new IOException();
                }
                pointValue = "";
                if (newPointIndex == 0) newPointIndex++;
                else throw new IOException();
                
            }
            // Leerzeichen ignorieren
            else if (c == ' ') {}
            else throw new IOException();
            
        }
        
        // Mittelpunkt als Point2D abspeichern
        try {
            newPoint[newPointIndex] = Double.parseDouble(pointValue);
        } catch (NumberFormatException nfe) {
            throw new IOException();
        }
        Point2D center = new Point2D(newPoint[0], newPoint[1]);
        
        
        // ----------- Teil 4: Farbe auslesen -----------
        String colorText;
        
        // Farbentext in eigenem String abspeichern
        cut = objectText.indexOf(", name=");
        if (cut == -1) throw new IOException();
        else {
            colorText = objectText.substring(0, cut);
            cut += 7;
        }
        // String kürzen
        objectText = objectText.substring(cut);
        
        // Farbe ermitteln lassen
        Color c = Color.valueOf(colorText);
        
        
        // ----------- Teil 5: Namen auslesen -----------
        String name;
        
        // Namen in einem eigenen String abspeichern
        cut = objectText.indexOf("]");
        if (cut == -1) throw new IOException();
        else name = objectText.substring(0, cut);
        
        
        // ----------- Abschließend: Objekt erstellen -----------
        // Passendes Objekt mit Name und Farbe (und ggf. Punkteanzahl) erstellen
        GraphicObject g;
        if (graphicObjectType.equalsIgnoreCase("Circle")) g = new Circle(name, c, 0, 0, 0);
        else if (graphicObjectType.equalsIgnoreCase("Line")) g = new Line(name, c, 0, 0, 0, 0);
        else if (graphicObjectType.equalsIgnoreCase("Polygon")) g = new Polygon(name, c, pointArray.length, 0, 0);
        else if (graphicObjectType.equalsIgnoreCase("Rectangle")) g = new Rectangle(name, c, 0, 0, 0, 0);
        else if (graphicObjectType.equalsIgnoreCase("Triangle")) g = new Triangle(name, c, 0, 0, 0);
        else throw new IOException();
        // Punkte und Mittelpunkt setzen
        g.setCenter(center);
        g.setPoints2D(pointArray);
        // Objekt zurückgeben
        return g;
        
    }
    
}
