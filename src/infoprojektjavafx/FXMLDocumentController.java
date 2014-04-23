/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoprojektjavafx;

import com.sun.org.apache.xerces.internal.impl.xs.util.ObjectListImpl;
import java.net.URL;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private ListView<Integer> ObjectListView = new ListView<Integer>();
    
    //private ObservableList<Integer> items = FXCollections.observableArrayList (
    //counter);
    
    private int counter = 0;
    
    @FXML
    protected void handleCopyButton(ActionEvent event){
        ObservableList<Integer> items = FXCollections.observableArrayList (
        counter);
        ObjectListView.setItems(items);
        counter++;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
