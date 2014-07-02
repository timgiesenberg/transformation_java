package ListView;

import GraphicObjects.GraphicObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Der ListController führt alle Objekte in einer Liste, stellt sie im ListView dar
 * und stellt funktionen zum einfügen, löschen, ausgabe und Update der Liste
 * @see 
 * @author Das TransPlosion-Team
 * @version 1.0
 */
public class ListController {
    
    private ObservableList<GraphicObject> items = FXCollections.observableArrayList();
    
    private ListView<GraphicObject> objectListView = null;
    
    /**
     * Umsetzung Singleton pattern, statische Eigenschafft
     */
    private static ListController instance = null;
    
    /**
     * privater Konstruktor, wird nur aufgerufen wenn die instance = null
     */
    private ListController(ListView lv){
        
        objectListView = lv;
        
        objectListView.setCellFactory(new Callback<ListView<GraphicObject>, 
            ListCell<GraphicObject>>() {
                
                @Override
                public ListCell call(ListView<GraphicObject> o) {
                    
                    System.out.println("Return new Graphiccell");
                    return new GraphicCell();
                    
                }
                
            }
        );
        
        objectListView.setItems(items);
        System.out.println("Constructor call was successful");
        
    }
    
    /**
     * Gibt eine Instanz dieser Klasse wieder, erzeugt eine Instanz sofern keine
     * erzeugt wurde.
     * @param lv ListView der Oberfläche
     * @return Instanz der Klasse ListController
     */
    public static ListController getInstance(ListView lv) {
        
        if(instance == null) {
            
            instance = new ListController(lv);
            System.out.println("ListController Object created");
            
        } else {
            
            System.out.println("ListController already instantiated");
            
        }
        return instance;
        
    }
    
    /**
     * Gibt die aktuelle Liste zurück
     * @return Liste als ObservableList
     */
    public ObservableList<GraphicObject> getItems(){
        
        System.out.print("ListGetItems");
        return items;
        
    }
    
    /**
     * Gibt ein Grafikobjekt bestimmtes Grafikobjekt zurück
     * @param id ID des gewünschten Objekts
     * @return Das gewählte Grafikobjekt
     */
    public GraphicObject getItem(int id){
        
        return items.get(id);
        
    }
    
    /**
     * Fügt ein Grafikobjekt in die Liste ein.
     * @param g Das neue hinzuzufügende Grafikobjekt
     */
    public void addItem(GraphicObject g){
        
        items.add(g);
        System.out.println(objectListView.getItems());
        System.out.println("addItem.");
        
    }
    
    /**
     * Löscht das gewählte Grafikobjekt aus der Liste.
     * @param g Grafikobjekt das gelöscht werden soll.
     */
    public void deleteItem(GraphicObject g){
        
        items.remove(g);
        
    }
    
    /*
    * Löscht die gesamte Liste
    */
    public void deleteAllItems() {
        
        items.clear();
        
    }
    
    /**
     * Gibt die Referenz des im ListView gewählten Objekts zurück.
     * @return ausgewähltes Grafikobjekt 
     */
    public GraphicObject getSelectedItem(){
        
        GraphicObject selected = objectListView.getSelectionModel().getSelectedItem();
        return selected;
        
    }
    
    /**
     * Hebt die Auswahl im ListView wieder auf.
     */
    public void clearSelection(){
        
        objectListView.getSelectionModel().clearSelection();
        
    }
    
    /**
     * setzt den Fokus des ListViews auf das übergebene Objekt
     * @param g referenz des ausgewählten Grafikobjekts
     */
    public void setFocus(GraphicObject g){
        
        objectListView.getSelectionModel().select(g);
        
    }
    
    /**
     * Lädt den ListView sofort wieder neu.
     */
    public void update(){
        
        objectListView.setItems(null);
        objectListView.setItems(items);
        
    }
    
    /**
     * sortiert die Grafikobjekte in der Liste neu. Ausgewähltes Objekt wird an
     * erster Stelle gesetzt, alle anderen werden um eine Position nach hinten
     * geschoben.
     * @deprecated 
     * @param g 
     */
    public void sort(GraphicObject g){
        
        if(items.indexOf(g) != 0){
            
            if(items.contains(g)){
                
                items.remove(g);
                
            }
            //insert first element in list
            items.add(0, g);
            
        }
        objectListView.setItems(items);
        
    }
    
    /**
     * Setzt eine selbst erzeugte Zelle.
     */
    private void generateCellFactory(){
        
        objectListView.setCellFactory(new Callback<ListView<GraphicObject>, 
            ListCell<GraphicObject>>() {
                
                @Override
                public ListCell call(ListView<GraphicObject> p) {
                    
                    return new GraphicCell();
                    
                }
                
            }
        );
        
    }
    
}
