import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        IntQueue queue = new IntQueue(new ArrayList<>());
        System.out.println(queue.test());
        IntStack stack = new IntStack(new ArrayList<>());
        System.out.println(stack.test());
    }
}
