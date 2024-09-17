import org.junit.jupiter.api.*;
public class StudentTest {
    @Test
    public void constructorTest(){
        Student s = new Student(69420, "David Lin", 9);
        Assertions.assertEquals(69420, s.getId());
        Assertions.assertEquals("David Lin", s.getName());
        Assertions.assertEquals(9, s.getGrade());

    }

    @Test
    public void setNameTest(){
        Student s = new Student(69420, "David Lin", 9);
        s.setName("David Lin");
        Assertions.assertEquals("David Lin", s.getName());
    }

    @Test
    public void checkValidTest(){
        Student s = new Student(69420, "David Lin", 9);
        Assertions.assertTrue(s.checkValid());
        s = new Student(-69420, "David Lin", 9);
        Assertions.assertFalse(s.checkValid());
    }

    @Test
    public void testToString(){
        Student s = new Student(69420, "David Lin", 9);
        Assertions.assertEquals("ID: 69420 Name: David Lin Grade: 9", s.toString());
    }
}
