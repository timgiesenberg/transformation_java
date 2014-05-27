/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Properties;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class PropertiesController implements Initializable{
    
    @FXML
    private TextField CircleName;
    
    @FXML
    private void onText(ActionEvent event) {
        
        System.out.println("Test");
        CircleName.setText("test");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
