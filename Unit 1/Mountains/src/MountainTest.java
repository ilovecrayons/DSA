import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class MountainTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testIsValid() throws Exception{
        String[] good = {"1","2","3","4","5","6"};
        Mountain.isValid(good);
    }
    
    @Test
    public void testIsValidEmpty() throws Exception {
        try{
            String[] bad = {"1", "2", "3", "", "5", "6"};
            Mountain.isValid(bad);}
        catch  (RuntimeException e) {
            assertEquals("Empty fields", e.getMessage());
        }

    }
    
    @Test 
    public void testIsValidWrongLat() throws Exception{
        try{
        String[] bad = {"1","2","3","91","5","6"};
        
        Mountain.isValid(bad);
        }
        catch  (RuntimeException e) {
            assertEquals("Latitude out of range", e.getMessage());
        }
    }
    
    @Test 
    public void testIsValidWrongLon() throws Exception{
        try{
        String[] bad = {"1","2","3","4","181","6"};
        
        Mountain.isValid(bad);}
        catch  (RuntimeException e) {
        assertEquals("Longitude out of range", e.getMessage());
        }
    }

    
    @Test 
    public void testIsValidWrongLatNeg() throws Exception{
        try{
        String[] bad = {"1","2","3","-91","5","6"};
        
        Mountain.isValid(bad);}
        catch  (RuntimeException e) {
            assertEquals("Latitude out of range", e.getMessage());
        }
    }
    
   
    
    
    @Test 
    public void testIsValidWrongLonNeg() throws Exception{
        try{
        String[] bad = {"1","2","3","4","-181","6"};
        
        Mountain.isValid(bad);}
        catch  (RuntimeException e) {
            assertEquals("Longitude out of range", e.getMessage());
        }
    }

    @Test
    public void testIsValidWrongNumFields() throws Exception{
        try{
        String[] bad = {"1","2","3","4","5"};
        
        Mountain.isValid(bad);}
        catch  (RuntimeException e) {
            assertEquals("Invalid number of fields", e.getMessage());
        }
    }


    
}
