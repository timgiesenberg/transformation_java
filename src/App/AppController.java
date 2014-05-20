/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.ProjectCircle;
import GraphicObjects.ProjectLine;
import GraphicObjects.ProjectPolygon;
import GraphicObjects.ProjectRectangle;
import GraphicObjects.ProjectTriangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Callback;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class AppController implements Initializable {
    
    @FXML
    final private ListView<Shape> ObjectListView = new ListView<Shape>();
    
    private ObservableList<Shape> items;
    
    /**
     * Initializes the controller class.
     * runs on projectstart.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        items = FXCollections.observableArrayList ();
        ObjectListView.setItems(items);
        ObjectListView.setCellFactory(new Callback<ListView<Shape>, 
            ListCell<Shape>>() {
                @Override 
                public ListCell<Shape> call(ListView<Shape> list) {
                    return new ObjectCell();
                }
            }
        );
    }
    /**
     * handles a click on ViewList items
     * @param event 
     */
    @FXML
    protected void handleListViewClick(Event event){
        System.out.println("Click on list item. " );
        Shape s = ObjectListView.getSelectionModel().getSelectedItem();
        System.out.println(s.getId());
        setInputFieldValues(s);
    }
    
    @FXML
    protected void handleTestButton(Event event){
        Rectangle rect = new Rectangle(10, 10, 100, 20);
        rect.setFill(Color.web("blue"));
        rect.setId("Rext");
        updateListView(rect);
        deleteItem(rect);
    }
    
    /**
     * updates List View
     * @param s 
     */
    private void updateListView(Shape s){
        items.add(s);
        ObjectListView.setItems(items);
        System.out.println(s.getId());
    }
    
    private void deleteItem(Shape s){
        String t = s.getId();
        if(items.remove(s)){
           System.out.println("Successfully removed Shape " + t); 
        } else {
            System.out.println("Could not delete shape" + t); 
        }
    }
    
    /**
     * sets Values of Input Fields
     * @param s 
     */
    protected void setInputFieldValues(Shape s){
        if (s instanceof Circle) {
        } else if (s instanceof Rectangle) {
        }
    }
    
     @FXML
    private AnchorPane canvas;
    
    @FXML
    private TextField polygonNumberOfAngles;
    
    @FXML
    private void handleButtonActionRectangle(ActionEvent event) {
        
        final ProjectRectangle r = new ProjectRectangle("Horst", Color.GREEN, 100, 100, 100, 100);
        r.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Das Rechteck " + r.getName() + " wurde ausgewählt.");
                
            }
            
        });
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
                canvas.getChildren().add(p);
                
            }
                
        }
        
    }
    
    static class ObjectCell extends ListCell<Shape> {
        @Override
        public void updateItem(Shape item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                setText(item.getId());
            } else {
                //rect.setFill(Color.web("blue"));
                //setGraphic(rect);
            }
        }
    }  
    
}
