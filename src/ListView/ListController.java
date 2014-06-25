/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ListView;

import GraphicObjects.GraphicObject;
import GraphicObjects.Line;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javax.swing.event.ChangeListener;

/**
 * ListController handles all interaction with the ListView in the application.
 * @author tim.giesenberg@me.com
 */
public class ListController {
    
    private ObservableList<GraphicObject> items = FXCollections.observableArrayList();
    
    private ListView<GraphicObject> objectListView = null;
    
    /**
     * keeps instance of own class for use of singleton pattern
     */
    private static ListController instance = null;
    
    /**
     * private Constructor to avoid instantiation
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
     * returns an instance of this class, deciding wether an instance has to be
     * created or not.
     * @param lv - ListView to generate a Listcontroller
     * @return 
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
     * return all Elements in List in a String array
     * @return 
     */
    public ObservableList<GraphicObject> getItems(){
        System.out.print("ListGetItems");
        return null;//this;
    }
    
    /**
     * returns a Graphicobject by its Id
     * @param id ID of desired graphic object
     * @return 
     */
    public GraphicObject getItem(int id){
        
        return items.get(id);
    }
    
    /**
     * insert a Graphic Object into the List
     * @param g reference of the graphic object that will be inserted into list
     */
    public void addItem(GraphicObject g){
        items.add(g);
        System.out.println(objectListView.getItems());
        System.out.println("addItem.");
    }
    
    /**
     * delete a graphic object from List
     * @param g graphic object that will be deleted
     * @return 
     */
    public void deleteItem(GraphicObject g){
        items.remove(g);
    }
    
    public void deleteAllItems() {
        items.clear();
    }
    
    /**
     * returns the reference of the graphic object that is selected in ListView
     * @return 
     */
    public GraphicObject getSelectedItem(){
        GraphicObject selected = objectListView.getSelectionModel().getSelectedItem();
        sort(selected);
        return selected;
    }
    
    /**
     * sets the selecteted Object in the ListView
     * object is then highlighted
     * @param g refernce of selected graphic object
     */
    public void setFocus(GraphicObject g){
        sort(g);
        objectListView.getSelectionModel().select(g);
    }
    
    /**
     * 
     * reorder the list and place selected item in first place
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
    }
    
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
