/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class InfoProjektJAVAFX extends Application {
    
    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
         // FXML-Dateien importieren
        Parent canvas = FXMLLoader.load(getClass().getResource("/App/Parent.fxml"));
        /* Parent canvas = FXMLLoader.load(getClass().getResource("/Utils/Canvas.fxml"));
        Parent buttons = FXMLLoader.load(getClass().getResource("/ButtonBar/ButtonBar.fxml"));
        Parent listView = FXMLLoader.load(getClass().getResource("/ListView/ListView.fxml"));
        Parent objectProperties = FXMLLoader.load(getClass().getResource("/Properties/ObjectpropertiesCircle_RICHTIGE_GRÖSSE.fxml"));
        Parent transform = FXMLLoader.load(getClass().getResource("/TransformationBar/TransformationBar.fxml"));/**/
        
        // Den einzelnen Bestandteilen Koordinaten im Gesamtprogramm zuordnen
        /**buttons.relocate(0, 0);
        listView.relocate(980, 0);
        objectProperties.relocate(980, 420);
        transform.relocate(0, 650);/**/
        canvas.relocate(0, 0);

        // Dateien zusammenführen
        Pane layout = new Pane();
        layout.getChildren().addAll(canvas);
        //layout.getChildren().addAll(buttons, listView, objectProperties, transform, canvas);

        // Layout zu einer Group hinzufügen
        Group root = new Group();
        root.getChildren().add(layout);
        
        // Szene mit dieser neuen Group erstellen und auf die Bühne bringen
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Atemberaubendes Transformationsprogramm");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
        
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
