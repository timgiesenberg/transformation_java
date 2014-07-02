package App;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

/**
 * Die Klasse ExplosionRemover entfernt das Anzeigefenster der Explosion 
 * bei dessen Ende.
 * @author Das TransPlosion-Team
 */
public class ExplosionRemover implements Runnable {
    
    // IV: UI und MediaView
    private final AnchorPane appUI;
    private final MediaView mediaView;
    
    /**
     * Erstellt einen neuen ExplosionRemover.
     * @param ap AnchorPane, auf dem der MediaView liegt
     * @param mv MediaView, in dem die Explosion angezeigt wird
     */
    public ExplosionRemover(AnchorPane ap, MediaView mv) {
        
        this.appUI = ap;
        this.mediaView = mv;
        
    }
    
    @Override
    /**
     * Entfernt das Explosionsfenster.
     */
    public void run() {
        
        // Wenn Objekte referenziert sind
        if (this.appUI != null && this.mediaView != null) {
            
            // MediaView aus der GUI entfernen
            this.appUI.getChildren().remove(this.mediaView);
            
        }
        
    }
    
}
