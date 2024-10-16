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
                calc.evaluateExpression(OpNode.OpPrio.HIGHEST);
                calc.evaluateExpression(OpNode.OpPrio.HIGHER);
                calc.evaluateExpression(OpNode.OpPrio.LOWER);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
    }
}
