package ListView;

import GraphicObjects.GraphicObject;
import javafx.scene.control.ListCell;

/**
 *
 * @author Das TransPlosion-Team
 */
public class GraphicCell extends ListCell<GraphicObject>{
    
    @Override
    /**
     * 
     */
    public void updateItem(GraphicObject item, boolean empty) {
        
        super.updateItem(item, empty);

        if (item != null) {
            
            setText(item.getName());
            
        }
        
    }
    
}
