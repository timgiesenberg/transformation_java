/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Translation;

import infoprojektjavafx.Point2D;
import infoprojektjavafx.Translate;
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
        
        Point2D a = Translate.translateToOrigin(new Point2D(0,0), new Point2D(0,0));
        
        blueRect.setX(a.x);
        blueRect.setY(a.y);
    }
    
    @FXML
    protected void handleOriginButton(ActionEvent event){
        
        Point2D a = Translate.translateToOrigin(new Point2D(0,0), new Point2D(0,0));
        
        blueRect.setX(a.x);
        blueRect.setY(a.y);
    }
    
}
