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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private ListView<String> ObjectListView = new ListView<String>();
    
    private ObservableList<String> items = FXCollections.observableArrayList (
        "1", "2", "3", "4", "5");
    
    @FXML
    protected void handleCreateButton(ActionEvent event){        
        ObjectListView.setItems(items);
    }
    
    @FXML
    protected void handleListViewClick(Event event){
        System.out.println("Click on list item. " + ObjectListView.getSelectionModel().getSelectedItems());
        value1.setText(ObjectListView.getSelectionModel().getSelectedItems().toString());
        value3.setText(ObjectListView.getSelectionModel().getSelectedItems().toString());
    }
    
}
