/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

/**
 *
 * @author Phil
 */
public class ExplosionRemover implements Runnable {
    
    // IV: UI und MediaView
    private final AnchorPane appUI;
    private final MediaView mediaView;
    
    public ExplosionRemover(AnchorPane ap, MediaView mv) {
        
        this.appUI = ap;
        this.mediaView = mv;
        
    }
    
    @Override
    public void run() {
        if (this.appUI != null && this.mediaView != null) {
            this.appUI.getChildren().remove(this.mediaView);
        }
    }
    
}
