import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import edu.ftdev.KeyInterceptor.KeyHook;
import edu.ftdev.Map.MapCanvas;

public class Program {
    

   
    /**
     * Lambda method which will be called each time the user
     * is pressing the key 'T'.
     * @param keyEvent - details about the key which was pressed.
     */
    private static KeyHook _onKeyT = (KeyEvent keyEvent, Object[] args) -> {
        MapCanvas mc = (MapCanvas)args[0];
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        statusText += "Routes: " + mc.getRoutes();
        mc.setStatusMessage(statusText);
    };

    private static KeyHook _onABCDE = (KeyEvent keyEvent, Object[] args) -> {
        MapCanvas mc = (MapCanvas)args[0];
        Set<String> allRoutes = mc.getRoutes();
        Set<String> ans = getRoutes(keyEvent.getKeyChar(), allRoutes);
        mc.setStatusMessage(ans.toString());
        mc.setOverlays(ans);
    };
    
    public static Set<String> getRoutes(char start, Set<String> allRoutes) {
        Set<String> ans = new TreeSet<String>();
        for(String route : allRoutes){
            if(route.charAt(0) == start) ans.add(route);
        }
        return ans;
    }

    String originalRoute;

    private static KeyHook _onKeyX = (KeyEvent keyEvent, Object[] args) -> {
        MapCanvas mc = (MapCanvas)args[0];
        Set<String> overlays = mc.getOverlays();
        Set<String> allRoutes = mc.getRoutes();
        Set<String> ans = new TreeSet<String>();
        if(overlays.size() == 1){
            String overlayRoute = overlays.iterator().next();
            for(String route : allRoutes){
                if(mc.collide(overlayRoute, route)){
                    ans.add(route);
                }
            }
            mc.setOverlays(ans);
        } else {

        }
    };

    public static void main(String[] args) throws IOException, InterruptedException {
        // create a MapCanvas object and load it with an intersection image
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");

        // registers the key T with the method _onKeyT
        mapCanvas.setKeyHook('T', _onKeyT, mapCanvas);
        
        // opens the GUI window
        mapCanvas.open();
        
        // break;step-level execution
        mapCanvas.breakStep();

        // register the 'A', 'B', 'C', .. key strokes for demo route highlights
        //mapCanvas.setDemoKeyHooks(true);
        mapCanvas.setKeyHook('A', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('B', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('C', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('D', _onABCDE, mapCanvas);
        mapCanvas.setKeyHook('E', _onABCDE, mapCanvas);

        // break jump-level execution
        mapCanvas.breakJump();
        
        // close the window and terminate the program
        mapCanvas.close();
    }
}
