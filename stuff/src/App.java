import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        nNearestNeighbors(null, null, 0);
    }

    public static ArrayList<Point> nNearestNeighbors(Point[] points, Point p, int n){
        ArrayList<Point> pointList = new ArrayList<>();
        ArrayList<Point> ans = new ArrayList<>();
        for (Point point: points){
            pointList.add(point);
        }
        bubbleSort(pointList, p);
        for(int i=points.length-1; i>points.length-n-1; i--){
            
            ans.add(pointList.get(i));
        }
        return ans;
    }

    public static void bubbleSort(ArrayList<Point> points, Point p){
        int n = points.size();
        for(int i = 0; i<n-1; i++){
            for(int j=0; i<n-i-1; j++){
                if(points.get(j).distanceToPoint(p) < points.get(j+1).distanceToPoint(p)){
                    Point temp = points.get(j);   
                    points.set(j, points.get(j+1));
                    points.set(j+1, temp);

                }
            }
        }    
    }
}
