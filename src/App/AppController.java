package App;

import GraphicObjects.*;
import IO.*;

import ListView.ListController;
import Utils.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.StrokeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author tim.giesenberg@me.com
 */
public class AppController implements Initializable {
    
    @FXML
    private AnchorPane AppUi;
    
    @FXML
    private ListView<GraphicObject> ObjectListView;
    
    @FXML
    private Pane canvas;
    
    @FXML
    private TextField polygonNumberOfAngles;
    
    @FXML
    private Button buttonCopyItem;
    
    @FXML
    private Button buttonDeleteItem;
    
    private ListController list;
    
    /**
     * Initializes the controller class.
     * runs on projectstart.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        //create new ListController
        list = ListController.getInstance(ObjectListView);
        
        list.setListView(ObjectListView);
        
    }
    
    private StrokeTransition aktuelleST;
    
    public void setStrokeTransition(StrokeTransition st) {
        this.aktuelleST = st;
    }
    
    public StrokeTransition getStrokeTransition() {
        return this.aktuelleST;
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
            if (this.aktuelleST != null) this.aktuelleST.stop();
            for (Node n : canvas.getChildren()) {
                if (n instanceof GraphicObject) {
                    GraphicObject g = (GraphicObject) n;
                    if (g instanceof Line) g.setStroke(g.getFill());
                    else g.setStroke(Color.TRANSPARENT);
                }
            }

            // Konturrand beim neu ausgewählten Objekt hinzufügen
            s.setStrokeWidth(3);
            Color fill = (Color) s.getFill();
            Color strokeColor = Color.hsb(fill.getHue() + 180, 1, 1);
            Color antiStrokeColor = Color.hsb(fill.getHue() + 90, 0.5, 0.5);
            this.aktuelleST = new StrokeTransition(Duration.millis(500), s, strokeColor, antiStrokeColor);
            this.aktuelleST.setCycleCount(StrokeTransition.INDEFINITE);
            this.aktuelleST.setAutoReverse(true);
            this.aktuelleST.play();
            
            //brings the object to the front
            s.toFront();
            System.out.println("You have chosen Object with name: " + s.getName());

            // change input fields
            this.setInputFieldValues(s);
           
        }
    }
    
    @FXML
    private Pane linePane;
    @FXML
    private Pane rectanglePane;
    @FXML
    private Pane circlePane;
    @FXML
    private Pane polygonPane;
    @FXML
    private Pane trianglePane;
    @FXML
    private Pane noObjectSelectedPane;
    
    @FXML
    private TextField circleName;
    @FXML
    private TextField circleRadius;
    @FXML
    private TextField circleX;
    @FXML
    private TextField circleY;
    @FXML
    private Slider circleRotationSlider;
    @FXML
    private ColorPicker circleColorFill;
    
    @FXML
    private TextField rectangleName;
    @FXML
    private TextField rectangleHeight;
    @FXML
    private TextField rectangleWidth;
    @FXML
    private TextField rectangleX;
    @FXML
    private TextField rectangleY;
    @FXML
    private Slider rectangleRotationSlider;
    @FXML
    private ColorPicker rectangleColorFill;
    
    @FXML
    private TextField polygonName;
    @FXML
    private TextField polygonX;
    @FXML
    private TextField polygonY;
    @FXML
    private Slider polygonRotationSlider;
    @FXML
    private ColorPicker polygonColorFill;
    
    @FXML
    private TextField triangleName;
    @FXML
    private TextField triangleSideLength;
    @FXML
    private TextField triangleX;
    @FXML
    private TextField triangleY;
    @FXML
    private Slider triangleRotationSlider;
    @FXML
    private ColorPicker triangleColorFill;
    
    @FXML
    private TextField lineName;
    @FXML
    private TextField lineLength;
    @FXML
    private TextField lineX;
    @FXML
    private TextField lineY;
    @FXML
    private Slider lineRotationSlider;
    @FXML
    private ColorPicker lineColorFill;
    
    @FXML
    private StackPane propertyPane;
    @FXML
    private Pane linealHorizontal;
    @FXML
    private Pane linealVertikal;
    
    
    private final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> ov,
        Number old_val, Number new_val) {
            GraphicObject s = list.getSelectedItem();
            if (s != null) {
                Matrix rotationMatrix = Transformate.getRotateAroundCenterMatrix(old_val.doubleValue()-new_val.doubleValue(), s.getCenter());
                s.transform(rotationMatrix);
            }
        }
    };
    
    /**
     * sets Values of Input Fields
     * @param s 
     */
    public void setInputFieldValues(GraphicObject s){
        
        // Vorherige Linealmarkierungen entfernen
        this.linealHorizontal.getChildren().clear();
        this.linealVertikal.getChildren().clear();
        
        // Linealmarkierungen erstellen
        if (s != null) {
            
            double x = 20 + s.getCenter().getX();
            double y = s.getCenter().getY();
            
            Line l1 = new Line("linie", Color.RED, x, 0, x, 20);
            l1.setStrokeWidth(2);
            Line l2 = new Line("linie", Color.RED, 0, y, 20, y);
            l2.setStrokeWidth(2);
            
            linealHorizontal.getChildren().add(l1);
            linealVertikal.getChildren().add(l2);
            
            // enable buttons
            this.buttonCopyItem.setDisable(false);
            this.buttonDeleteItem.setDisable(false);
            
        }
        
        // Alle Panes unsichtbar machen
        for (Node n : this.propertyPane.getChildren()) {
            if (n instanceof Pane) {
                Pane p = (Pane) n;
                p.setVisible(false);
            }
        }
        
        // Richtiges Pane anzeigen und mit Eigenschaften füllen
        if (s == null) {
            
            this.noObjectSelectedPane.setVisible(true);
            
        }
        else if (s instanceof Circle) {
            
            this.circlePane.setVisible(true);
            final Circle c = (Circle) s;
            
            // Kreiseigenschaften befüllen
            circleName.setText(c.getName()); 
            circleRadius.setText(""+c.getRadius());
            circleX.setText(""+c.getCenter().getX());
            circleY.setText(""+c.getCenter().getY());
            circleRotationSlider.valueProperty().removeListener(this.changeListener);
            circleRotationSlider.setValue(0);
            circleRotationSlider.valueProperty().addListener(this.changeListener);
            circleColorFill.setValue((Color) c.getFill());
            circleColorFill.setOnAction(new EventHandler() {
                @Override
                public void handle(Event e) {
                    c.setFill(circleColorFill.getValue());               
                }
            });
            
        } 
        else if (s instanceof Rectangle) {
            
            this.rectanglePane.setVisible(true);
            final Rectangle r = (Rectangle) s;
            
            // Rechteckeigenschaften befüllen
            rectangleName.setText(r.getName());
            rectangleHeight.setText(""+r.getHeight());
            rectangleWidth.setText(""+r.getWidth());
            rectangleX.setText(""+r.getCenter().getX());
            rectangleY.setText(""+r.getCenter().getY());
            rectangleRotationSlider.valueProperty().removeListener(this.changeListener);
            rectangleRotationSlider.setValue(0);
            rectangleRotationSlider.valueProperty().addListener(this.changeListener);
            rectangleColorFill.setValue((Color) r.getFill());
            rectangleColorFill.setOnAction(new EventHandler() {
                @Override
                public void handle(Event e) {
                    r.setFill(rectangleColorFill.getValue());               
                }
            });
            
        }
        else if (s instanceof Triangle) {
            
            this.trianglePane.setVisible(true);
            final Triangle t = (Triangle) s;
            
            // Dreieckeigenschaften befüllen
            triangleName.setText(t.getName());
            triangleSideLength.setText(""+t.getSideLength());
            triangleX.setText(""+t.getCenter().getX());
            triangleY.setText(""+t.getCenter().getY());
            triangleRotationSlider.valueProperty().removeListener(this.changeListener);
            triangleRotationSlider.setValue(0);
            triangleRotationSlider.valueProperty().addListener(this.changeListener);
            triangleColorFill.setValue((Color) t.getFill());
            triangleColorFill.setOnAction(new EventHandler() {
                @Override
                public void handle(Event e) {
                    t.setFill(triangleColorFill.getValue());               
                }
            });
            
        }
        else if (s instanceof Line) {
            
            this.linePane.setVisible(true);
            final Line l = (Line) s;
            
            // Linieneigenschaften befüllen
            lineName.setText(l.getName());
            lineLength.setText(""+l.getLength());
            lineX.setText(""+l.getCenter().getX());
            lineY.setText(""+l.getCenter().getY());
            lineRotationSlider.valueProperty().removeListener(this.changeListener);
            lineRotationSlider.setValue(0);
            lineRotationSlider.valueProperty().addListener(this.changeListener);
            lineColorFill.setValue((Color) l.getFill());
            lineColorFill.setOnAction(new EventHandler() {
                @Override
                public void handle(Event e) {
                    l.setFill(lineColorFill.getValue());
                    l.setStroke(lineColorFill.getValue());
                }
            });
            
        }
        else if (s instanceof Polygon) {
            
            this.polygonPane.setVisible(true);
            final Polygon p = (Polygon) s;
            
            // Polygoneigenschaften befüllen
            polygonName.setText(p.getName());
            polygonX.setText(""+p.getCenter().getX());
            polygonY.setText(""+p.getCenter().getY());
            polygonRotationSlider.valueProperty().removeListener(this.changeListener);
            polygonRotationSlider.setValue(0);
            polygonRotationSlider.valueProperty().addListener(this.changeListener);
            polygonColorFill.setValue((Color) p.getFill());
            polygonColorFill.setOnAction(new EventHandler() {
                @Override
                public void handle(Event e) {
                    p.setFill(polygonColorFill.getValue());               
                }
            });
            
        }
        
    }
    
    @FXML
    private void changeName(Event event){
        
        GraphicObject s = list.getSelectedItem();
        if (s instanceof Circle) {
            s.setName(circleName.getText());
        } 
        else if (s instanceof Line) {
            s.setName(lineName.getText());
        }
        else if (s instanceof Polygon) {
            s.setName(polygonName.getText());
        }
        else if (s instanceof Rectangle) {
            s.setName(rectangleName.getText());
        }
        else if (s instanceof Triangle) {
            s.setName(triangleName.getText());
        }
        // Liste muss direkt geupdatet werden
        
    }
    
    // ---------- Button Handler-Methoden ---------
    @FXML
    private void handleButtonClear(Event event) {
        
        // Objekte entfernen
        for (Node n : canvas.getChildren()) {
            if (n instanceof GraphicObject) {
                GraphicObject g = (GraphicObject) n;
                list.deleteItem(g);
            }
        }
        canvas.getChildren().clear();
        
        // Eigenschaften-Pane zurücksetzen
        this.setInputFieldValues(null);
        // Eingabefelder zurücksetzen
        this.clearTransformationBar(null);
        polygonNumberOfAngles.clear();
        // Disable buttons
        this.buttonCopyItem.setDisable(true);
        this.buttonDeleteItem.setDisable(true);
        // Objekte-Zähler zurücksetzen
        GraphicObject.resetCounter();
        Circle.resetCounter();
        Line.resetCounter();
        Polygon.resetCounter();
        Rectangle.resetCounter();
        Triangle.resetCounter();
        
        // Explosion
        Media m = new Media(new File("explosion.mp4").toURI().toString());
        MediaPlayer video = new MediaPlayer(m);
        video.setAutoPlay(true);
        MediaView mediaView = new MediaView(video);
        mediaView.setFitWidth(1200);
        AppUi.getChildren().add(mediaView);
        video.setOnEndOfMedia(new ExplosionRemover(AppUi, mediaView));
        
    }
    
    @FXML
    private void handleButtonSave(Event event) {
        
        // Fenster anlegen
        FileChooser fileChooser = new FileChooser();
        // Dateiendungen und weitere Eigenschaften festlegen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Speicher unter...");
        fileChooser.setInitialFileName("Unbenannt");
        
        // Fenster anziegen
        File file = fileChooser.showSaveDialog(null);
        
        // Wenn Dateipfad ausgewählt
        if (file != null) try {
            
            // Dort Objekte abspeichern
            GraphicObjectWriter gow = new GraphicObjectWriter(file.getPath());
            gow.write(list.getItems());
            
        } catch (IOException ex) {
            
            // Wenn ein Fehler auftritt, Fehlermeldung anzeigen
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Beim Speichern ist ein Fehler aufgetreten.")).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();
            
        }
        
    }
    
    @FXML
    private void handleButtonOpen(Event event) {
        
        // Fenster anlegen
        FileChooser fileChooser = new FileChooser();
        // Dateiendungen und weitere Eigenschaften festlegen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Öffnen...");
        
        // Fenster anziegen
        File file = fileChooser.showOpenDialog(null);
        
        // Wenn Dateipfad ausgewählt
        if (file != null) try {
            
            // Alte Objekte entfernen
            canvas.getChildren().clear();
            list.deleteAllItems();
            
            // GraphicObjects auslesen und dem Programm hinzufügen
            GraphicObjectReader gor = new GraphicObjectReader(file.getPath());
            GraphicObject g;
            do {
                
                g = gor.read();
                if (g != null) {
                    g.setOnMousePressed(new GraphicClickEventHandler(g, list, this));
                    list.addItem(g);
                    canvas.getChildren().add(g);
                }
                
            }
            // Wenn g == null, dann ist das File zu Ende
            while (g != null);
            
        } catch (FileNotFoundException fnfe) {
            
            // Wenn ein Fehler auftritt, Fehlermeldung anzeigen
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Beim Öffnen ist ein Fehler aufgetreten.")).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();
            
        } catch (IOException ioe) {
            
            // Wenn ein Fehler auftritt, Fehlermeldung anzeigen
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Beim Lesen der Datei ist ein Fehler aufgetreten.")).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();
            
            // Ggf. hinzugefügte Objekte wieder entfernen
            canvas.getChildren().clear();
            list.deleteAllItems();
            
        }
        
    }
    
    @FXML
    private void handleButtonActionRectangle(Event event) {
        
        final Rectangle r = new Rectangle(null, Color.GREEN, 100, 100, 100, 100);
        r.setOnMousePressed(new GraphicClickEventHandler(r, list, this));
        list.addItem(r);
        canvas.getChildren().add(r);
        
    }
    
    @FXML
    private void handleButtonActionCircle(Event event) {
        
        final Circle c = new Circle(null, Color.BLUE, 100, 100, 40);
        c.setOnMousePressed(new GraphicClickEventHandler(c, list, this));
        list.addItem(c);
        canvas.getChildren().add(c);
        
    }
    
    @FXML
    private void handleButtonActionTriangle(Event event) {
        
        final Triangle t = new Triangle(null, Color.YELLOW, 60, 60, 80);
        t.setOnMousePressed(new GraphicClickEventHandler(t, list, this));
        list.addItem(t);
        canvas.getChildren().add(t);
        
    }
    
    @FXML
    private void handleButtonActionLine(Event event) {
        
        final Line l = new Line(null, Color.RED, 100, 50, 150, 100);
        l.setOnMousePressed(new GraphicClickEventHandler(l, list, this));
        list.addItem(l);
        canvas.getChildren().add(l);
        
    }
    
    @FXML
    private void handleButtonActionPolygon(Event event) {
        
        String s = polygonNumberOfAngles.getText();
        if (s.length() > 0 && s.matches("[0-9]+")) {
            
            int numberOfAngles = Integer.parseInt(s);
            if (numberOfAngles > 2) {
                
                final Polygon p = new Polygon(null, Color.GRAY, numberOfAngles, 200, 200);
                p.setOnMousePressed(new GraphicClickEventHandler(p, list, this));
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
            // Element entfernen
            list.deleteItem(g);
            canvas.getChildren().remove(g);
            // Eigenschaftenfenster zurücksetzen
            this.setInputFieldValues(null);
            // Kein Objekt ausgewählt
            list.setFocus(null);
            // disable buttons
            this.buttonCopyItem.setDisable(true);
            this.buttonDeleteItem.setDisable(true);
        }
        
    }
    
    @FXML
    private void handleButtonCopyItem(Event event) {
        
        // Aktuelles Objekt ermitteln
        GraphicObject g = list.getSelectedItem();
        
        // Wenn ein Objekt ausgewählt ist
        if (g != null) {
            // Kopie des Objekts anlegen
            GraphicObject copyOfObject = g.getCopyInstance();
            copyOfObject.setOnMousePressed(
                new GraphicClickEventHandler(copyOfObject, list, this)
            );
            // Kopie hinzufügen
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
        
        // Skalieren-Darstellung
        scale0_0.setText(""+Double.parseDouble(scalePercent.getText())/100);
        scale1_1.setText(""+Double.parseDouble(scalePercent.getText())/100);
        
        // eingegebene Punkte in eine Matrix setzen
        Matrix transformationMatrix = Transformate.getTranslationMatrix(Double.parseDouble(translation0_2.getText()),Double.parseDouble(translation1_2.getText()));
        Matrix rotationMatrix = Transformate.getRotateMatrix(Double.parseDouble(rotateAt.getText()));
        Matrix scaleMatrix = Transformate.getScaleMatrix(Double.parseDouble(scalePercent.getText()));
        
        // eine Gesamtmatrix erstellen
        Matrix totalMatrix;
        totalMatrix= Transformate.multiplyMatrices(transformationMatrix, rotationMatrix);
        totalMatrix= Transformate.multiplyMatrices(totalMatrix, scaleMatrix);
        
        // Gesamtmatrix in der TransformationBar anzeigen
        total0_0.setText(""+totalMatrix.mat[0][0]);
        total0_1.setText(""+totalMatrix.mat[0][1]);
        total0_2.setText(""+totalMatrix.mat[0][2]);
        total1_0.setText(""+totalMatrix.mat[1][0]);
        total1_1.setText(""+totalMatrix.mat[1][1]);
        total1_2.setText(""+totalMatrix.mat[1][2]);
        
        // Objekt transformieren
        GraphicObject s = list.getSelectedItem();
        if (s != null) s.transform(totalMatrix);
        
        // Eigenschaften-Pane aktualisieren
        this.setInputFieldValues(s);

    }
    	
    @FXML
    private void clearTransformationBar(Event event){
        // die Eingabefelder wieder leeren
        translationToX.setText("");
        translationToY.setText("");
        rotateAt.setText("");
        scalePercent.setText("");
        
        // Matrix-Ansicht zurücksetzen
        translation0_2.setText("xT"); // das was ins label geschrieben wurde kommt in die angezeigte Matrix
        translation1_2.setText("yT");
        rotation0_0.setText("cos α");
        rotation0_1.setText("-sin α");
        rotation1_0.setText("sin α");
        rotation1_1.setText("cos α");
        scale0_0.setText("sT");
        scale1_1.setText("sT");
        total0_0.setText("m1");
        total0_1.setText("m2");
        total0_2.setText("m3");
        total1_0.setText("m4");
        total1_1.setText("m5");
        total1_2.setText("m6");
    }
}