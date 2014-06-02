/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ListView;

import GraphicObjects.GraphicObject;
import GraphicObjects.ProjectLine;
import ListView.ListViewController.ObjectCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 *
 * @author tim.giesenberg@me.com
 * @param <GraphicObject>
 */
public class ListController<GraphicObject> {
    
    private ObservableList<GraphicObject> items = FXCollections.observableArrayList();
    
    private ListView<GraphicObject> objectListView = new ListView<>();
    
    private static ListController instance = null;
    
    private ProjectLine line = new ProjectLine("test", Color.RED, 0, 0, 10, 10);
    
    /**
     * private Constructor to avoid instantiation
     */
    private ListController(){
        //items = FXCollections.observableArrayList(line.);
        //objectListView.setItems(FXCollections.observableArrayList((GraphicObject) line));
        //generateCellFactory();
        objectListView.setCellFactory(new Callback<ListView<GraphicObject>, 
            ListCell<GraphicObject>>() {
                
                @Override
                public ListCell call(ListView<GraphicObject> o) {
                    System.out.println("Return new Graphiccell");
                    return new GraphicCell();
                }
            }
        );
        System.out.println("Constructor call was successful");
    }
    
    public static ListController getInstance() {
      if(instance == null) {
         instance = new ListController();
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
    
    public GraphicObject getItem(int id){
        return items.get(id);
    }
    
    public void setItems(ObservableList<GraphicObject> list){
        //this = list;
    }
    
    /**
     * insert a Graphic Object into the List
     * @param g
     * @return 
     */
    public void addItem(GraphicObject g){
        items.add(g);
        objectListView.setItems(items);
        System.out.println(objectListView.getItems());
        System.out.println("addItem.");
    }
    
    /**
     * delete a graphicobject from List
     * @param g
     * @return 
     */
    public boolean deleteItem(GraphicObject g){
        //this.remove(g);
        return false;
    }
    
    public GraphicObject getSelectedItem(){
        return objectListView.getSelectionModel().getSelectedItem();
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
