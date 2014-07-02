package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Programm zum Testen von Transformationen und Explosionen.
 * @author Das TransPlosion-Team
 */
public class InfoProjektJAVAFX extends Application {
    
    /**
     * Startet das TransPlosions-Programm.
     * @param stage Fenster des Programms
     * @throws Exception Fehler beim Starten der App.
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        // JavaFX Version ausgeben
        System.out.println("Your Computer is running JavaFX version " + com.sun.javafx.runtime.VersionInfo.getRuntimeVersion());

        // FXML-Datei importieren
        Parent canvas = FXMLLoader.load(getClass().getResource("/App/Parent.fxml"));
        canvas.relocate(0, 0);

        // Dateien zusammenführen
        Pane layout = new Pane();
        layout.getChildren().addAll(canvas);

        // Layout zu einer Group hinzufügen
        Group root = new Group();
        root.getChildren().add(layout);
        
        // Szene mit dieser neuen Group erstellen und auf die Bühne bringen
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TRANSPLOSION");
        //TODO set AppIcon
        stage.getIcons().add(new Image("/img/Neu.png"));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
        
    }

    /**
     * Startet das Ausführen der Applikation.
     * @param args Komandozeilen-Parameter
     */
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
}
