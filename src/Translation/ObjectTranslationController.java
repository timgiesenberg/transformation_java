/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Translation;

import infoprojektjavafx.Point2D;
import infoprojektjavafx.Transformate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author tim.giesenberg@me.com
 */
public class ObjectTranslationController implements Initializable {

    @FXML
    private Rectangle blueRect;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    protected void handleBackButton(ActionEvent event){        
        
        Point2D a = new Point2D(100,100);
        
        blueRect.setLayoutX(a.y);
        blueRect.setLayoutY(a.y);
        System.out.println(a.x + " " + a.y);
    }
    
    @FXML
    protected void handleOriginButton(ActionEvent event){
        
        Point2D a = Transformate.translateObjectToOrigin(new Point2D((int) blueRect.getX(), (int) blueRect.getY()));

        blueRect.setLayoutX(a.x);
        blueRect.setLayoutY(a.y);
        System.out.println(a.x + " " + a.y);
    }
    
}
