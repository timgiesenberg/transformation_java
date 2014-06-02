/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.GraphicObject;
import GraphicObjects.ProjectCircle;
import GraphicObjects.ProjectLine;
import GraphicObjects.ProjectPolygon;
import GraphicObjects.ProjectRectangle;
import GraphicObjects.ProjectTriangle;
import ListView.ListController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class AppController implements Initializable {
    
    @FXML
    final private ListView<GraphicObject> ObjectListView = new ListView<>();
    
    @FXML
    private Pane canvas;
    
    @FXML
    private TextField polygonNumberOfAngles;
    
    private ListController list;
    
    /**
     * Initializes the controller class.
     * runs on projectstart.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        //create new ListController
        list = ListController.getInstance();
        
        //TODO: transfer ObjectListview Reference to the list Controllter
        list.setListView(ObjectListView);
        
        //if needed, add some previously generated Objects
        //list.setItems(FXCollections.observableArrayList(line));
    }
    /**
     * handles a click on ViewList items
     * @param event 
     */
    @FXML
    protected void handleListViewClick(Event event){
        System.out.println("Click on list item. " );
        //GraphicObject s = list.getSelectedItem();
        //System.out.println(s.getId());
        //setInputFieldValues(s);
    }
    
    /**
     * sets Values of Input Fields
     * @param s 
     */
    protected void setInputFieldValues(GraphicObject s){
        if (s instanceof ProjectCircle) {
        } else if (s instanceof ProjectRectangle) {
        }
    }
    
    @FXML
    private void handleButtonActionRectangle(ActionEvent event) {
        
        final ProjectRectangle r = new ProjectRectangle("Horst", Color.GREEN, 100, 100, 100, 100);
        r.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Das Rechteck " + r.getName() + " wurde ausgewählt.");
                
            }
            
        });
        list.addItem(r);
        canvas.getChildren().add(r);
    }
    
    @FXML
    private void handleButtonActionCircle(ActionEvent event) {
        
        final ProjectCircle c = new ProjectCircle("Angelika", Color.BLUE, 100, 100, 40);
        c.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Der Kreis " + c.getName() + " wurde ausgewählt.");
                
            }
            
        });
        list.addItem(c);
        canvas.getChildren().add(c);
        
    }
    
    @FXML
    private void handleButtonActionTriangle(ActionEvent event) {
        
        final ProjectTriangle t = new ProjectTriangle("Wilhelmine", Color.YELLOW, 60, 60, 80);
        t.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Das Dreieck " + t.getName() + " wurde ausgewählt.");
                
            }
            
        });
        list.addItem(t);
        canvas.getChildren().add(t);
        
    }
    
    @FXML
    private void handleButtonActionLine(ActionEvent event) {
        
        final ProjectLine l = new ProjectLine("Gottfried", Color.RED, 100, 50, 150, 100);
        l.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Die Linie " + l.getName() + " wurde ausgewählt.");
                
            }
            
        });
        list.addItem(l);
        canvas.getChildren().add(l);
        
    }
    
    @FXML
    private void handleButtonActionPolygon(ActionEvent event) {
        
        String s = polygonNumberOfAngles.getCharacters().toString();
        if (s.length() > 0 && s.matches("[0-9]+")) {
            
            int numberOfAngles = Integer.parseInt(s);
            if (numberOfAngles > 0) {
                
                final ProjectPolygon p = new ProjectPolygon("Sybille", Color.GRAY, numberOfAngles, 200, 200);
                p.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        System.out.println("Das Polygon " + p.getName() + " wurde ausgewählt.");

                    }

                });
                list.addItem(p);
                canvas.getChildren().add(p);
                
            }
                
        }
        
    }
    /*
    static class ObjectCell extends ListCell<GraphicObject> {
        @Override
        public void updateItem(GraphicObject item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getId());
            } else {
            }
        }
    }  //**/
    
}
