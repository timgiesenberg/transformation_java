/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ButtonBar;

import GraphicObjects.ProjectCircle;
import GraphicObjects.ProjectLine;
import GraphicObjects.ProjectPolygon;
import GraphicObjects.ProjectRectangle;
import GraphicObjects.ProjectTriangle;
import Utils.CanvasController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class ButtonBarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //canvas = CanvasController.getController();
    }
    
    private CanvasController canvas;
    
    public void setCanvasController(CanvasController c){
        canvas = c;
    }
    
    @FXML
    private void handleButtonActionRectangle(Event event) {
        
        final ProjectRectangle r = new ProjectRectangle("Horst", Color.GREEN, 100, 100, 100, 100);
        r.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Das Rechteck " + r.getName() + " wurde ausgewählt.");
                
            }
            
        });
        System.out.println("Nicht was ich erwartete");
        canvas.add(r);
    }
    
    public void test(){
        final ProjectRectangle r = new ProjectRectangle("Horst", Color.GREEN, 100, 100, 100, 100);
        r.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Das Rechteck " + r.getName() + " wurde ausgewählt.");
                
            }
            
        });
        System.out.println("Test funktion");
        canvas.add(r);
    }
    
    @FXML
    private void handleButtonActionCircle(ActionEvent event) {
        
        final ProjectCircle c = new ProjectCircle("Angelika", Color.BLUE, 100, 100, 40);
        c.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Der Kreis " + c.getName() + " wurde ausgewählt.");
                
            }
            
        });
        //canvas.getChildren().add(c);
        
    }
    
    @FXML
    private void handleButtonActionTriangle(ActionEvent event) {
        
        final ProjectTriangle t = new ProjectTriangle("Wilhelmine", Color.YELLOW, 60, 60, 80);
        t.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Das Dreieck " + t.getName() + " wurde ausgewählt.");
                
            }
            
        });
        //canvas.getChildren().add(t);
        
    }
    
    @FXML
    private void handleButtonActionLine(ActionEvent event) {
        
        final ProjectLine l = new ProjectLine("Gottfried", Color.RED, 100, 50, 150, 100);
        l.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("Die Linie " + l.getName() + " wurde ausgewählt.");
                
            }
            
        });
        //canvas.getChildren().add(l);
        
    }
    
    @FXML
    private void handleButtonActionPolygon(ActionEvent event) {
        /*
        String s = polygonNumberOfAngles.getCharacters().toString();
        if (s.length() > 0 && s.matches("[0-9]+")) {
            
            int numberOfAngles = Integer.parseInt(s);
            if (numberOfAngles > 0) {
                
                final ProjectPolygon p = new ProjectPolygon("Sybille", Color.GRAY, numberOfAngles, 200, 200);
                p.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        System.out.println("Das Polygon " + p.getName() + " wurde ausgewählt.");

                    }

                });
                //canvas.getChildren().add(p);
                
            }
                
        }
        /**/
    }
    
}
