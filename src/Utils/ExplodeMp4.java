package Utils;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.*;
import javafx.scene.media.*;
import javafx.stage.Stage;

public class ExplodeMp4 extends Application {
  public static void main(String[] args) throws Exception { launch(args); }
  @Override public void start(final Stage stage) throws Exception {
    final MediaPlayer oracleVid = new MediaPlayer(
      new Media("file:///Users/Janis/VideoPlayerExample/explosion.mp4")
    );
    stage.setScene(new Scene(new Group(new MediaView(oracleVid)), 800, 600));
    
    stage.show();
  
    oracleVid.setMute(true);
    oracleVid.setRate(0.8);

    oracleVid.setCycleCount(1);

    oracleVid.play();
  }
}