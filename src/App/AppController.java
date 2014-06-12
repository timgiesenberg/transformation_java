/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import GraphicObjects.GraphicObject;
import GraphicObjects.Circle;
import GraphicObjects.Line;
import GraphicObjects.Polygon;
import GraphicObjects.Rectangle;
import GraphicObjects.Triangle;
import ListView.ListController;
import Utils.Matrix;
import Utils.Transformate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class AppController implements Initializable {
    
    @FXML
    final private ListView<GraphicObject> ObjectListView = new ListView<>();
    
    @FXML
    private Pane canvas;
    
    @FXML
    private TextField polygonNumberOfAngles;
    
    private ListController list;
    
    /**
     * Initializes the controller class.
     * runs on projectstart.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        //create new ListController
        list = ListController.getInstance(ObjectListView);
        
        list.setListView(ObjectListView);
        
        //if needed, add some previously generated Objects
        //list.setItems(FXCollections.observableArrayList(line));
    }
    
    /**
     * handles a click on ViewList items
     * @param event 
     */
    @FXML
    protected void handleListViewClick(Event event){
        //retrieves selected object
        GraphicObject s = list.getSelectedItem();
        
        if (s != null) {
        
            // Konturrand bei allen anderen Objekten entfernen
            for (Node n : canvas.getChildren()) {
                if (n instanceof GraphicObject) {
                    GraphicObject g = (GraphicObject) n;
                    if (g instanceof Line) g.setStroke(g.getFill());
                    else g.setStroke(Color.TRANSPARENT);
                }
            }

            // Konturrand beim neu ausgewählten Objekt hinzufügen
            // Zeichen, dass dieses Objekt ausgewählt ist
            Color fill = (Color) s.getFill();
            s.setStroke(
                Color.hsb(
                    fill.getHue() + 180, 
                    1, 
                    1
                )
            );
            s.setStrokeWidth(3);

            //brings the object to the front
            s.toFront();
            System.out.println("You have chosen Object with name: " + s.getName());

            // c// change input fieldshange input fields
        
        }
    }
    
    /**
     * sets Values of Input Fields
     * @param s 
     */
    protected void setInputFieldValues(GraphicObject s){
        if (s instanceof Circle) {
        } else if (s instanceof Rectangle) {
        }
    }
    
    @FXML
    private void handleButtonClear(Event event) {
        
        for (Node n : canvas.getChildren()) {
            if (n instanceof GraphicObject) {
                GraphicObject g = (GraphicObject) n;
                list.deleteItem(g);
            }
        }
        
        canvas.getChildren().clear();
        
    }
    
    @FXML
    private void handleButtonActionRectangle(Event event) {
        
        final Rectangle r = new Rectangle("Horst", Color.GREEN, 100, 100, 100, 100);
        r.setOnMousePressed(new GraphicClickEventHandler(r, list));
        list.addItem(r);
        canvas.getChildren().add(r);
    }
    
    @FXML
    private void handleButtonActionCircle(Event event) {
        
        final Circle c = new Circle("Angelika", Color.BLUE, 100, 100, 40);
        c.setOnMousePressed(new GraphicClickEventHandler(c, list));
        list.addItem(c);
        canvas.getChildren().add(c);
        
    }
    
    @FXML
    private void handleButtonActionTriangle(Event event) {
        
        final Triangle t = new Triangle("Wilhelmine", Color.YELLOW, 60, 60, 80);
        t.setOnMousePressed(new GraphicClickEventHandler(t, list));
        list.addItem(t);
        canvas.getChildren().add(t);
        
    }
    
    @FXML
    private void handleButtonActionLine(Event event) {
        
        final Line l = new Line("Gottfried", Color.RED, 100, 50, 150, 100);
        l.setOnMousePressed(new GraphicClickEventHandler(l, list));
        list.addItem(l);
        canvas.getChildren().add(l);
        
    }
    
    @FXML
    private void handleButtonActionPolygon(Event event) {
        
        String s = polygonNumberOfAngles.getText();
        if (s.length() > 0 && s.matches("[0-9]+")) {
            
            int numberOfAngles = Integer.parseInt(s);
            if (numberOfAngles > 0) {
                
                final Polygon p = new Polygon("Sybille", Color.GRAY, numberOfAngles, 200, 200);
                p.setOnMousePressed(new GraphicClickEventHandler(p, list));
                list.addItem(p);
                canvas.getChildren().add(p);
                polygonNumberOfAngles.setStyle("-fx-background-color: white");
                
            }
            else polygonNumberOfAngles.setStyle("-fx-background-color: red");
                
        }
        else polygonNumberOfAngles.setStyle("-fx-background-color: red");
        
    }    
    
    @FXML
    private void handleButtonDeleteItem(Event event) {
        
        GraphicObject g = list.getSelectedItem();
        if (g != null) {
            list.deleteItem(g);
            canvas.getChildren().remove(g);
        }
        
    }
    
    @FXML
    private void handleButtonCopyItem(Event event) {
        
        GraphicObject g = list.getSelectedItem();
        
        if (g != null) {
            GraphicObject copyOfObject = g.getCopyInstance();
            copyOfObject.setOnMousePressed(
                new GraphicClickEventHandler(copyOfObject, list)
            );

            list.addItem(copyOfObject);
            canvas.getChildren().add(copyOfObject);
        }
        
    }
    
    
    /**************************************************************************/
    //                      Spiegelung
    /**************************************************************************/
    
    @FXML
    private void handleButtonSpiegelungHorizontal(Event event) {
        
        GraphicObject g = list.getSelectedItem();
                
        if (g != null) {
        
            Matrix zumMittelpunkt = Transformate.getTranslationToOriginMatrix(g.getCenter());
            Matrix spiegelnHorizontal = Transformate.getFlipHorizontalMatrix();
            Matrix zurueckVerschieben = Transformate.getTranslationMatrix(g.getCenter().getX(), g.getCenter().getY());

            Matrix totalMatrix = Transformate.multiplyMatrices(spiegelnHorizontal, zumMittelpunkt);
            totalMatrix = Transformate.multiplyMatrices(zurueckVerschieben, totalMatrix);

            g.transform(totalMatrix);

        }
        
    }
    
    @FXML
    private void handleButtonSpiegelungVertikal(Event event) {
        
        GraphicObject g = list.getSelectedItem();
        
        if (g != null) {
        
          Matrix zumMittelpunkt = Transformate.getTranslationToOriginMatrix(g.getCenter());
          Matrix spiegelnVertikal = Transformate.getFlipVerticalMatrix();
          Matrix zurueckVerschieben = Transformate.getTranslationMatrix(g.getCenter().getX(), g.getCenter().getY());
          
          Matrix totalMatrix = Transformate.multiplyMatrices(spiegelnVertikal, zumMittelpunkt);
          totalMatrix = Transformate.multiplyMatrices(zurueckVerschieben, totalMatrix);
          
          g.transform(totalMatrix);
        
       }
        
    }
    

    /**************************************************************************/
    //                      TransformationBar
    /**************************************************************************/
    
    // Translations Eingabe und Anzeige-Matrix
    @FXML
    private Label translation0_2;
    @FXML
    private Label translation1_2;
    
    @FXML
    private TextField translationToX;
    @FXML
    private TextField translationToY;
    
    // Rotantions Eingabe und Anzeige- Matrix
    @FXML
    private Label rotation0_0;
    @FXML
    private Label rotation0_1;
    @FXML
    private Label rotation1_0;
    @FXML
    private Label rotation1_1;
    
    @FXML
    private TextField rotateAt;
    
    // Scale Eingabe und Anzeige-Matrix
    @FXML
    private Label scale0_0;
    @FXML
    private Label scale1_1;
    
    @FXML
    private TextField scalePercent;
    
    // GesamtMatrix-Anzeige
    @FXML
    private Label total0_0;
    @FXML
    private Label total0_1;
    @FXML
    private Label total0_2;
    @FXML
    private Label total1_0;
    @FXML
    private Label total1_1;
    @FXML
    private Label total1_2;
    
    @FXML
    private void transformationOfAll(Event event){
    	
    	
    	// falls ein Buchstabe oder nichts eingegeben wird wird der eingegebene Wert als 0 interpretiert
    	if (!translationToX.getText().matches("^-?\\d+([.]{1}\\d+)?")||translationToX.getText().length() == 0)
    		translationToX.setText(""+0); 
        if(!translationToY.getText().matches("^-?\\d+([.]{1}\\d+)?")||translationToY.getText().length() == 0)
    		translationToY.setText(""+0);
        if(!rotateAt.getText().matches("^-?\\d+([.]{1}\\d+)?")||rotateAt.getText().length() == 0)
			rotateAt.setText(""+0);
        if(!scalePercent.getText().matches("^-?\\d+([.]{1}\\d+)?")||scalePercent.getText().length() == 0)
			scalePercent.setText(""+100);
        
        
    	//Transformieren-Darstellung
    	translation0_2.setText(translationToX.getText()); // das was ins label geschrieben wurde kommt in die angezeigte Matrix
    	translation1_2.setText(translationToY.getText());
    	
    	
    	//Rotation-Darstellung
    	
    	rotation0_0.setText("cos ("+rotateAt.getText()+")");
    	rotation0_1.setText("-sin ("+rotateAt.getText()+")");
    	rotation1_0.setText("sin ("+rotateAt.getText()+")");
    	rotation1_1.setText("cos ("+rotateAt.getText()+")");
    	
    	
    	
    	// Scalieren-Darstellung
    	
    	scale0_0.setText(""+Double.parseDouble(scalePercent.getText())/100);
    	scale1_1.setText(""+Double.parseDouble(scalePercent.getText())/100);
    	
    	
    	// eingegebene Punkte in eine Matrix setzen
    	Matrix transformationMatrix = new Matrix();
    	transformationMatrix =Transformate.getTranslationMatrix(Double.parseDouble(translation0_2.getText()),Double.parseDouble(translation1_2.getText()));
    	
    	Matrix rotationMatrix=new Matrix();
    	rotationMatrix= Transformate.getRotateMatrix(Double.parseDouble(rotateAt.getText()));
    	
    	Matrix scaleMatrix= new Matrix();
    	scaleMatrix = Transformate.getScaleMatrix(Double.parseDouble(scalePercent.getText()));
    	
    	// die Eingabefelder wieder leeren
    	translationToX.setText("");
    	translationToY.setText("");
    	rotateAt.setText("");
    	scalePercent.setText("");
    	
    	
    	// eine Gesamtmatrix erstellen
    	Matrix totalMatrix = new Matrix();
    	totalMatrix= Transformate.multiplyMatrices(transformationMatrix, rotationMatrix);
    	totalMatrix= Transformate.multiplyMatrices(totalMatrix, scaleMatrix);
    	
    	// Gesamtmatrix in der TransformationBar anzeigen
    	total0_0.setText(""+totalMatrix.mat[0][0]);
    	total0_1.setText(""+totalMatrix.mat[0][1]);
    	total0_2.setText(""+totalMatrix.mat[0][2]);
    	total1_0.setText(""+totalMatrix.mat[1][0]);
    	total1_1.setText(""+totalMatrix.mat[1][1]);
    	total1_2.setText(""+totalMatrix.mat[1][2]);
    	
    	
    	GraphicObject s = list.getSelectedItem();
    	if (s != null) s.transform(totalMatrix);
    	// was fehlt hier?
    	//Scene-Builder anpassen
    	// Gesamtmatrix muss zwischengespeiert werden und das Objekt neu gezeichnet werden

    }
}
