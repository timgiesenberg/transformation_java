/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoprojektjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class InfoProjektJAVAFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Informatikprojekt.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/ListView/ListView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        /*
        Matrix m = new Matrix(1);
        Matrix n = new Matrix(1);
        
        m.printMatrix();
        n.printMatrix();
        
        Matrix o  =  Transformate.muliplyMatrices(m, n);
        o.printMatrix();
        
        Transformate.getRotateMatrix(50);
        Transformate.getScaleMatrix(new Point2D(3,3));
        Transformate.getTranslateMatrix(new Point2D(5, 10));
        Transformate.mulitplyMatricesWithVector(m, new double[]{3,2,1});
        /**/
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
