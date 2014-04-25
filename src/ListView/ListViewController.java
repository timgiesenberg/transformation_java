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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
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
    
    private ObservableList<Shape> items = FXCollections.observableArrayList (
        blueCircle, blueRect);
    
    @FXML
    protected void handleCreateButton(ActionEvent event){        
        ObjectListView.setItems(items);
        ObjectListView.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>(){
 
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
        });
    }
    
    @FXML
    protected void handleListViewClick(Event event){
        System.out.println("Click on list item. " );
        value1.setText(String.valueOf(ObjectListView.getItems().get(0).getId()));
        value3.setText(String.valueOf(ObjectListView.getItems().get(0).getId()));
    }
    
    @FXML
    protected void circleClick(Event event){
        value1.setText(String.valueOf(blueCircle.getRadius()));
        value2.setText("");
        value3.setText(blueCircle.getId());
    }
    @FXML
    protected void rectClick(Event event){
        value1.setText(String.valueOf(blueRect.getWidth()));
        value2.setText(String.valueOf(blueRect.getHeight()));
        value3.setText(blueRect.getId());
    }
}
