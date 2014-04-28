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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
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
    private TextField value1;
    
    @FXML
    private TextField value2;
    
    @FXML
    private Label value3;
    
    @FXML
    private Circle blueCircle;
    
    @FXML
    private Rectangle blueRect;
    
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
    
    @FXML
    protected void handleCreateButton(ActionEvent event){
        /**ObjectListView.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>(){
 
            @Override
            public ListCell<Shape> call(ListView<Shape> p) {
                 
                ListCell<Shape> cell = new ListCell<Shape>(){
 
                    @Override
                    protected void updateItem(Shape t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getId());
                        }
                    }
 
                };
                 
                return cell;
            }
        });/**/
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
        //value1.setText(String.valueOf());
        //value2.setText(String.valueOf(s.getHeight()));
        value3.setText(s.getId());
    }
    
    /**
     * handles a click on the static blue circle
     * @param event 
     */
    @FXML
    protected void circleClick(Event event){
        value1.setText(String.valueOf(blueCircle.getRadius()));
        value2.setText("");
        value3.setText(blueCircle.getId());
    }
    /**
     * handles a click on the static blue rectangle
     * @param event 
     */
    @FXML
    protected void rectClick(Event event){
        value1.setText(String.valueOf(blueRect.getWidth()));
        value2.setText(String.valueOf(blueRect.getHeight()));
        value3.setText(blueRect.getId());
    }
    
    /**
     * updates Object information with given Object
     * fields like height, width are set then
     * @param s 
     */
    private void updateUI(Shape s){
        
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

