
package Utils;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ExplodeGif extends JPanel implements ActionListener {
  ImageIcon images[];
  
    // Anzahl, Anfangsbild, Geschwindigkeit der Animation (je kleiner Zahl desto schneller abgespielt wird)
  int totalImages = 87, currentImage = 0, animationDelay = 35;
  Timer animationTimer;
  public ExplodeGif() {
    images = new ImageIcon[totalImages];
    for (int i = 0; i < images.length; ++i)
      images[i] = new ImageIcon("gifs/foto" + i + ".gif");
    startAnimation();
  }

  @Override // Malt die Bilder wenn die eingeladen sind
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (images[currentImage].getImageLoadStatus() == MediaTracker.COMPLETE) {
      images[currentImage].paintIcon(this, g, 0, 0);
      currentImage = (currentImage + 1); // Am Ende setzen für ein loop - " % totalImages " GIBT JETZT SEHR VIELE ERRORS AUS!!!
      
    }
  }

  
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  // Timer - startet die Animation
  public void startAnimation() {
    if (animationTimer == null) {
      currentImage = 0;
      animationTimer = new Timer(animationDelay, this);
      animationTimer.start();
    }
  }
  //    Stopp, nur einmal abspielen! * ABER hilft eh nicht...
  // } else if (!animationTimer.isRunning())
  //    animationTimer.restart();
  //}

  public void stopAnimation() {
    animationTimer.stop();
  }
  public static void main(String args[]) throws IOException {
    ExplodeGif anim = new ExplodeGif();
    JFrame app = new JFrame("Animator test");
    app.add(anim, BorderLayout.CENTER);
    app.setSize(800, 600);          // Setzt die Flächengröße fest
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    app.setVisible(true);
  }
}
