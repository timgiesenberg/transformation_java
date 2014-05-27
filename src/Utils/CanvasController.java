/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import GraphicObjects.GraphicObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class CanvasController implements Initializable{

    @FXML
    private AnchorPane canvas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    //a static variable to hold the object reference
    protected static CanvasController cc;
    
    //constroctur allocates the current object
    //this is a workaround, no good practice
    public CanvasController(){
        cc = this;
    }
    
    public static CanvasController getController(){
        return cc;
    }
    
    public void add(GraphicObject o){
        canvas.getChildren().add(o);
    }
    
}
