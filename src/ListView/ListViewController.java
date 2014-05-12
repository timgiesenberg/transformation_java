/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ListView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author tim.giesenberg@me.com
 */
public class ListViewController implements Initializable {

    @FXML
    private TextField heightInput;
    
    @FXML
    private TextField widthInput;
    
    @FXML
    private TextField radiusInput;
    
    @FXML
    private Label rectangleLabel;
    
    @FXML
    private Label circleLabel;
    
    @FXML
    private GridPane circleGrid;
    
    @FXML
    private GridPane rectangleGrid;
    
    @FXML
    private Circle blueCircle;
    
    @FXML
    private Rectangle blueRect;
    
    @FXML
    private Button testButton;
    
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
        circleGrid.setVisible(false);
        items = FXCollections.observableArrayList (
        blueCircle, blueRect);
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
    
    /**
     * handles a click on the static blue circle
     * @param event 
     */
    @FXML
    protected void circleClick(Event event){
        setInputFieldValues(blueCircle);
    }
    /**
     * handles a click on the static blue rectangle
     * @param event 
     */
    @FXML
    protected void rectClick(Event event){
        setInputFieldValues(blueRect);
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
            Circle c = ((Circle)s);
            radiusInput.setText(String.valueOf(c.getRadius()));
            circleLabel.setText(c.getId());
            rectangleGrid.setVisible(false);
            circleGrid.setVisible(true);
        } else if (s instanceof Rectangle) {
            Rectangle r = ((Rectangle)s);
            heightInput.setText(String.valueOf(r.getHeight()));
            widthInput.setText(String.valueOf(r.getWidth()));
            rectangleLabel.setText(r.getId());
            rectangleGrid.setVisible(true);
            circleGrid.setVisible(false);
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

