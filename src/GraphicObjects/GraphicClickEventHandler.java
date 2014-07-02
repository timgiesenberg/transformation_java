package GraphicObjects;

import App.AppController;
import ListView.ListController;
import javafx.animation.StrokeTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Die Klasse GraphicClickEventHandler repräsentiert einen EventHandler, der auf
 * einen Klick auf ein GraphicObject reagiert.
 * @author Das TransPlosion-Team
 */
public class GraphicClickEventHandler implements javafx.event.EventHandler<MouseEvent>{

    final private GraphicObject graphicObject;
    final private ListController listController;
    final private AppController appController;
    
    /**
     * Constructor, der einen neuen EventHandler erstellt.
     * @param o Reagierendes GraphicObject
     * @param lc ListController, in dem das Objekt steht
     * @param ac AppController, der die Programmlogik koordiniert
     */
    public GraphicClickEventHandler(GraphicObject o, ListController lc, AppController ac){
        
        graphicObject = o;
        listController = lc;
        appController = ac;
        
    }
    
    /**
     * Reagiert auf einen Klick auf das GraphicObject, dieses wird ausgewählt.
     * Das Programmfenster wird dementsprechend aktualisiert.
     * @param t MouseEvent des Klicks
     */
    @Override
    public void handle(MouseEvent t) {
        
        System.out.println("Das Objekt: " + graphicObject.getName() + " wurde ausgewählt.");
        
        // Konturrand beim letzten ausgewählten Objekt entfernen
        GraphicObject selectedItem = this.listController.getSelectedItem();
        if (selectedItem != null) {
            
            if (selectedItem instanceof Line) selectedItem.setStroke(selectedItem.getFill());
            else selectedItem.setStroke(Color.TRANSPARENT);
            
        }
        StrokeTransition oldST = appController.getStrokeTransition();
        if (oldST != null) oldST.stop();
        
        
        // Konturrand zu diesem Objekt hinzufügen
        this.graphicObject.setStrokeWidth(3);
        Color fill = (Color) this.graphicObject.getFill();
        Color strokeColor = Color.hsb(fill.getHue() + 180, 1, 1);
        Color antiStrokeColor = Color.hsb(fill.getHue() + 90, 0.5, 0.5);
        StrokeTransition aktuelleST = new StrokeTransition(Duration.millis(500), this.graphicObject, strokeColor, antiStrokeColor);
        aktuelleST.setCycleCount(StrokeTransition.INDEFINITE);
        aktuelleST.setAutoReverse(true);
        aktuelleST.play();
        appController.setStrokeTransition(aktuelleST);
        
        // Das angeklickte GraphicObject in der Liste auswählen
        listController.setFocus(graphicObject);
        // Das angeklickte GrahpicObject in den Vordergrund bringen
        graphicObject.toFront();
        // Die Eigenschaftenleiste aktualisieren
        appController.setInputFieldValues(graphicObject);
        // Angeben, dass ein Objekt angeklickt wurde, damit nicht durch den
        // Canvas-Click die Auswahl wieder aufgehoben wird
        appController.setObjectIsClicked(true);
        
    }
    
}
