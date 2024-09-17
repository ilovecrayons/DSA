public class Point {
    
    private int[] coordinates;
    private String name;

    public Point(int[] coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isValid(){
        boolean ans= (coordinates == null || coordinates.length != 2 || name == null || name.trim().equals("") ? false : true);
        if (ans){ // to satisfy one if else statement
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args){
        System.out.println("hello world");
    }


}
