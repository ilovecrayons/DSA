import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import edu.ftdev.Map.MapCanvas;

public class Tests {
    @Test
    public void testOnABCDE() throws IOException{
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");
        Set<String> allRoutes = mapCanvas.getRoutes();
        Set<String> ans = Program.getRoutes('A', allRoutes);
        
        assertTrue(ans.contains("AB"));
        assertTrue(ans.contains("AC"));
        assertTrue(ans.contains("AD"));
        assertEquals(3, ans.size());
        mapCanvas.close();
    }
    
    @Test
    public void testGetRoutesB() throws IOException{
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");
        Set<String> allRoutes = mapCanvas.getRoutes();
        
        Set<String> ans = Program.getRoutes('B', allRoutes);
        assertTrue(ans.isEmpty());
        assertEquals(0, ans.size());
        mapCanvas.close();
    }
    
    @Test
    public void testGetRoutesC() throws IOException{
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");
        Set<String> allRoutes = mapCanvas.getRoutes();
        
        Set<String> ans = Program.getRoutes('C', allRoutes);
        assertTrue(ans.contains("CD"));
        assertTrue(ans.contains("CA"));
        assertTrue(ans.contains("CB"));
        assertEquals(3, ans.size());
        mapCanvas.close();
    }
    
    @Test
    public void testGetRoutesD() throws IOException{
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");
        Set<String> allRoutes = mapCanvas.getRoutes();
        
        Set<String> ans = Program.getRoutes('D', allRoutes);
        assertTrue(ans.contains("DA"));
        assertTrue(ans.contains("DB"));
        assertTrue(ans.contains("DC"));
        assertEquals(3, ans.size());
        mapCanvas.close();
    }
    
    @Test
    public void testGetRoutesE() throws IOException{
        MapCanvas mapCanvas = new MapCanvas("Woodlawn.jpg");
        Set<String> allRoutes = mapCanvas.getRoutes();
        
        Set<String> ans = Program.getRoutes('E', allRoutes);
        assertTrue(ans.contains("EA"));
        assertTrue(ans.contains("EB"));
        assertTrue(ans.contains("EC"));
        assertTrue(ans.contains("ED"));
        assertEquals(4, ans.size());
        mapCanvas.close();
    }
}
