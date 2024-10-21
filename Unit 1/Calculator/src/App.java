import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String expr = "";
        while (true) {
            System.out.print("Enter expression: ");
            expr = scanner.nextLine();
            if(expr.equals("exit") || expr.equals("quit")) break;

            try {
                NumCalc calc = new NumCalc(expr);
                ArrayList<String> steps = calc.evaluateExpression(OpNode.OpPrio.HIGHEST);
                
                steps.addAll(calc.evaluateExpression(OpNode.OpPrio.HIGHER));
                
                steps.addAll(calc.evaluateExpression(OpNode.OpPrio.LOWER));
                
                steps = new ArrayList<String>(new HashSet<String>(steps));
                System.out.println("Result: " + steps.get(steps.size() - 1));
                System.out.println("Steps:");
                for(String step : steps) {
                    System.out.println(step);
                }

                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
    }
}
