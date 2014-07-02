package IO;

import GraphicObjects.GraphicObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;

/**
 * Ein Objekt der Klasse GraphicObjectWriter speichert die GraphicObjects in
 * einer Datei.
 * @author Das TransPlosion-Team
 */
public class GraphicObjectWriter {
    
    // IV: Nächste Station im Outputstream
    private final PrintWriter out;
    
    /**
     * Der Konstruktor erstellt einen neuen GraphicObjectWriter.
     * @param filename Dateipfad der zu schreibenden Datei
     * @throws IOException Fehler beim Erzeugen des Output-Streams
     */
    public GraphicObjectWriter(String filename) throws IOException {
        
        // Output-Stream anlegen
        this.out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        
    }
    
    /**
     * Schreibt eine Liste von GraphicObjects in die Datei.
     * @param items Liste der GraphicObjects, die gespeichert werden sollen
     */
    public void write(ObservableList<GraphicObject> items) {
        
        // für alle Objekte
        for (GraphicObject g : items) {
            
            // in eine Zeile ein Objekt (toString) schreiben
            this.out.println(g);
            
        }
        
        // Output-Stream schließen
        this.out.close();
        
    }
    
}
