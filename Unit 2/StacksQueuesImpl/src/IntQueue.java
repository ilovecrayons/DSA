import java.util.ArrayList;

public class IntQueue{
    ArrayList<Integer> list;
    int n = 10000;
    public IntQueue(ArrayList<Integer> list){
        this.list = list;
    }

    public void add(int value){
        list.add(value);
    }

    public int remove(){
        return list.remove(0);
    }

    public int peek(){
        return list.get(0);
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
            add(i);
        }
        for(int i = 0; i < n; i++){
            remove();
        }
        return System.nanoTime() - startTime;
    }

}