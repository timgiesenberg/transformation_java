/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ListView;

import GraphicObjects.GraphicObject;
import GraphicObjects.ProjectLine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class ListController {
    
    private ObservableList<GraphicObject> items = FXCollections.observableArrayList();
    
    private ListView<GraphicObject> objectListView = null;
    
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
     * set the ListView
     * @param lv 
     */
    public void setListView(ListView lv){
        objectListView = lv;
        generateCellFactory();
        //Immediately set new cell Factory
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
     * @param id
     * @return 
     */
    public GraphicObject getItem(int id){
        return items.get(id);
    }
    
    public void setItems(ObservableList<GraphicObject> list){
        //this = list;
    }
    
    /**
     * insert a Graphic Object into the List
     * @param g
     */
    public void addItem(GraphicObject g){
        items.add(g);
        System.out.println(objectListView.getItems());
        System.out.println("addItem.");
    }
    
    /**
     * delete a graphicobject from List
     * @param g
     * @return 
     */
    public void deleteItem(GraphicObject g){
        items.remove(g);
    }
    
    /**
     * returns the reference of the GraphicObject that is selected in ListView
     * @return 
     */
    public GraphicObject getSelectedItem(){
        return objectListView.getSelectionModel().getSelectedItem();
    }
    
    /**
     * sets the selecteted Object in the ListView
     * object is then highlighted
     * @param g 
     */
    public void setFocus(GraphicObject g){
        objectListView.getSelectionModel().select(g);
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
