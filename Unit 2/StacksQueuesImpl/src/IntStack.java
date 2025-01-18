import java.util.ArrayList;

public class IntStack {
    private ArrayList<Integer> list ;
    int n = 10000;
    public IntStack(ArrayList<Integer> list){
        this.list = list;
    }

    public void push(int value){

        list.add(value);
    }

    public int pop(){
        return list.remove(list.size() - 1);
    }

    public int peek(){
        return list.get(list.size() - 1);
    }

    public boolean isEmpty(){
        return list.size() == 0;
    }

    public int size(){
        return list.size();
    }

    public long test(){
        long startTime = System.nanoTime();
        for(int i = 0; i < n; i++){
            push(i);
        }
        for(int i = 0; i < n; i++){
            pop();
        }
        return System.nanoTime() - startTime;
    }
}
