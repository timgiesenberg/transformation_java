/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TransformationBar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class TransformationBarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void transform(Event event){
        System.out.println("transform Button clicked");
    }
    
}
