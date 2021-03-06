package App;

import GraphicObjects.GraphicClickEventHandler;
import GraphicObjects.Circle;
import GraphicObjects.GraphicObject;
import GraphicObjects.Line;
import GraphicObjects.Polygon;
import GraphicObjects.Rectangle;
import GraphicObjects.Triangle;
import IO.GraphicObjectReader;
import IO.GraphicObjectWriter;
import ListView.ListController;
import Utils.Matrix;
import Utils.Transformate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ResourceBundle;
import javafx.animation.StrokeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Die Klasse AppController koordiniert die Kommunikation zwischen User-Oberfläche und Programmlogik.
 * @author Das TransPlosion-Team
 */
public class AppController implements Initializable {
    
    // IV: Felder der GUI und Hilfsinstanzen
    @FXML
    private AnchorPane appUI;
    
    @FXML
    private ListView<GraphicObject> objectListView;
    
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
     * Initialisiert das TransPlosions-Programm.
     * @param url Ort des Programms
     * @param rb Quellen des Programms
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        // neuen ListController erstellen
        list = ListController.getInstance(objectListView);
        
    }
    
    // ------------------ Allgemeine Programmfunktionen ------------------------
    
    private StrokeTransition aktuelleST;
    
    private boolean objectIsClicked = false;
    
    /**
     * Setzt den aktuell blinkenden Rahmen.
     * @param st neuer blinkender Rahmen
     */
    public void setStrokeTransition(StrokeTransition st) {
        
        this.aktuelleST = st;
        
    }
    
    /**
     * Liefert den aktuell blinkenden Rahmen zurück.
     * @return aktuell blinkender Rahmen
     */
    public StrokeTransition getStrokeTransition() {
        
        return this.aktuelleST;
        
    }
    
    /**
     * Setzt, ob ein Objekt angeklickt wurde.
     * @param b Wahrheitswert, ob ein Objekt angeklickt wurde.
     */
    public void setObjectIsClicked(boolean b) {
        
        this.objectIsClicked = b;
        
    }
    
    /**
     * Beim Klick auf die Zeichenfläche ggf. Auswahl entfernen, wenn nicht
     * gerade ein Objekt angeklickt wurde.
     * @param event Event des Klicks
     */
    @FXML
    public void handleCanvasClick(Event event){
        
        // Nur, wenn kein Objekt ausgewählt wurde
        if (!objectIsClicked) {
            
            // Auswahl entfernen
            list.clearSelection();
            // Konturrand bei allen anderen Objekten entfernen
            if (this.aktuelleST != null) this.aktuelleST.stop();
            for (Node n : canvas.getChildren()) {
                
                if (n instanceof GraphicObject) {
                    
                    GraphicObject g = (GraphicObject) n;
                    if (g instanceof Line) g.setStroke(g.getFill());
                    else g.setStroke(Color.TRANSPARENT);
                    
                }
                
            }
            // Eigenschaften-Pane zurücksetzen
            this.setInputFieldValues(null);
            // Buttons deaktivieren
            this.buttonCopyItem.setDisable(true);
            this.buttonDeleteItem.setDisable(true);
            
        }
        else this.objectIsClicked = false;
        
    }
    
    /**
     * Aktualisiert bestimmte Teile des Programms beim Klick auf ein Objekt in
     * der Objektliste.
     * @param event Event des Klicks
     */
    @FXML
    protected void handleListViewClick(Event event){
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Wenn überhaupt eins ausgewählt wurde
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
            
            // holt das Objekt in den Vordergrund
            s.toFront();
            System.out.println("You have chosen Object with name: " + s.getName());

            // aktualisiert Eingabeleiste
            this.setInputFieldValues(s);
           
        }
        
    }
    
    // ---------------------- Eigenschaften-Leiste -----------------------------
    
    // IV: Felder der GUI
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
    
    // IV: ChangeListener, der die Objektrotationsslider überwacht
    private final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
        
        /**
         * Dreht ein Objekt beim Bewegen des Schiebers.
         */
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
    
    // IV: Lotlinien zu dem ausgewählten Objekt
    private javafx.scene.shape.Line lotHorizontal1 = null;
    private javafx.scene.shape.Line lotHorizontal2 = null;
    private javafx.scene.shape.Line lotVertikal1 = null;
    private javafx.scene.shape.Line lotVertikal2 = null;
    
    /**
     * Setzt die Feldwerte der Eigenschaftenleiste neu.
     * @param s Ausgewähltes GraphicObject
     */
    public void setInputFieldValues(GraphicObject s){
        
        // Vorherige Linealmarkierungen entfernen
        this.linealHorizontal.getChildren().clear();
        this.linealVertikal.getChildren().clear();
        if (lotVertikal1 != null) this.canvas.getChildren().remove(lotVertikal1);
        if (lotVertikal2 != null) this.canvas.getChildren().remove(lotVertikal2);
        if (lotHorizontal1 != null) this.canvas.getChildren().remove(lotHorizontal1);
        if (lotHorizontal2 != null) this.canvas.getChildren().remove(lotHorizontal2);
        lotVertikal1 = null;
        lotVertikal2 = null;
        lotHorizontal1 = null;
        lotHorizontal2 = null;
        
        // Wenn überhaupt ein Objekt ausgewählt wurde
        if (s != null) {
            
            // Buttons aktivieren
            this.buttonCopyItem.setDisable(false);
            this.buttonDeleteItem.setDisable(false);
            
            // Linealmarkierungen erstellen
            double x = s.getCenter().getX();
            double y = s.getCenter().getY();
            Line l1 = new Line("linie", Color.RED, x + 20, 0, x + 20, 20);
            l1.setStrokeWidth(2);
            Line l2 = new Line("linie", Color.RED, 0, y, 20, y);
            l2.setStrokeWidth(2);
            linealHorizontal.getChildren().add(l1);
            linealVertikal.getChildren().add(l2);
            
            // Lot zum Mittelpunkt fällen
            lotVertikal1 = new javafx.scene.shape.Line();
            lotVertikal1.setStartX(x);
            lotVertikal1.setStartY(0);
            lotVertikal1.setEndX(x);
            lotVertikal1.setEndY(y);
            lotVertikal1.setStroke(Color.BLACK);
            lotVertikal1.setStrokeWidth(0.3);
            lotVertikal1.getStrokeDashArray().addAll(10.0, 10.0);
            lotVertikal2 = new javafx.scene.shape.Line();
            lotVertikal2.setStartX(x);
            lotVertikal2.setStartY(0);
            lotVertikal2.setEndX(x);
            lotVertikal2.setEndY(y);
            lotVertikal2.setStroke(Color.WHITE);
            lotVertikal2.setStrokeWidth(0.3);
            lotVertikal2.getStrokeDashArray().addAll(10.0, 10.0);
            lotVertikal2.setStrokeDashOffset(10.0);
            lotHorizontal1 = new javafx.scene.shape.Line();
            lotHorizontal1.setStartX(0);
            lotHorizontal1.setStartY(y);
            lotHorizontal1.setEndX(x);
            lotHorizontal1.setEndY(y);
            lotHorizontal1.setStroke(Color.BLACK);
            lotHorizontal1.setStrokeWidth(0.3);
            lotHorizontal1.getStrokeDashArray().addAll(10.0, 10.0);
            lotHorizontal2 = new javafx.scene.shape.Line();
            lotHorizontal2.setStartX(0);
            lotHorizontal2.setStartY(y);
            lotHorizontal2.setEndX(x);
            lotHorizontal2.setEndY(y);
            lotHorizontal2.setStroke(Color.WHITE);
            lotHorizontal2.setStrokeWidth(0.3);
            lotHorizontal2.getStrokeDashArray().addAll(10.0, 10.0);
            lotHorizontal2.setStrokeDashOffset(10.0);
            this.canvas.getChildren().addAll(lotVertikal1, lotVertikal2, lotHorizontal1, lotHorizontal2);
            
        }
        
        // Alle Panes unsichtbar machen
        for (Node n : this.propertyPane.getChildren()) {
            
            if (n instanceof Pane) {
                
                Pane p = (Pane) n;
                p.setVisible(false);
                
            }
            
        }
        
        // Formatierung, nur zwei Nachkommastellen
        DecimalFormatSymbols fs = new DecimalFormatSymbols();
        fs.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat("#0.00", fs);
        
        // Richtiges Pane anzeigen und mit Eigenschaften füllen
        if (s == null) {
            
            // Pane, wenn kein Objekt ausgewählt wurde
            this.noObjectSelectedPane.setVisible(true);
            
        }
        else if (s instanceof Circle) {
            
            this.circlePane.setVisible(true);
            final Circle c = (Circle) s;
            
            // Kreiseigenschaften befüllen
            circleName.setText(c.getName()); 
            circleRadius.setText(f.format(c.getRadius()));
            circleX.setText(f.format(c.getCenter().getX()));
            circleY.setText(f.format(c.getCenter().getY()));
            circleRotationSlider.valueProperty().removeListener(this.changeListener);
            circleRotationSlider.setValue(0);
            circleRotationSlider.valueProperty().addListener(this.changeListener);
            circleColorFill.setValue((Color) c.getFill());
            circleColorFill.setOnAction(new EventHandler() {
                
                @Override
                /**
                 * Ändert die Farbe des Objekts.
                 */
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
            rectangleHeight.setText(f.format(r.getHeight()));
            rectangleWidth.setText(f.format(r.getWidth()));
            rectangleX.setText(f.format(r.getCenter().getX()));
            rectangleY.setText(f.format(r.getCenter().getY()));
            rectangleRotationSlider.valueProperty().removeListener(this.changeListener);
            rectangleRotationSlider.setValue(0);
            rectangleRotationSlider.valueProperty().addListener(this.changeListener);
            rectangleColorFill.setValue((Color) r.getFill());
            rectangleColorFill.setOnAction(new EventHandler() {
                
                @Override
                /**
                 * Ändert die Farbe des Objekts.
                 */
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
            triangleSideLength.setText(f.format(t.getSideLength()));
            triangleX.setText(f.format(t.getCenter().getX()));
            triangleY.setText(f.format(t.getCenter().getY()));
            triangleRotationSlider.valueProperty().removeListener(this.changeListener);
            triangleRotationSlider.setValue(0);
            triangleRotationSlider.valueProperty().addListener(this.changeListener);
            triangleColorFill.setValue((Color) t.getFill());
            triangleColorFill.setOnAction(new EventHandler() {
                
                @Override
                /**
                 * Ändert die Farbe des Objekts.
                 */
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
            lineLength.setText(f.format(l.getLength()));
            lineX.setText(f.format(l.getCenter().getX()));
            lineY.setText(f.format(l.getCenter().getY()));
            lineRotationSlider.valueProperty().removeListener(this.changeListener);
            lineRotationSlider.setValue(0);
            lineRotationSlider.valueProperty().addListener(this.changeListener);
            lineColorFill.setValue((Color) l.getFill());
            lineColorFill.setOnAction(new EventHandler() {
                
                @Override
                /**
                 * Ändert die Farbe des Objekts.
                 */
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
            polygonX.setText(f.format(p.getCenter().getX()));
            polygonY.setText(f.format(p.getCenter().getY()));
            polygonRotationSlider.valueProperty().removeListener(this.changeListener);
            polygonRotationSlider.setValue(0);
            polygonRotationSlider.valueProperty().addListener(this.changeListener);
            polygonColorFill.setValue((Color) p.getFill());
            polygonColorFill.setOnAction(new EventHandler() {
                
                @Override
                /**
                 * Ändert die Farbe des Objekts.
                 */
                public void handle(Event e) {
                    
                    p.setFill(polygonColorFill.getValue());  
                    
                }
            });
            
        }
        
    }
    
    // ---------------------- Änderungen am Objekt -----------------------------
    
    @FXML
    /**
     * Setzt den Namen des ausgewählten Objekts neu.
     */
    private void changeName(Event event){
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Setzt den Namen neu
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
        
        // Aktualisierung der Liste
        list.update();
        
    }
    
    @FXML
    /**
     * Setzt die x-Position des Mittelpunkts des ausgewählten Objekts neu.
     */
    private void changeX(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Aktives Textfeld suchen
        TextField x = null;
        if (rectanglePane.isVisible()) x = rectangleX;
        else if (circlePane.isVisible()) x = circleX;
        else if (trianglePane.isVisible()) x = triangleX;
        else if (linePane.isVisible()) x = lineX;
        else if (polygonPane.isVisible()) x = polygonX;
        
        // Wenn ein Objekt ausgewählt ist
        if (x != null) {
            
            // Form der Eingabe überprüfen
            if (x.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
                
                // x-Koordinate setzen
                s.moveToX(Double.parseDouble(x.getText()));
                x.setStyle("-fx-background-color: white");

            }
            else x.setStyle("-fx-background-color: red");
        
        }
        
        // Eigenschaftenleiste aktualisieren
        this.setInputFieldValues(s);
        
    }
    
    @FXML
    /**
     * Setzt die y-Position des Mittelpunkts des ausgewählten Objekts neu.
     */
    private void changeY(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Aktives Textfeld suchen
        TextField y = null;
        if (rectanglePane.isVisible()) y = rectangleY;
        else if (circlePane.isVisible()) y = circleY;
        else if (trianglePane.isVisible()) y = triangleY;
        else if (linePane.isVisible()) y = lineY;
        else if (polygonPane.isVisible()) y = polygonY;
        
        // Wenn ein Objekt ausgewählt ist
        if (y != null) {
        
            // Form der Eingabe überprüfen
            if (y.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
                
                // y-Koordinate setzen
                s.moveToY(Double.parseDouble(y.getText()));
                y.setStyle("-fx-background-color: white");

            }
            else y.setStyle("-fx-background-color: red");
        
        }
        
        // Eigenschaftenleiste aktualisieren
        this.setInputFieldValues(s);
        
    }
    
    @FXML
    /**
     * Setzt die Breite eines Rechtecks neu.
     */
    private void changeWidth(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Eingabe überprüfen
        if (rectangleWidth.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
            
            // Wenn Objekt ein Rechteck ist
            if (s instanceof Rectangle) {
                
                // Breite neu setzen
                Rectangle g = (Rectangle) s;
                g.setWidth(Double.parseDouble(rectangleWidth.getText()));
                rectangleWidth.setStyle("-fx-background-color: white");
                
            }
            
        }
        else rectangleWidth.setStyle("-fx-background-color: red");
        
    }
    
    @FXML
    /**
     * Setzt die Höhe eines Rechtecks neu.
     */
    private void changeHeight(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Eingabe überprüfen
        if (rectangleHeight.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
            
            // Wenn Objekt ein Rechteck ist
            if (s instanceof Rectangle) {
                
                // Höhe neu setzen
                Rectangle g = (Rectangle) s;
                g.setHeight(Double.parseDouble(rectangleHeight.getText()));
                rectangleHeight.setStyle("-fx-background-color: white");
                
            }
            
        }
        else rectangleHeight.setStyle("-fx-background-color: red");
        
    }
    
    @FXML
    /**
     * Setzt den Radius eines Kreises neu.
     */
    private void changeRadius(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Eingabe überprüfen
        if (circleRadius.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
            
            // Wenn Objekt ein Kreis ist
            if (s instanceof Circle) {
                
                // Radius neu setzen
                Circle c = (Circle) s;
                c.setRadius(Double.parseDouble(circleRadius.getText()));
                circleRadius.setStyle("-fx-background-color: white");
                
            }
            
        }
        else circleRadius.setStyle("-fx-background-color: red");
        
    }
    
    @FXML
    /**
     * Setzt die Seitenlänge eines Dreiecks neu.
     */
    private void changeSideLength(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Eingabe überprüfen
        if (triangleSideLength.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
            
            // Wenn Objekt ein Dreieck ist
            if (s instanceof Triangle) {
                
                // Seitenlänge neu setzen
                Triangle t = (Triangle) s;
                t.setSideLength(Double.parseDouble(triangleSideLength.getText()));
                triangleSideLength.setStyle("-fx-background-color: white");
                
            }
            
        }
        else triangleSideLength.setStyle("-fx-background-color: red");
        
    }
    
    @FXML
    /**
     * Setzt die Länge einer Linie neu.
     */
    private void changeLength(Event event) {
        
        // Holt das aktuell ausgewählte Objekt
        GraphicObject s = list.getSelectedItem();
        
        // Eingabe überprüfen
        if (lineLength.getText().matches("^-?\\d+([.]{1}\\d+)?")) {
            
            // Wenn Objekt eine Linie ist
            if (s instanceof Line) {
                
                // Länge neu setzen
                Line l = (Line) s;
                l.setLength(Double.parseDouble(lineLength.getText()));
                lineLength.setStyle("-fx-background-color: white");
                
            }
            
        }
        else lineLength.setStyle("-fx-background-color: red");
        
    }
    
    // -------------------- Button Handler-Methoden ----------------------------
    @FXML
    /**
     * Setzt das Programmfenster mit einer Explosion zurück.
     */
    private void handleButtonClear(Event event) {
        
        // Objekte entfernen
        for (Node n : canvas.getChildren()) {
            
            if (n instanceof GraphicObject) {
                
                GraphicObject g = (GraphicObject) n;
                list.deleteItem(g);
                
            }
            
        }
        canvas.getChildren().clear();
        list.deleteAllItems();
        
        // Eigenschaften-Pane zurücksetzen
        this.setInputFieldValues(null);
        
        // Eingabefelder zurücksetzen
        this.clearTransformationBar(null);
        polygonNumberOfAngles.clear();
        
        // Buttons deaktivieren
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
        mediaView.setPreserveRatio(false);
        mediaView.setFitHeight(800);
        mediaView.setFitWidth(1200);
        appUI.getChildren().add(mediaView);
        video.setOnEndOfMedia(new ExplosionRemover(appUI, mediaView));
        
    }
    
    @FXML
    /**
     * Speichert die aktuellen GraphicObjects.
     */
    private void handleButtonSave(Event event) {
        
        // Fenster anlegen
        FileChooser fileChooser = new FileChooser();
        
        // Dateiendungen und weitere Eigenschaften festlegen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Speicher unter...");
        fileChooser.setInitialFileName("Unbenannt");
        
        // Fenster anzeigen
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
    /**
     * Öffnet eine TXT-Datei mit GraphicObject-Inhalt.
     */
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
    /**
     * Erzeugt ein neues Rechteck.
     */
    private void handleButtonActionRectangle(Event event) {
        
        // Rechteck erzeugen und hinzufügen
        final Rectangle r = new Rectangle(null, Color.valueOf("#435555"), 100, 100, 100, 100);
        r.setOnMousePressed(new GraphicClickEventHandler(r, list, this));
        list.addItem(r);
        canvas.getChildren().add(r);
        
    }
    
    @FXML
    /**
     * Erzeugt einen neuen Kreis.
     */
    private void handleButtonActionCircle(Event event) {
        
        // Kreis erzeugen und hinzufügen
        final Circle c = new Circle(null, Color.valueOf("#4499DC"), 100, 100, 40);
        c.setOnMousePressed(new GraphicClickEventHandler(c, list, this));
        list.addItem(c);
        canvas.getChildren().add(c);
        
    }
    
    @FXML
    /**
     * Erzeugt ein neues Dreieck.
     */
    private void handleButtonActionTriangle(Event event) {
        
        // Dreieck erzeugen und hinzufügen
        final Triangle t = new Triangle(null, Color.valueOf("#879999"), 60, 60, 80);
        t.setOnMousePressed(new GraphicClickEventHandler(t, list, this));
        list.addItem(t);
        canvas.getChildren().add(t);
        
    }
    
    @FXML
    /**
     * Erzeugt eine neue Linie.
     */
    private void handleButtonActionLine(Event event) {
        
        // Linie erzeugen und hinzufügen
        final Line l = new Line(null, Color.valueOf("#DDDDDD"), 100, 50, 150, 100);
        l.setOnMousePressed(new GraphicClickEventHandler(l, list, this));
        list.addItem(l);
        canvas.getChildren().add(l);
        
    }
    
    @FXML
    /**
     * Erzeugt ein neues Polygon.
     */
    private void handleButtonActionPolygon(Event event) {
        
        // Überprüft die Eingabe der Eckenanzahl
        String s = polygonNumberOfAngles.getText();
        if (s.length() > 0 && s.matches("[0-9]+")) {
            
            int numberOfAngles = Integer.parseInt(s);
            if (numberOfAngles > 2) {
                
                // Polygon erzeugen und hinzufügen
                final Polygon p = new Polygon(null, Color.valueOf("#AACCCD"), numberOfAngles, 200, 200);
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
    /**
     * Löscht das ausgewählte Objekt.
     */
    private void handleButtonDeleteItem(Event event) {
        
        // Holt das ausgewählte Objekt
        GraphicObject g = list.getSelectedItem();
        
        if (g != null) {
            
            // Element entfernen
            list.deleteItem(g);
            canvas.getChildren().remove(g);
            
            // Eigenschaftenfenster zurücksetzen
            this.setInputFieldValues(null);
            
            // Objektauswahl aufheben
            list.setFocus(null);
            
            // Buttons deaktivieren
            this.buttonCopyItem.setDisable(true);
            this.buttonDeleteItem.setDisable(true);
            
        }
        
    }
    
    @FXML
    /**
     * Kopiert das ausgewählte Objekt.
     */
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
    
    
    // ------------------------- Spiegelung ------------------------------------
    
    @FXML
    /**
     * Spiegelt das ausgewählte Objekt horizontal.
     */
    private void handleButtonSpiegelungHorizontal(Event event) {
        
        // Holt das ausgewählte Objekt
        GraphicObject g = list.getSelectedItem();
        
        // Wenn überhaupt ein Objekt ausgewählt wurde
        if (g != null) {
            
            // Matrizen erstellen, miteinander multiplizieren und Objekt transformieren
            Matrix zumMittelpunkt = Transformate.getTranslationToOriginMatrix(g.getCenter());
            Matrix spiegelnHorizontal = Transformate.getFlipHorizontalMatrix();
            Matrix zurueckVerschieben = Transformate.getTranslationMatrix(g.getCenter().getX(), g.getCenter().getY());

            Matrix totalMatrix = Transformate.multiplyMatrices(spiegelnHorizontal, zumMittelpunkt);
            totalMatrix = Transformate.multiplyMatrices(zurueckVerschieben, totalMatrix);

            g.transform(totalMatrix);
            
        }
        
    }
    
    @FXML
    /**
     * Spiegelt das ausgewählte Objekt vertikal.
     */
    private void handleButtonSpiegelungVertikal(Event event) {
        
        // Holt das ausgewählte Objekt
        GraphicObject g = list.getSelectedItem();
        
        // Wenn überhaupt ein Objekt ausgewählt wurde
        if (g != null) {
            
            // Matrizen erstellen, miteinander multiplizieren und Objekt transformieren
            Matrix zumMittelpunkt = Transformate.getTranslationToOriginMatrix(g.getCenter());
            Matrix spiegelnVertikal = Transformate.getFlipVerticalMatrix();
            Matrix zurueckVerschieben = Transformate.getTranslationMatrix(g.getCenter().getX(), g.getCenter().getY());
            
            Matrix totalMatrix = Transformate.multiplyMatrices(spiegelnVertikal, zumMittelpunkt);
            totalMatrix = Transformate.multiplyMatrices(zurueckVerschieben, totalMatrix);
            
            g.transform(totalMatrix);
            
        }
        
    }
    
    // ---------------------- Transformationsleiste ----------------------------
    
    // IV: Felder der GUI für die Transformationsleiste
    // Translationseingabe und Anzeige-Matrix
    @FXML
    private Label translation0_2;
    @FXML
    private Label translation1_2;
    
    @FXML
    private TextField translationToX;
    @FXML
    private TextField translationToY;
    
    // Rotationseingabe und Anzeige-Matrix
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
    
    // Skalierungseingabe und Anzeige-Matrix
    @FXML
    private Label scale0_0;
    @FXML
    private Label scale1_1;
    
    @FXML
    private TextField scaleFactor;
    
    // Gesamtmatrix-Anzeige
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
    /**
     * Transformiert das ausgewählte Objekt anhand der eingegebenen Werte.
     */
    private void transformationOfAll(Event event){
        
        // Falls nichts eingegeben wurde, wird der eingegebene Wert als 0 interpretiert
        if (translationToX.getText().length() == 0) translationToX.setText(""+0);
        if (translationToY.getText().length() == 0) translationToY.setText(""+0);
        if (rotateAt.getText().length() == 0) rotateAt.setText(""+0);
        if (scaleFactor.getText().length() == 0) scaleFactor.setText(""+1);
        
        // Falls etwas falsches eingegeben wurde, Fehler anzeigen
        if (!translationToX.getText().matches("^-?\\d+([.]{1}\\d+)?")) translationToX.setStyle("-fx-background-color: red");
        else translationToX.setStyle("-fx-background-color: white");
        if (!translationToY.getText().matches("^-?\\d+([.]{1}\\d+)?")) translationToY.setStyle("-fx-background-color: red");
        else translationToY.setStyle("-fx-background-color: white");
        if (!rotateAt.getText().matches("^-?\\d+([.]{1}\\d+)?")) rotateAt.setStyle("-fx-background-color: red");
        else rotateAt.setStyle("-fx-background-color: white");
        if (!scaleFactor.getText().matches("^-?\\d+([.]{1}\\d+)?")) scaleFactor.setStyle("-fx-background-color: red");
        else scaleFactor.setStyle("-fx-background-color: white");
        
        // Wenn kein Fehler gefunden wurde
        if (translationToX.getText().matches("^-?\\d+([.]{1}\\d+)?") && translationToY.getText().matches("^-?\\d+([.]{1}\\d+)?")
            && rotateAt.getText().matches("^-?\\d+([.]{1}\\d+)?") && scaleFactor.getText().matches("^-?\\d+([.]{1}\\d+)?")) 
        {
        
            // Transformieren-Darstellung
            translation0_2.setText(translationToX.getText()); // das was ins label geschrieben wurde kommt in die angezeigte Matrix
            translation1_2.setText(translationToY.getText());

            // Rotation-Darstellung
            rotation0_0.setText("cos ("+rotateAt.getText()+")");
            rotation0_1.setText("-sin ("+rotateAt.getText()+")");
            rotation1_0.setText("sin ("+rotateAt.getText()+")");
            rotation1_1.setText("cos ("+rotateAt.getText()+")");

            // Skalieren-Darstellung
            scale0_0.setText(""+Double.parseDouble(scaleFactor.getText()));
            scale1_1.setText(""+Double.parseDouble(scaleFactor.getText()));

            // eingegebene Punkte in eine Matrix setzen
            Matrix transformationMatrix = Transformate.getTranslationMatrix(Double.parseDouble(translation0_2.getText()),Double.parseDouble(translation1_2.getText()));
            Matrix rotationMatrix = Transformate.getRotateMatrix(Double.parseDouble(rotateAt.getText()));
            Matrix scaleMatrix = Transformate.getScaleMatrix(Double.parseDouble(scaleFactor.getText()));

            // eine Gesamtmatrix erstellen
            Matrix totalMatrix;
            totalMatrix = Transformate.multiplyMatrices(transformationMatrix, rotationMatrix);
            totalMatrix = Transformate.multiplyMatrices(totalMatrix, scaleMatrix);

            // Gesamtmatrix in der Transformationleiste anzeigen
            DecimalFormatSymbols fs = new DecimalFormatSymbols();
            fs.setDecimalSeparator('.');
            DecimalFormat f = new DecimalFormat("#0.00", fs); // lässt nur zwei Nachkommastellen anzeigen
            total0_0.setText(f.format(totalMatrix.mat[0][0]));
            total0_1.setText(f.format(totalMatrix.mat[0][1]));
            total0_2.setText(f.format(totalMatrix.mat[0][2]));
            total1_0.setText(f.format(totalMatrix.mat[1][0]));
            total1_1.setText(f.format(totalMatrix.mat[1][1]));
            total1_2.setText(f.format(totalMatrix.mat[1][2]));

            // Objekt transformieren
            GraphicObject s = list.getSelectedItem();
            if (s != null) s.transform(totalMatrix);

            // Eigenschaften-Pane aktualisieren
            this.setInputFieldValues(s);
            
        }

    }
    	
    @FXML
    /**
     * Setzt die Eingabefelder auf der Transformationsleiste zurück.
     */
    private void clearTransformationBar(Event event){
        
        // die Eingabefelder wieder leeren
        translationToX.setText("");
        translationToY.setText("");
        rotateAt.setText("");
        scaleFactor.setText("");
        
        // Matrix-Ansicht zurücksetzen
        translation0_2.setText("xT");
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