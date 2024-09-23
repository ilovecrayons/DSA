import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class ProgramTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testIsValid() throws Exception{
        String[] good = {"1","2","3","4","5","6"};
        Program.isValid(good);
    }
    
    @Test
    public void testIsValidEmpty() throws Exception {
        String[] bad = {"1", "2", "3", "", "5", "6"};
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Empty fields");
        Program.isValid(bad);
    }
    
    @Test 
    public void testIsValidWrongLat() throws Exception{
        String[] bad = {"1","2","3","91","5","6"};
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Latitude out of range");
        Program.isValid(bad);
    }
    
    @Test 
    public void testIsValidWrongLon() throws Exception{
        String[] bad = {"1","2","3","4","181","6"};
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Longitude out of range");
        Program.isValid(bad);
    }

    
    @Test 
    public void testIsValidWrongLatNeg() throws Exception{
        String[] bad = {"1","2","3","-91","5","6"};
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Latitude out of range");
        Program.isValid(bad);
    }
    
    @Test 
    public void testIsValidWrongLonNeg() throws Exception{
        String[] bad = {"1","2","3","4","-181","6"};
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Longitude out of range");
        Program.isValid(bad);
    }

    @Test
    public void testIsValidWrongNumFields() throws Exception{
        String[] bad = {"1","2","3","4","5"};
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Invalid number of fields");
        Program.isValid(bad);
    }

    
}
