import org.junit.jupiter.api.*;

public class PointTest {
    @Test 
    public void ConstructorTest(){
        int[] coordinates = {1, 2};
        Point p = new Point(coordinates, "point");
        Assertions.assertArrayEquals(coordinates, p.getCoordinates());
        Assertions.assertEquals("point", p.getName());
    }

    @Test
    public void getCoordinatesTest(){
        int[] coordinates = {1, 2};
        Point p = new Point(coordinates, "point");
        Assertions.assertArrayEquals(coordinates, p.getCoordinates());
    }

    @Test
    public void getNameTest(){
        int[] coordinates = {1, 2};
        Point p = new Point(coordinates, "point");
        Assertions.assertEquals("point", p.getName());
    }

    @Test
    public void setCoordinatesTest(){
        int[] coordinates = {1, 2};
        Point p = new Point(coordinates, "point");
        int[] newCoordinates = {3, 4};
        p.setCoordinates(newCoordinates);
        Assertions.assertArrayEquals(newCoordinates, p.getCoordinates());
    }

    @Test
    public void isValidTest(){
        int[] coordinates = {1, 2};
        Point p1 = new Point(coordinates, "point");
        Point p2 = new Point(null, "point");
        Point p3 = new Point(coordinates, "");
        Point p4 = new Point(coordinates, null);
        coordinates = new int[3];
        Point p5 = new Point(coordinates, "point");
        Assertions.assertTrue(p1.isValid());
        Assertions.assertFalse(p2.isValid());
        Assertions.assertFalse(p3.isValid());
        Assertions.assertFalse(p4.isValid());
        Assertions.assertFalse(p5.isValid());
    }

}
